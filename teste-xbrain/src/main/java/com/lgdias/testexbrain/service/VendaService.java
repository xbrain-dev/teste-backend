package com.lgdias.testexbrain.service;

import com.lgdias.testexbrain.exception.VendedorNaoEncontradoException;
import com.lgdias.testexbrain.model.Venda;
import java.util.List;

public interface VendaService {

  Double calcularMediaVendas(List<Venda> vendas, long dias);

  Venda salvar(Venda venda) throws VendedorNaoEncontradoException;
}
