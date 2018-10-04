package com.xbrain.dto;

import java.math.BigInteger;
import java.time.LocalDate;

public class VendedorResumoDTO {

    private BigInteger id;
    private String nome;
    private String cpf;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private BigInteger quantidadeVendas;
    private Double valorMedioDiario;
    private Double valorTotalPeriodo;

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

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public BigInteger getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(BigInteger quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }

    public Double getValorMedioDiario() {
        return valorMedioDiario;
    }

    public void setValorMedioDiario(Double valorMedioDiario) {
        this.valorMedioDiario = valorMedioDiario;
    }

    public Double getValorTotalPeriodo() {
        return valorTotalPeriodo;
    }

    public void setValorTotalPeriodo(Double valorTotalPeriodo) {
        this.valorTotalPeriodo = valorTotalPeriodo;
    }
}
