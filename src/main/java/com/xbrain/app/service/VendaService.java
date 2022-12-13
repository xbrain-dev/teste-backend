package com.xbrain.app.service;

import com.xbrain.app.dto.VendaDTO;
import com.xbrain.app.exception.VendaNaoEncontradaException;
import com.xbrain.app.exception.VendedorNaoEncontradoException;
import com.xbrain.app.model.Venda;
import com.xbrain.app.model.Vendedor;
import com.xbrain.app.repository.VendaRepository;
import com.xbrain.app.repository.VendedorRepository;
import com.xbrain.app.util.mapper.MapperUtil;
import com.xbrain.app.util.matcher.TypeExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final VendedorRepository vendedorRepository;
    private final MapperUtil mapperUtil;

    // - LOGICA PARA A REALIZAÇÃO DO GET DE TODAS AS VENDAS JÁ COM PAGINAÇÃO
    public Page<VendaDTO> getAll(VendaDTO vendaDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(vendaDTO, Venda.class),
                new TypeExampleMatcher().exampleMatcherMatchingAny());

        return vendaRepository.findAll(example, pageable).map(Venda -> mapperUtil.mapTo(Venda, VendaDTO.class));
    }

    // - LOGICA PARA A REALIZAÇÃO DO GET BY ID DA VENDA
    public VendaDTO findById(Long id) {
        return mapperUtil.mapTo(vendaRepository.findById(id)
                .orElseThrow(VendaNaoEncontradaException::new), VendaDTO.class);
    }

    // - REALIZAÇÃO PARA A REALIZAÇÃO DO SAVE DA VENDA
    public VendaDTO save(VendaDTO vendaDTO) {
        if(vendaDTO.getDataVenda() == null)
            vendaDTO.setDataVenda(LocalDate.now());

        // - Realizando o calculo do valor total;
        vendaDTO.setValorTotal(vendaDTO.getValor().multiply(vendaDTO.getQuantidade()));

        vendaRepository.save(mapperUtil.mapTo(vendaDTO, Venda.class));
        Vendedor v = vendedorRepository.findById(vendaDTO.getIdVendedor())
                .orElseThrow(VendedorNaoEncontradoException::new);

        List<Venda> all = v.getVendas();
        List<BigDecimal> allValues = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;

        for(Venda venda : all)
            allValues.add(venda.getValorTotal());
        for(BigDecimal venda : allValues)
            sum = sum.add(venda);

        v.setValorTotalDeVendas(sum);
        v.setSellDate(LocalDate.now());
        vendedorRepository.save(v);

        return vendaDTO;
    }

    // - LOGICA PARA A REALIZAÇÃO DO UPDATE DA VENDA
    public VendaDTO update(Long id,VendaDTO vendaDTO) {
        Vendedor v = vendedorRepository.findById(vendaDTO.getIdVendedor())
                .orElseThrow(VendedorNaoEncontradoException::new);
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(VendaNaoEncontradaException::new);

        // - Realizando os calculos para a atualizar os dados do vendedor diante das mudanças da venda.
        v.setValorTotalDeVendas(v.getValorTotalDeVendas().subtract(venda.getValor()));
        v.setValorTotalDeVendas(v.getValorTotalDeVendas().add(vendaDTO.getValor()));
        vendaDTO.setValorTotal(vendaDTO.getValor().multiply(vendaDTO.getQuantidade()));
        vendaDTO.setDataVenda(LocalDate.now());
        BeanUtils.copyProperties(vendaDTO, venda, "id");

        vendedorRepository.save(v);
        return mapperUtil.mapTo(vendaRepository.save(venda), VendaDTO.class);
    }

    // - LOGICA PARA A REALIZAÇÃO DO DELETE DA VENDA
    public String delete(Long id) {
        Venda v = vendaRepository.findById(id).orElseThrow(VendaNaoEncontradaException::new);
        Vendedor v1 = v.getVendedor();

        // - Atualizando o valor total no usuario após a exclusão do produto;
        v1.setValorTotalDeVendas(v1.getValorTotalDeVendas().subtract(v.getValor()));
        vendedorRepository.save(v1);
        vendaRepository.deleteById(id);
        return "Venda Excluida";
    }
}
