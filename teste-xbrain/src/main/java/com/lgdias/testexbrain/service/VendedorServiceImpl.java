package com.lgdias.testexbrain.service;

import com.lgdias.testexbrain.dto.VendedorRankingDTO;
import com.lgdias.testexbrain.exception.DataInvalidaException;
import com.lgdias.testexbrain.model.Venda;
import com.lgdias.testexbrain.model.Vendedor;
import com.lgdias.testexbrain.repository.VendaRepository;
import com.lgdias.testexbrain.repository.VendedorRepository;
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
  private final VendedorRepository vendedorRepository;
  private final VendaService vendaService;

  @Autowired
  public VendedorServiceImpl(VendaRepository vendaRepository, VendedorRepository vendedorRepository,
      VendaService vendaService) {

    this.vendaRepository = vendaRepository;
    this.vendedorRepository = vendedorRepository;
    this.vendaService = vendaService;
  }

  @Override
  public List<VendedorRankingDTO> recuperarRankingPorVendasNoPeriodo(Long limiteRanking,
      LocalDate dataInicial,
      LocalDate dataFinal) throws DataInvalidaException {

    if (dataInicial.isAfter(dataFinal)) {
      throw new DataInvalidaException(
          "As datas informadas devem informar um período de tempo positivo.");
    }

    Duration duration = Duration.between(dataInicial.atStartOfDay(), dataFinal.atStartOfDay());
    long diferenca = duration.toDays();

    return vendaRepository
        .findAllByDataBetween(dataInicial, dataFinal)
        .stream()
        .filter(
            venda -> !venda.getData().isBefore(dataInicial) && venda.getData().isBefore(dataFinal))
        .collect(Collectors.groupingBy(Venda::getVendedor))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(
            Entry::getKey, entry -> vendaService.calcularMediaVendas(entry.getValue(), diferenca)))
        .entrySet()
        .stream()
        .sorted(Comparator.<Entry<Vendedor, Double>>comparingDouble(Entry::getValue).reversed())
        .limit(limiteRanking)
        .map(entry -> new VendedorRankingDTO(entry.getValue(), entry.getKey()))
        .collect(Collectors.toList());
  }

  @Override
  public Vendedor salvar(Vendedor vendedor) {
    return vendedorRepository.save(vendedor);
  }
}
