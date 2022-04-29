package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class ProductByBatchResponseImpl implements ProductByBatchResponse {

    private BigInteger id;
    private Double height;
    private String name;
    private Double price;
    private Double width;
    private BigInteger batchId;
    private String expirationDate;
    private Integer currentQuantity;

}
