package br.com.meli.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private char genre;

    @OneToOne(cascade = CascadeType.ALL)
    private Seller seller;

    @OneToOne(cascade = CascadeType.ALL)
    private StockManager stockManager;

}
