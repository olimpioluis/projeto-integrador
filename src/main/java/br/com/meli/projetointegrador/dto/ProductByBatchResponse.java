package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ProductByBatchResponse {
    Long getId();
    String getName();
    Double getPrice();
    Double getWidth();
    Double getHeight();
    Long getBatchId();
    LocalDate getExpirationDate();
    Long getCurrentQuantity();
}
