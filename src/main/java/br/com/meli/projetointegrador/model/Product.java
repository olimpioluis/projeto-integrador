package br.com.meli.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private Double price;
    private Double width;
    private Double height;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

//    @OneToOne
//    private Advertisement advertisement;

}
