package com.lgdias.testexbrain.service;

import com.lgdias.testexbrain.dto.VendedorRankingDTO;
import com.lgdias.testexbrain.exception.DataInvalidaException;
import com.lgdias.testexbrain.model.Venda;
import com.lgdias.testexbrain.model.Vendedor;
import com.lgdias.testexbrain.repository.VendaRepository;
import com.lgdias.testexbrain.util.DateUtil;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendedorServiceImpl implements VendedorService {

  private final VendaRepository vendaRepository;
  private final VendaService vendaService;

  @Autowired
  public VendedorServiceImpl(VendaRepository vendaRepository, VendaService vendaService) {

    this.vendaRepository = vendaRepository;
    this.vendaService = vendaService;
  }

  @Override
  public List<VendedorRankingDTO> recuperarRankingPorVendasNoPeriodo(Long number,
      LocalDate initialDate,
      LocalDate finalDate) throws DataInvalidaException {

    if (initialDate.isAfter(finalDate)) {
      throw new DataInvalidaException(
          "As datas informadas devem informar um período de tempo positivo.");
    }

    Duration duration = Duration.between(initialDate.atStartOfDay(), finalDate.atStartOfDay());
    long diffDays = duration.toDays();

    return vendaRepository
        .findAllByDataBetween(DateUtil.asDate(initialDate), DateUtil.asDate(finalDate))
        .stream()
        .filter(
            venda -> !venda.getData().isBefore(initialDate) && venda.getData().isBefore(finalDate))
        .collect(Collectors.groupingBy(Venda::getVendedor))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(
            Entry::getKey, entry -> vendaService.calcularMediaVendas(entry.getValue(), diffDays)))
        .entrySet()
        .stream()
        .sorted(Comparator.<Entry<Vendedor, Double>>comparingDouble(Entry::getValue).reversed())
        .limit(number)
        .map(entry -> new VendedorRankingDTO(entry.getValue(), entry.getKey()))
        .collect(Collectors.toList());
  }
}
