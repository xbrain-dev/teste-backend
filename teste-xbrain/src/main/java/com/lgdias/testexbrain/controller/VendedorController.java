package com.lgdias.testexbrain.controller;

import com.lgdias.testexbrain.dto.VendedorRankingDTO;
import com.lgdias.testexbrain.exception.DataInvalidaException;
import com.lgdias.testexbrain.model.Vendedor;
import com.lgdias.testexbrain.service.VendedorService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {

  @Autowired
  private VendedorService vendedorService;

  @GetMapping("/ranking")
  public List<VendedorRankingDTO> recuperarRankingTop10PorVendasNoPeriodo(
      @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
      @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate,
      @RequestParam("quantidade") Long quantidade)
      throws DataInvalidaException {
    return vendedorService.recuperarRankingPorVendasNoPeriodo(quantidade, initialDate, finalDate);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Vendedor salvar(@RequestBody final Vendedor vendedor) {
    return vendedorService.salvar(vendedor);
  }
}
