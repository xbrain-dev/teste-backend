package com.lgdias.testexbrain.service;

import com.lgdias.testexbrain.dto.VendedorRankingDTO;
import com.lgdias.testexbrain.exception.DataInvalidaException;
import java.time.LocalDate;
import java.util.List;

public interface VendedorService {

  List<VendedorRankingDTO> recuperarRankingPorVendasNoPeriodo(Long number, LocalDate initialDate, LocalDate finalDate)
      throws DataInvalidaException;
}
