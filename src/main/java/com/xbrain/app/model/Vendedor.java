package com.xbrain.app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendedor", insertable = false, length = 50)
    private String nome;

    @Column(name = "vendedor_cpf", insertable = false, unique = true, length = 11)
    private String cpf;

}
