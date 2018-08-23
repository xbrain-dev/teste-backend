package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataVenda = LocalDate.now();

    private Double valor;

    @ManyToOne
    @NotNull
    private Vendedor vendedor;

    public Venda() {

    }

    public Venda(Long id, LocalDate dataVenda, Double valor, Vendedor vendedor) {
      this.id = id;
      this.dataVenda = dataVenda;
      this.valor = valor;
      this.vendedor = vendedor;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public LocalDate getDataVenda() {
      return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
      this.dataVenda = dataVenda;
    }

    public Double getValor() {
      return valor;
    }

    public void setValor(Double valor) {
      this.valor = valor;
    }

    public Vendedor getVendedor() {
      return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
    }

}
