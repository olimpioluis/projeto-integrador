package br.com.meli.projetointegrador.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectionDTO {
    @NotNull(message = "SectionCode missing.")
    private Long sectionCode;
    @NotNull(message = "Warehouse missing.")
    private Long warehouseCode;
}
