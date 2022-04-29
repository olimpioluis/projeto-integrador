package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public interface ProductByBatchResponse {
    BigInteger getId();
    Double getHeight();
    String getName();
    Double getPrice();
    Double getWidth();
    BigInteger getBatchId();
    String getExpirationDate();
    Integer getCurrentQuantity();
}
