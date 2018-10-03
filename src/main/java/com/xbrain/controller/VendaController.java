package com.xbrain.controller;

import com.xbrain.model.Venda;
import com.xbrain.repository.VendaJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaJpaRepository vendaJpaRepository;

    // Buscar vendas
    @GetMapping
    public List<Venda> buscar() {
        return vendaJpaRepository.findAll();
    }

    // Criar uma venda
    @PostMapping
    public Venda salvar(@RequestBody final Venda venda) {
        return vendaJpaRepository.save(venda);
    }

    // Atualizar uma venda
    @PutMapping
    public Venda atualizar(@RequestBody final Venda venda) {
        return vendaJpaRepository.save(venda);
    }

    // Remover uma venda
    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Long id) {
        vendaJpaRepository.deleteById(id);
    }
}
