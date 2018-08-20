package com.lgdias.testexbrain.dto;

import com.lgdias.testexbrain.model.Vendedor;
import java.util.Objects;

public class VendedorRankingDTO {

  private Double valor;

  private Vendedor vendedor;

  public VendedorRankingDTO(Double valor, Vendedor vendedor) {
    this.valor = valor;
    this.vendedor = vendedor;
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
    VendedorRankingDTO that = (VendedorRankingDTO) o;
    return Objects.equals(valor, that.valor) &&
        Objects.equals(getVendedor(), that.getVendedor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(valor, getVendedor());
  }
}
