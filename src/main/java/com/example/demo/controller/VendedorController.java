package com.example.demo.controller;

import com.example.demo.model.Vendedor;
import com.example.demo.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    public VendedorRepository vendedorRepository;

    /**
     * Recupera todas as vendas do repository.
     * @return
     */
    @GetMapping
    public List<Vendedor> getAllVendedor() {
        List<Vendedor> vendedores =  vendedorRepository.findAll();
        return  vendedores;
    }

    /**
     * Recupera o vendedor pelo id do vendedor.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Vendedor> getVendedor(@PathVariable("id") Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedor;
    }

    /**
     * Salva o vendedor no repository.
     * @param vendedor
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor saveVendedor(@RequestBody Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    /**
     * Atualiza o objeto pelo id no repository.
     * @param id
     * @param vendedor
     * @return
     */
    @PutMapping("/{id}")
    public Vendedor updateVendedor(@PathVariable("id") Long id, @RequestBody Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    /**
     * Deleta a venda do repository pelo id.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteVendedor(@PathVariable("id") Long id) {
        vendedorRepository.deleteById(id);
    }

}
