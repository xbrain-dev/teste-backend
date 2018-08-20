package com.lgdias.testexbrain.service;

import com.lgdias.testexbrain.exception.VendedorNaoEncontradoException;
import com.lgdias.testexbrain.model.Venda;
import com.lgdias.testexbrain.repository.VendaRepository;
import com.lgdias.testexbrain.repository.VendedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaServiceImpl implements VendaService {

  @Autowired
  private VendaRepository vendaRepository;

  @Autowired
  private VendedorRepository vendedorRepository;

  @Override
  public Double calcularMediaVendas(List<Venda> vendas, long dias) {

    Double total = vendas
        .stream()
        .map(Venda::getValor)
        .reduce(0.0, Double::sum);

    return total / dias;
  }

  @Override
  public Venda salvar(Venda venda) throws VendedorNaoEncontradoException {
    if (venda.getVendedor() == null) {
      throw new VendedorNaoEncontradoException(
          "Não foi encontrado nenhum vendedor com o ID informado.");
    }

    vendedorRepository
        .findByIdAndNome(venda.getVendedor().getId(), venda.getVendedor().getNome())
        .orElseThrow(() -> new VendedorNaoEncontradoException(
            "Não foi encontrado nenhum vendedor com o ID informado."));

    return vendaRepository.save(venda);
  }
}
