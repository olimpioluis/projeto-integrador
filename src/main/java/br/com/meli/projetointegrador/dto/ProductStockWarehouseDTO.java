package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductStockWarehouseDTO {
    private Long productId;
    private List<QuantityByWarehouseDTO> quantityByWarehouseList;
}
