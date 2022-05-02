package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.dto.ProductByBatchResponse;
import br.com.meli.projetointegrador.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BatchRepository extends JpaRepository<Batch, Long> {

    Batch findBySectionId(Long sectionId);

    @Query(value = "select p.*, b.id as batchId, b.expiration_date as expirationDate, b.current_quantity as " +
            "currentQuantity from batch as b , (select * from product where id=?1 ) p where b.product_id=p.id order by batchId DESC", nativeQuery = true)
    List<ProductByBatchResponse> getAllProductThatHaveBatch(Long id);

    @Query(value = "select p.*, b.id as batchId, b.expiration_date as expirationDate, b.current_quantity as " +
            "currentQuantity from batch as b , (select * from product where id=?1 ) p where b.product_id=p.id order by expirationDate DESC", nativeQuery = true)
    List<ProductByBatchResponse> getAllProductThatHaveBatchExpiration(Long id);

    @Query(value = "select p.*, b.id as batchId, b.expiration_date as expirationDate, b.current_quantity as " +
            "currentQuantity from batch as b , (select * from product where id=?1 ) p where b.product_id=p.id order by currentQuantity DESC", nativeQuery = true)
    List<ProductByBatchResponse> getAllProductThatHaveBatchQuantity(Long id);

    @Query(value = "select sum(b.current_quantity) as totalQuantity, s.warehouse_id as warehouseCode from batch b inner join section s on b.section_id=s.id where b.product_id=?1 group by s.warehouse_id order by totalQuantity DESC", nativeQuery = true)
    List<Object[]> groupAllByWarehouseId(Long productId);

    List<Batch> findAllByProductId(Long id);


}
