package com.xbrain.app.service;

import com.xbrain.app.dto.VendedorDTO;
import com.xbrain.app.exception.DataInvalidaException;
import com.xbrain.app.exception.VendedorNaoEncontradoException;
import com.xbrain.app.model.Vendedor;
import com.xbrain.app.repository.VendedorRepository;
import com.xbrain.app.util.mapper.MapperUtil;
import com.xbrain.app.util.matcher.TypeExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final MapperUtil mapperUtil;

    // - LOGICA PARA A REALIZACAO DO GET DE TODOS OS VENDEDORES JÁ COM PAGINAÇÃO;
    public Page<VendedorDTO> getAll(VendedorDTO vendedorDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(vendedorDTO, Vendedor.class),
                new TypeExampleMatcher().exampleMatcherMatchingAny());

        return vendedorRepository.findAll(example, pageable).map(Vendedor -> mapperUtil.mapTo(Vendedor, VendedorDTO.class));
    }

    // - LOGICA PARA REALIZACAO DO GET BY ID DO VENDEDOR;
    public VendedorDTO findById(Long id) {
        return mapperUtil.mapTo(vendedorRepository.findById(id).orElseThrow(VendedorNaoEncontradoException::new), VendedorDTO.class);
    }

    // - LOGICA PARA A REALIZAÇÃO DO GET DO RANKING;
    public List<Vendedor> ranking(LocalDate fromDate, LocalDate toDate) {

        if (fromDate.isAfter(LocalDate.now()))
            throw new DataInvalidaException("Data não é valida");

        Period period = Period.between(fromDate, toDate);
        BigDecimal days = BigDecimal.valueOf(period.getDays());

        List<Vendedor> vendedors = vendedorRepository.findAllBySellDateBetween(fromDate, toDate);

        vendedors.removeIf(v -> v.getVendas().isEmpty());
        for(Vendedor v : vendedors)
            v.setMediaDeVendas(v.getValorTotalDeVendas().divide(days, BigDecimal.ROUND_UP));

        return vendedors;
    }

    // - LOGICA PARA A REALIZACAO DO POST DO VENDEDOR;
    public VendedorDTO saveVendedor(VendedorDTO vendedorDTO) {
        vendedorRepository.save(mapperUtil.mapTo(vendedorDTO, Vendedor.class));
        return vendedorDTO;
    }

    // - LOGICA PARA A REALIZACAO DO UPDATE DO VENDEDOR;
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

}