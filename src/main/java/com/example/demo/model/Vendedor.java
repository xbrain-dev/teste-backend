package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Vendedor {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "vendedor")
    @JsonIgnore
    private List<Venda> vendas;


    public Vendedor() {

    }

    public Vendedor(Long id, String nome, List<Venda> vendas) {
        this.id = id;
        this.nome = nome;
        this.vendas = vendas;
    }

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

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
