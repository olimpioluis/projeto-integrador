package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentInboundOrderException;
import br.com.meli.projetointegrador.exception.SectionMatchWithBatchCategoryException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.repository.*;
import br.com.meli.projetointegrador.validator.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class InboundOrderServiceImpl implements InboundOrderService {

    private InboundOrderRepository inboundOrderRepository;
    private BatchRepository batchRepository;
    private SectionRepository sectionRepository;
    private StockManagerRepository stockManagerRepository;
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Batch> save(InboundOrder inboundOrder) {
        List<Validator> validators = Arrays.asList(
                new SectionAvailableSpace(sectionRepository, inboundOrder.getSection().getId(), inboundOrder),
                new SectionExists(sectionRepository, inboundOrder.getSection().getId()),
//                new StockManagerNotInWarehouse(stockManagerRepository, inboundOrder.getStockManager().getId(), inboundOrder.getSection().getWarehouse().getId()),
                new WarehouseExists(inboundOrder.getSection().getWarehouse().getId(), warehouseRepository)
        );

        validators.forEach(Validator::validate);
        inboundOrder.getBatchList().forEach(batch -> new SectionMatchWithBatchCategory(batch).validate());

        InboundOrder orderCreated = inboundOrderRepository.save(inboundOrder);

        inboundOrder.getBatchList().forEach(batch -> batch.setInboundOrder(orderCreated));

        return batchRepository.saveAll(inboundOrder.getBatchList());
    }

    @Override
    public InboundOrder update(InboundOrder inboundOrder) {
        InboundOrder inboundOrderExists = inboundOrderRepository.findById(inboundOrder.getId()).orElseThrow(() -> new InexistentInboundOrderException("Inboud Order" + inboundOrder.getId() + " does not exists!"));

        return inboundOrderRepository.save(inboundOrder);
    }
}
