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
    private String nome;
    private BigDecimal quantidade;
    private LocalDate dataVenda;
    private BigDecimal valor;
    private BigDecimal total;

    @Column(name = "vendedor_id")
    private Long idVendedor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Vendedor vendedor;

    @PrePersist
    private void prePersist() {
        if(this.vendedor != null && this.idVendedor == null)
            this.idVendedor = this.vendedor.getId();
    }
}
