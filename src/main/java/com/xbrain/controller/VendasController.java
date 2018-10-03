package com.xbrain.controller;

import com.xbrain.model.Venda;
import com.xbrain.repository.VendaJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendaJpaRepository vendaJpaRepository;

    // Buscar vendas
    @GetMapping
    public List<Venda> buscar() {
        return vendaJpaRepository.findAll();
    }

    // Criar uma venda
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venda salvar(@RequestBody final Venda venda){
        return vendaJpaRepository.save(venda);
    }

    // Atualizar uma venda
    @PutMapping("/{id}")
    public Venda atualizar(@PathVariable("id") Long id, @RequestBody final Venda venda) {
        venda.setId(id);
        return vendaJpaRepository.save(venda);
    }

    // Remover uma venda
    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Long id) {
        vendaJpaRepository.deleteById(id);
    }
}
