package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;

    public ProductDTO convert(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        return this;
    }
}

