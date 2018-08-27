package com.example.demo.dto;

import com.example.demo.model.Vendedor;

public class VendedorDTO {

  Vendedor vendedor;

  Double valor;

  public VendedorDTO() {

  }

  public VendedorDTO(Vendedor vendedor, Double valor) {
    this.vendedor = vendedor;
    this.valor = valor;
  }

  public Vendedor getVendedor() {
    return vendedor;
  }

  public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }
}
