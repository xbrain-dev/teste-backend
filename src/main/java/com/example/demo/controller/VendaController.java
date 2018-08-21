package com.example.demo.controller;

import com.example.demo.model.Venda;
import com.example.demo.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    /**
     * Recupera todas as vendas do repository.
     * @return
     */
    @GetMapping
    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }

    /**
     * Recupera uma venda pelo id da venda.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Venda> getVenda(@PathVariable("id") Long id) {
        return vendaRepository.findById(id);
    }


    /**
     * Salva a venda no repository.
     * @param venda
     * @return
     */
    @PostMapping
    public Venda saveVenda(@RequestBody Venda venda) {
        return vendaRepository.save(venda);
    }

    /**
     * Atualiza a venda pelo id da venda e recebe o novo objeto atualizado.
     * @param id
     * @param venda
     * @return
     */
    @PutMapping("/{id}")
    public Venda updateVenda(@PathVariable("id") Long id, @RequestBody Venda venda) {
        return vendaRepository.save(venda);
    }

    /**
     * Deleta a venda pelo id da venda.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteVenda(@PathVariable("id") Long id) {
        vendaRepository.deleteById(id);
    }

}
