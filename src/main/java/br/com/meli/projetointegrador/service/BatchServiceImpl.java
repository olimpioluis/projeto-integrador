package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentBatchException;
import br.com.meli.projetointegrador.exception.NotFoundProductException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BatchServiceImpl implements BatchService {

    private BatchRepository batchRepository;
    private SectionService sectionService;

    @Override
    public List<Batch> save(List<Batch> batches) {
        return batchRepository.saveAll(batches);
    }

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id).orElseThrow(() -> new InexistentBatchException("Batch " + id + " does not exists!"));
    }

    @Override
    public List<Object[]> getBatchStockByWarehouse(Long productId) {
        List<Object[]> result = batchRepository.groupAllByWarehouseId(productId);

        if (result.size() == 0) {
            throw new NotFoundProductException("The Product ID: " + productId + " does not exists!");
        }
        return result;
    }

    public List<Batch> getBatchesWithExpirationDateGreaterThan3Weeks(Long productId) {
        List<Batch> batches = findAllBatchesByProduct(productId);

        return batches.stream().filter(batch -> ChronoUnit.DAYS.between(LocalDate.now(), batch.getExpirationDate()) > 21).collect(Collectors.toList());
    }

    @Override
    public List<Batch> findAllBatchesByProduct(Long productId) {
        return batchRepository.findAllByProductId(productId);
    }

    @Override
    public void decreaseBatch(List<Batch> batches, Integer remainingQuantity) {

        for (Batch batch:batches){

            if (remainingQuantity == 0) break;

            else if(batch.getCurrentQuantity().equals(remainingQuantity)){
                remainingQuantity = 0;
                batch.setCurrentQuantity(0);
                sectionService.updateCurrentSize(1, batch.getSection().getId(), true);
            }
            else if(batch.getCurrentQuantity() > remainingQuantity){
                batch.setCurrentQuantity(batch.getCurrentQuantity() - remainingQuantity);
                remainingQuantity = 0;
            }
            else{
                remainingQuantity -= batch.getCurrentQuantity();
                batch.setCurrentQuantity(0);
                sectionService.updateCurrentSize(1, batch.getSection().getId(), true);
            }
            batchRepository.save(batch);
        }
    }

    @Override
    public void takeOutProducts(List<Item> items) {
        items.forEach(item -> {
            List<Batch> batches = getBatchesWithExpirationDateGreaterThan3Weeks(item.getAdvertisement().getId());
            batches.sort(Comparator.comparing(Batch::getCurrentQuantity));
            decreaseBatch(batches, item.getQuantity());
        });
    }

}
