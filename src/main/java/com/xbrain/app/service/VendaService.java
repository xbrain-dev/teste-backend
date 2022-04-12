package com.xbrain.app.service;

import com.xbrain.app.dto.VendaDTO;
import com.xbrain.app.exception.DataInvalidaException;
import com.xbrain.app.exception.VendaNaoEncontradaException;
import com.xbrain.app.model.Venda;
import com.xbrain.app.repository.VendaRepository;
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
import java.util.Date;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final MapperUtil mapperUtil;

    // - Faz o get de todas as vendas já com a paginação;
    public Page<VendaDTO> getAll(VendaDTO vendaDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(vendaDTO, Venda.class),
                new TypeExampleMatcher().exampleMatcherMatchingAny());

        return vendaRepository.findAll(example, pageable).map(Venda -> mapperUtil.mapTo(Venda, VendaDTO.class));
    }

    public Venda findById(Long id) {
        return vendaRepository.findById(id).orElseThrow(VendaNaoEncontradaException::new);
    }

    public Venda save(VendaDTO vendaDTO) {
        // - Fazendo o set da data no post para que não ocorra o set caso a entidade seja instanciada em outro momento;
        vendaDTO.setDataVenda(LocalDate.now());
        BigDecimal total = vendaDTO.getQuantidade().multiply(vendaDTO.getValor());
        vendaDTO.setTotal(total);
        return vendaRepository.save(mapperUtil.mapTo(vendaDTO, Venda.class));
    }

    public VendaDTO update(Long id,VendaDTO vendaDTO) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(VendaNaoEncontradaException::new);
        BeanUtils.copyProperties(vendaDTO, venda, "id");

        return mapperUtil.mapTo(vendaRepository.save(venda), VendaDTO.class);
    }

    public String delete(Long id) {
        vendaRepository.delete(vendaRepository.findById(id)
                .orElseThrow(VendaNaoEncontradaException::new));

        return "Venda Excluida";
    }
}
