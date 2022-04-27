package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.*;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();;
        price = product.getPrice();
    }

    public static List<ProductDTO> converter(@NotNull List<Product> productList) {
        return productList.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}

