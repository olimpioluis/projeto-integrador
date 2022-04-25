package br.com.meli.projetointegrador.model;

import lombok.*;

import javax.persistence.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sector")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    private Double size;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "sector",cascade = CascadeType.ALL)
    private List<Batch> batchList;
}



