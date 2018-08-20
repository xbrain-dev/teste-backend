package com.lgdias.testexbrain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Venda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate data;

  private Double valor;

  @ManyToOne
  private Vendedor vendedor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Venda venda = (Venda) o;
    return Objects.equals(getId(), venda.getId()) &&
        Objects.equals(getData(), venda.getData()) &&
        Objects.equals(getValor(), venda.getValor()) &&
        Objects.equals(getVendedor(), venda.getVendedor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getData(), getValor(), getVendedor());
  }

  public static class VendaBuilder {

    private Long id;
    private LocalDate data;
    private Double valor;
    private Vendedor vendedor;

    public VendaBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public VendaBuilder data(LocalDate data) {
      this.data = data;
      return this;
    }

    public VendaBuilder valor(Double valor) {
      this.valor = valor;
      return this;
    }

    public VendaBuilder vendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
      return this;
    }

    public Venda build() {
      Venda venda = new Venda();
      venda.setId(this.id);
      venda.setData(this.data);
      venda.setValor(this.valor);
      venda.setVendedor(this.vendedor);
      return venda;
    }
  }
}
