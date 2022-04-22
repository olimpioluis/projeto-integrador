package br.com.meli.projetointegrador.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="inbound_order")
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderDate;

    @OneToMany(mappedBy = "inboundOrder", cascade = CascadeType.ALL)
    private List<Batch> batchList = new ArrayList<Batch>();

    @ManyToOne
    private StockManager stockManager;
}
