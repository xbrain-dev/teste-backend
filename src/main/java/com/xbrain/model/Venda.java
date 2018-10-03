package com.xbrain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Venda {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Vendedor vendedor;
    private Timestamp dataHora;
    private BigDecimal valor;

    public Venda() {}

    public Venda(Long id, Timestamp dataHora, BigDecimal valor, Vendedor vendedor) {
        this.id = id;
        this.dataHora = dataHora;
        this.valor = valor;
        this.vendedor = vendedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
