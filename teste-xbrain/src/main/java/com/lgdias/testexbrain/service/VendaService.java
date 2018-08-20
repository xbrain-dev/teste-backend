package com.lgdias.testexbrain.service;

import com.lgdias.testexbrain.exception.VendedorNaoEncontradoException;
import com.lgdias.testexbrain.model.Venda;
import java.util.List;

public interface VendaService {

  /**
   * Calcula a média diária de vendas.
   *
   * @param vendas Lista de vendas realizadas
   * @param dias Número de dias
   * @return A média diária de vendas
   */
  Double calcularMediaVendas(List<Venda> vendas, long dias);

  /**
   * Realiza uma nova venda.
   *
   * @param venda A venda para ser realizada
   * @return A venda concretizada
   * @throws VendedorNaoEncontradoException Se o vendedor não for encontrado
   */
  Venda salvar(Venda venda) throws VendedorNaoEncontradoException;
}
