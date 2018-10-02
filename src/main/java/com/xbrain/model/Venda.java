package com.xbrain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Currency;

@Entity
public class Venda {

    @Id
    @GeneratedValue
    public Long id;
    public Timestamp dataHoraVenda;
    public Currency valor;
    public Long vendedorId;

    @Column(length = 200)
    public String vendedorNome;

    public Venda() {}

    public Venda(Long id, Timestamp dataHoraVenda, Currency valor, Long vendedorId, String vendedorNome) {
        this.id = id;
        this.dataHoraVenda = dataHoraVenda;
        this.valor = valor;
        this.vendedorId = vendedorId;
        this.vendedorNome = vendedorNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDataHoraVenda() {
        return dataHoraVenda;
    }

    public void setDataHoraVenda(Timestamp dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    public Currency getValor() {
        return valor;
    }

    public void setValor(Currency valor) {
        this.valor = valor;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public String getVendedorNome() {
        return vendedorNome;
    }

    public void setVendedorNome(String vendedorNome) {
        this.vendedorNome = vendedorNome;
    }
}
