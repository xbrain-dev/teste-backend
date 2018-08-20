package com.lgdias.testexbrain.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vendedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @OneToMany(mappedBy = "vendedor")
  private List<Venda> vendas;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vendedor vendedor = (Vendedor) o;
    return Objects.equals(id, vendedor.id) &&
        Objects.equals(nome, vendedor.nome) &&
        Objects.equals(vendas, vendedor.vendas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, vendas);
  }

  public static class VendedorBuilder {

    private Long id;
    private String nome;

    public VendedorBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public VendedorBuilder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Vendedor build() {
      Vendedor vendedor = new Vendedor();
      vendedor.setId(this.id);
      vendedor.setNome(this.nome);
      return vendedor;
    }
  }
}
