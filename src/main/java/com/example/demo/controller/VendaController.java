package com.example.demo.controller;

import com.example.demo.dto.VendedorDTO;
import com.example.demo.exceptions.DatasInvalidasException;
import com.example.demo.exceptions.VendaSemVendedorException;
import com.example.demo.model.Venda;
import com.example.demo.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    /**
     * Recupera todas as vendas do repository.
     *
     * @return
     */
    @GetMapping
    public List<Venda> getAllVendas() {
      return vendaRepository.findAll();
    }

    /**
     * Recupera uma venda pelo id da venda.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Venda> getVenda(@PathVariable("id") Long id) {
      return vendaRepository.findById(id);
    }


    /**
     * Salva a venda no repository.
     *
     * @param venda
     * @return
     */
    @PostMapping
    public Venda saveVenda(@RequestBody Venda venda) throws VendaSemVendedorException {
      if(venda.getVendedor() == null || venda.getVendedor().getId() == null) {
        throw new VendaSemVendedorException("Essa venda não possui vendedor.");
      }
      return vendaRepository.save(venda);
    }

    /**
     * Atualiza a venda pelo id da venda e recebe o novo objeto atualizado.
     *
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
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteVenda(@PathVariable("id") Long id) {
      vendaRepository.deleteById(id);
    }

    /**
     * O método irá recuperar todas as vendas feitas entre as datas dataStart e dataEnd,
     * após recuperar irá fazer uma média das vendas dos vendedores e ordenar em ordem decrescente de média.
     * @param dataStart
     * @param dataEnd
     * @param maxRanking
     * @return
     */
    @GetMapping("/ranking")
    public Stream<VendedorDTO> rankingDezMelhores(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataStart, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataEnd, @RequestParam Long maxRanking) throws DatasInvalidasException {
      if(dataStart.isAfter(dataEnd)) {
        throw new DatasInvalidasException("A datas não são válidas.");
      }
      List<Venda> vendas = vendaRepository.findAllByDataVendaBetween(dataStart, dataEnd);
      Period periodo = Period.between(dataStart, dataEnd);
      float populacao = periodo.getDays();
      List<VendedorDTO> vendedores = vendas.stream()
        .collect(Collectors.groupingBy(Venda::getVendedor))
        .entrySet()
        .stream()
        .map(entry -> {
          Double valorTotal = 0.0;
          Double media = 0.0;
          for (Venda venda : entry.getValue()) {
            valorTotal += venda.getValor();
          }
          media = valorTotal / populacao;
          return new VendedorDTO(entry.getKey(), media);
         }).collect(Collectors.toList());

        return vendedores.stream()
          .sorted(Comparator.comparing(VendedorDTO::getValor).reversed())
          .limit(maxRanking);
    }
}
