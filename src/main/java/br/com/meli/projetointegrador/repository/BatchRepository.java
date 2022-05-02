package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    Batch findBySectionId(Long sectionId);

    @Query(value = "select sum(b.current_quantity) as totalQuantity, s.warehouse_id as warehouseCode from batch b inner join section s on b.section_id=s.id where b.product_id=?1 group by s.warehouse_id order by totalQuantity DESC", nativeQuery = true)
    List<Object[]> groupAllByWarehouseId(Long productId);

    List<Batch> findAllByProductId(Long id);

}
