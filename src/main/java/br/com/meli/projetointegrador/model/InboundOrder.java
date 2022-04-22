package br.com.meli.projetointegrador.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="InboundOrder")
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate orderDate;

    // Um representante vendeu vários lotes

    @OneToMany(mappedBy = "InboundOrder", cascade = CascadeType.ALL)
    private List<Batch> itens = new ArrayList<Batch>();

    private List<StockManager> itens = new ArrayList<StockManager>();

}
