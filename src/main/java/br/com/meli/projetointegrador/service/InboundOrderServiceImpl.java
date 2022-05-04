package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentInboundOrderException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.model.StockManager;
import br.com.meli.projetointegrador.repository.*;
import br.com.meli.projetointegrador.security.services.UserDetailsImpl;
import br.com.meli.projetointegrador.validator.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InboundOrderServiceImpl implements InboundOrderService {

    private InboundOrderRepository inboundOrderRepository;
    private BatchService batchService;
    private SectionService sectionService;
    private StockManagerService stockManagerService;
    private WarehouseService warehouseService;



    @Override
    public List<Batch> save(InboundOrder inboundOrder) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        StockManager stockManager = stockManagerService.findByUserUsername(userDetails.getUsername());
        inboundOrder.setStockManager(stockManager);

        List<Validator> validators = Arrays.asList(
                new WarehouseExists(inboundOrder.getSection().getWarehouse().getId(), warehouseService),
                new SectionExists(sectionService, inboundOrder.getSection().getId()),
                new SectionAvailableSpace(sectionService, inboundOrder.getSection().getId(), inboundOrder.getBatchList()),
                new SectionMatchWithWarehouse(inboundOrder.getSection(),sectionService),
                new StockManagerMatchWithWarehouse(stockManagerService, inboundOrder.getStockManager().getId(), inboundOrder.getSection().getWarehouse().getId())
        );

        validators.forEach(Validator::validate);
        inboundOrder.getBatchList().forEach(batch -> new SectionMatchWithBatchCategory(batch).validate());

        InboundOrder orderCreated = inboundOrderRepository.save(inboundOrder);

        inboundOrder.getBatchList().forEach(batch -> batch.setInboundOrder(orderCreated));
        List<Batch> batchesCreated = batchService.save(inboundOrder.getBatchList());

        sectionService.updateCurrentSize(inboundOrder.getBatchList().size(), inboundOrder.getSection().getId(), false);

        return batchesCreated;
    }

    @Override
    public List<Batch> update(InboundOrder inboundOrder) {
        List<Batch> newBatches = inboundOrder.getBatchList().stream().filter(batch -> Objects.isNull(batch.getId())).collect(Collectors.toList());

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StockManager stockManager = stockManagerService.findByUserUsername(userDetails.getUsername());

        List<Validator> validators = Arrays.asList(
                new SectionAvailableSpace(sectionService, inboundOrder.getSection().getId(), newBatches),
                new StockManagerMatchWithWarehouse(stockManagerService, stockManager.getId(), inboundOrder.getSection().getWarehouse().getId())
        );

        validators.forEach(Validator::validate);

        inboundOrderRepository.save(inboundOrder);

        List<Batch> batchesCreated = batchService.save(inboundOrder.getBatchList());

        sectionService.updateCurrentSize(newBatches.size(), inboundOrder.getSection().getId(), false);

        return batchesCreated;
    }

    @Override
    public InboundOrder findById(Long id) {
        return inboundOrderRepository.findById(id).orElseThrow(() -> new InexistentInboundOrderException("Inboud Order " + id + " does not exists!"));
    }
}
