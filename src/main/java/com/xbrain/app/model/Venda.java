package com.xbrain.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private BigDecimal quantidade;// quantidade de produtos
    private LocalDate dataVenda = LocalDate.now();
    private BigDecimal valor;// valor do produto
    private BigDecimal valorTotal; //Valor total da venda

    @Column(name = "vendedor_id")
    private Long idVendedor;

    @JsonIgnore // - Json Ignore para que no get ele não entre em loop;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Vendedor vendedor;

    @PrePersist
    private void prePersist() { // - Mapeamento duplo da entidade Vendedor;
        if(this.vendedor != null && this.idVendedor == null)
            this.idVendedor = this.vendedor.getId();
    }
}
