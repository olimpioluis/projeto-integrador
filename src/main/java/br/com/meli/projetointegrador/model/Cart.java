package br.com.meli.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    @ManyToOne
    private Customer customer;

    private BigDecimal totalCart;

    @OneToOne
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Item> items;

}
