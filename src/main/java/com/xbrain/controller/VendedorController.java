package com.xbrain.controller;

import com.xbrain.model.Vendedor;

import com.xbrain.repository.VendedorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorJpaRepository vendedorJpaRepository;

    @GetMapping(value = "/listar")
    public List<Vendedor> listarTudo() {
        return vendedorJpaRepository.findAll();
    }

    @PostMapping(value = "/salvar")
    public void salvar(@RequestBody final Vendedor vendedor) {
        vendedorJpaRepository.save(vendedor);
    }
}
