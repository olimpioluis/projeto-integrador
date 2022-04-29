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
@Builder
@Entity
@Table(name="inbound_order")
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne
    private Section section;

    @OneToMany(mappedBy = "inboundOrder", cascade = CascadeType.ALL)
    private List<Batch> batchList = new ArrayList<>();

    @ManyToOne
    private StockManager stockManager;
}
