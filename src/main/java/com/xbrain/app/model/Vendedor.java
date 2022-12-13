package com.xbrain.app.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private BigDecimal mediaDeVendas; // - Media de vendas;
    private LocalDate sellDate; // - Data Da ultima Venda feita pelo Vendedor;
    private BigDecimal valorTotalDeVendas; // - valor total de todas as vendas feitas somado;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "vendedor")
    private List<Venda> vendas = new ArrayList<>();
}
