package com.xbrain.app.service;

import com.xbrain.app.dto.VendaDTO;
import com.xbrain.app.dto.VendedorDTO;
import com.xbrain.app.exception.DataInvalidaException;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final VendaRepository vendaRepository;
    private final MapperUtil mapperUtil;

    // - LOGICA PARA A REALIZACAO DO GET DE TODOS OS VENDEDORES;
    public Page<VendedorDTO> getAll(VendedorDTO vendedorDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(vendedorDTO, Vendedor.class),
                new TypeExampleMatcher().exampleMatcherMatchingAny());

        return vendedorRepository.findAll(example, pageable).map(Vendedor -> mapperUtil.mapTo(Vendedor, VendedorDTO.class));
    }

    // - LOGICA PARA REALIZACAO DO GET BY ID DO VENDEDOR;
    public Vendedor findById(Long id) {
        return vendedorRepository.findById(id).orElseThrow(VendedorNaoEncontradoException::new);
    }

    // - LOGICA PARA A REALIZACAO DO POST DO VENDEDOR;
    public Vendedor saveVendedor(VendedorDTO vendedorDTO) {
        return vendedorRepository.save(mapperUtil.mapTo(vendedorDTO, Vendedor.class));
    }

    // - LOGICA PARA A REALIZACAI DO UPDATE DO VENDEDOR;
    public VendedorDTO updateVendedor(Long id, VendedorDTO vendedorDTO) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(VendedorNaoEncontradoException::new);
        BeanUtils.copyProperties(vendedorDTO, vendedor, "id");

        return mapperUtil.mapTo(vendedorRepository.save(vendedor), VendedorDTO.class);
    }

    // - LOGICA PARA A REALIZACAO DO DELETE DO VENDEDOR;
    public String deleteVendedor(Long id) {
        vendedorRepository.delete(vendedorRepository.findById(id)
                .orElseThrow(VendedorNaoEncontradoException::new));

        return "Vendedor Excluido";
    }


//  TODO - Continuar logica do ranking e realizar o retorno dos 10 melhores e realizar a media diaria;

//    // - Logica do ranking - usando fromDate e toDate como parametros para buscar no banco;
//    public List<VendedorDTO> ranking(LocalDate fromDate, LocalDate toDate) {
//
//        boolean wrongDate = fromDate.isAfter(toDate);
//        if(wrongDate) // - Verificando se as datas estão corretas;
//           throw new DataInvalidaException("Data invalida");
//
//        Period period = Period.between(fromDate, toDate);
//        int days = period.getDays();
//
//        List<Venda> sellList = vendaRepository.findAllByDataVendaBetween(fromDate, toDate);
//
//        if(sellList.isEmpty())
//            throw new VendaNaoEncontradaException();
//    }
}
