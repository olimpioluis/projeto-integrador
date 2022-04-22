package br.com.meli.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Warehouse {
    @Id
    private Long id;
    private String name;

    @OneToMany
    private  StockManager stockManager;

    @OneToMany
    private Sector sector;
}
