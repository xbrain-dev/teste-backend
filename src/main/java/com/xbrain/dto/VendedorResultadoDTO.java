package com.xbrain.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class VendedorResultadoDTO {

    private BigInteger id;
    private String nome;
    private String cpf;
    private Date dataVendas;
    private BigInteger quantidadeVendas;
    private BigDecimal valorTotalVendas;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataVendas() {
        return dataVendas;
    }

    public void setDataVendas(Date dataVendas) {
        this.dataVendas = dataVendas;
    }

    public BigInteger getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(BigInteger quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }

    public BigDecimal getValorTotalVendas() {
        return valorTotalVendas;
    }

    public void setValorTotalVendas(BigDecimal valorTotalVendas) {
        this.valorTotalVendas = valorTotalVendas;
    }
}
