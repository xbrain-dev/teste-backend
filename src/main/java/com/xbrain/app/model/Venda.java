package com.xbrain.app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_venda", insertable = false, updatable = false)
    private LocalDateTime dataVenda;

    @Column(name = "valor", insertable = false, updatable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Vendedor vendedor;
}
