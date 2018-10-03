package com.xbrain.controller;

import com.xbrain.model.Vendedor;
import com.xbrain.util.ValidadorCPF;

import com.xbrain.repository.VendedorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorJpaRepository vendedorJpaRepository;

    // Listar todos os vendedores
    @GetMapping
    public List<Vendedor> buscar() {
        return vendedorJpaRepository.findAll();
    }

    // Criar um novo vendedor
    @PostMapping
    public Vendedor salvar(@RequestBody final Vendedor vendedor) throws Exception {

        // Verificar o número do CPF informado
        if (!ValidadorCPF.validar(vendedor.getCpf())) {
            throw new Exception( "O número de CPF não foi informado corretamente.");
        }
        // Verificar se o nome foi preenchido
        if (vendedor.getNome() == null || vendedor.getCpf().isEmpty()) {
            throw new Exception( "É obrigatório informar o nome do vendedor.");
        }
        return vendedorJpaRepository.save(vendedor);
    }

    // Atualizar um vendedor
    @PutMapping(value = "/{id}")
    public Vendedor atualizar(@PathVariable("id") Long id, @RequestBody final Vendedor vendedor) {
        return vendedorJpaRepository.save(vendedor);
    }

    // Remover um vendedor
    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Long id) {
        vendedorJpaRepository.deleteById(id);
    }
}
