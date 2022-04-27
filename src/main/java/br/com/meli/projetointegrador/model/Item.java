package br.com.meli.projetointegrador.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Advertisement advertisement;

    @ManyToOne
    private Cart cart;

    private Integer quantity;

}
