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
    private BatchRepository batchRepository;
    private SectionRepository sectionRepository;
    private StockManagerRepository stockManagerRepository;
    private WarehouseRepository warehouseRepository;

    private SectionService sectionService;

    @Override
    public List<Batch> save(InboundOrder inboundOrder) {
        List<Validator> validators = Arrays.asList(
                new SectionAvailableSpace(sectionRepository, inboundOrder.getSection().getId(), inboundOrder.getBatchList()),
                new SectionExists(sectionRepository, inboundOrder.getSection().getId()),
                new SectionMatchWithWarehouse(inboundOrder.getSection(),sectionRepository),
                new SectionMatchWithWarehouse(inboundOrder.getSection(), sectionRepository),
//                new StockManagerNotInWarehouse(stockManagerRepository, inboundOrder.getStockManager().getId(), inboundOrder.getSection().getWarehouse().getId()),
                new WarehouseExists(inboundOrder.getSection().getWarehouse().getId(), warehouseRepository)
        );

        validators.forEach(Validator::validate);
        inboundOrder.getBatchList().forEach(batch -> new SectionMatchWithBatchCategory(batch).validate());

        InboundOrder orderCreated = inboundOrderRepository.save(inboundOrder);

        inboundOrder.getBatchList().forEach(batch -> batch.setInboundOrder(orderCreated));
        List<Batch> batchesCreated = batchRepository.saveAll(inboundOrder.getBatchList());

        sectionService.updateCurrentSize(inboundOrder.getBatchList().size(), inboundOrder.getSection().getId());

        return batchesCreated;
    }

    @Override
    public List<Batch> update(InboundOrder inboundOrder) {
        InboundOrder inboundOrderExists = findById(inboundOrder.getId());
        List<Batch> newBatches = inboundOrder.getBatchList().stream().filter(batch -> Objects.isNull(batch.getId())).collect(Collectors.toList());

        List<Validator> validators = Arrays.asList(
                new SectionAvailableSpace(sectionRepository, inboundOrder.getSection().getId(), newBatches)
//                new StockManagerNotInWarehouse(stockManagerRepository, inboundOrder.getStockManager().getId(), inboundOrder.getSection().getWarehouse().getId()),
        );

        validators.forEach(Validator::validate);

        inboundOrderRepository.save(inboundOrder);

        List<Batch> batchesCreated = batchRepository.saveAll(inboundOrder.getBatchList());

        sectionService.updateCurrentSize(newBatches.size(), inboundOrder.getSection().getId());

        return batchesCreated;
    }

    @Override
    public InboundOrder findById(Long id) {
        return inboundOrderRepository.findById(id).orElseThrow(() -> new InexistentInboundOrderException("Inboud Order" + id + " does not exists!"));
    }
}
