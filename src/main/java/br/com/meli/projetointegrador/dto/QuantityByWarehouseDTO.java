package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class QuantityByWarehouseDTO {
    private BigDecimal totalQuantity;
    private BigInteger warehouseCode;

    @Override
    public String toString() {
        return "QuantityByWarehouseDTO{" +
                "totalQuantity=" + totalQuantity +
                ", warehouseCode=" + warehouseCode +
                '}';
    }
}
