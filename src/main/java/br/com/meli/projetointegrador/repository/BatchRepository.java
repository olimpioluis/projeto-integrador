package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.dto.ProductByBatchResponse;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    Batch findBySectionId(Long sectionId);

    @Query(value = "select p.*, b.id as batchId, b.expiration_date as expirationDate, b.current_quantity as currentQuantity from batch as b , (select * from product where id=?1 ) p where b.product_id=p.id order by ?2 DESC", nativeQuery = true)
    List<ProductByBatchResponse> getAllProductThatHaveBatch(Long id, String orderBy);

}
