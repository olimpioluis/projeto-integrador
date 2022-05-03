package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductDTOiImpl implements ProductDTOi {
    private Long id;
    private String name;
    private int quantity;
}
