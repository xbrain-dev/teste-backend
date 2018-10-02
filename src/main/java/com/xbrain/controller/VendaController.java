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

    @GetMapping(value = "/listar")
    public List<Venda> listarTudo() {
        return vendaJpaRepository.findAll();
    }

    @PostMapping(value = "/salvar")
    public void salvar(@RequestBody final Venda venda) {
        vendaJpaRepository.save(venda);
    }

}
