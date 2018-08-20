package com.lgdias.testexbrain.service;

import com.lgdias.testexbrain.dto.VendedorRankingDTO;
import com.lgdias.testexbrain.exception.DataInvalidaException;
import com.lgdias.testexbrain.model.Vendedor;
import java.time.LocalDate;
import java.util.List;

public interface VendedorService {

  /**
   * Recupera um ranking dos vendedores com melhor média diária de vendas no período informado.
   *
   * @param limiteRanking Número de vendedeores para limitar
   * @param dataInicial Data de início do período
   * @param dataFinal Data de conclusão do período
   * @return Lista de vendedores juntamente com sua média de vendas diária
   */
  List<VendedorRankingDTO> recuperarRankingPorVendasNoPeriodo(Long limiteRanking,
      LocalDate dataInicial, LocalDate dataFinal)
      throws DataInvalidaException;

  /**
   * Cadastra um novo vendedor
   *
   * @param vendedor Novo vendedor
   * @return Vendedor cadastrado
   */
  Vendedor salvar(Vendedor vendedor);
}
