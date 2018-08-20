package com.lgdias.testexbrain.controller;

import com.lgdias.testexbrain.exception.VendedorNaoEncontradoException;
import com.lgdias.testexbrain.model.Venda;
import com.lgdias.testexbrain.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(path = "/vendas")
public class VendaController {

  @Autowired
  private VendaService vendaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Venda realizarVenda(@RequestBody final Venda venda) throws VendedorNaoEncontradoException {
    return vendaService.salvar(venda);
  }
}
