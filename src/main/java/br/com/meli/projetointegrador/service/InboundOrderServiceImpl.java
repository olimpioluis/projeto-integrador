package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentInboundOrderException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.repository.*;
import br.com.meli.projetointegrador.validator.*;
import lombok.AllArgsConstructor;
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
        List<Validator> validators = Arrays.asList(
                new WarehouseExists(inboundOrder.getSection().getWarehouse().getId(), warehouseService),
                new SectionExists(sectionService, inboundOrder.getSection().getId()),
                new SectionAvailableSpace(sectionService, inboundOrder.getSection().getId(), inboundOrder.getBatchList()),
                new SectionMatchWithWarehouse(inboundOrder.getSection(),sectionService)
//                new StockManagerNotInWarehouse(stockManagerService, inboundOrder.getStockManager().getId(), inboundOrder.getSection().getWarehouse().getId()),

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

        List<Validator> validators = Arrays.asList(
                new SectionAvailableSpace(sectionService, inboundOrder.getSection().getId(), newBatches)
//                new StockManagerNotInWarehouse(stockManagerService, inboundOrder.getStockManager().getId(), inboundOrder.getSection().getWarehouse().getId()),
        );

        validators.forEach(Validator::validate);

        inboundOrderRepository.save(inboundOrder);

        List<Batch> batchesCreated = batchService.save(inboundOrder.getBatchList());

        sectionService.updateCurrentSize(newBatches.size(), inboundOrder.getSection().getId(), false);

        return batchesCreated;
    }

    @Override
    public InboundOrder findById(Long id) {
        return inboundOrderRepository.findById(id).orElseThrow(() -> new InexistentInboundOrderException("Inboud Order" + id + " does not exists!"));
    }
}
