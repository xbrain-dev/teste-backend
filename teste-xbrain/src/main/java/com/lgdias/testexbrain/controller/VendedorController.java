package com.lgdias.testexbrain.controller;

import com.lgdias.testexbrain.dto.VendedorRankingDTO;
import com.lgdias.testexbrain.exception.DataInvalidaException;
import com.lgdias.testexbrain.service.VendedorService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {

  @Autowired
  private VendedorService vendedorService;

  @GetMapping("/ranking")
  public List<VendedorRankingDTO> recuperarRankingTop10PorVendasNoPeriodo(
      @RequestParam("dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate initialDate,
      @RequestParam("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate finalDate)
      throws DataInvalidaException {
    return vendedorService.recuperarRankingPorVendasNoPeriodo(10L, initialDate, finalDate);
  }
}
