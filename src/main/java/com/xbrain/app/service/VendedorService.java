package com.xbrain.app.service;

import com.xbrain.app.dto.VendaDTO;
import com.xbrain.app.dto.VendedorDTO;
import com.xbrain.app.exception.DataInvalidaException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final MapperUtil mapperUtil;

    public Page<VendedorDTO> getAll(VendedorDTO vendedorDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(vendedorDTO, Vendedor.class),
                new TypeExampleMatcher().exampleMatcherMatchingAny());

        return vendedorRepository.findAll(example, pageable).map(Vendedor -> mapperUtil.mapTo(Vendedor, VendedorDTO.class));
    }

    public Vendedor findById(Long id) {
        return vendedorRepository.findById(id).orElseThrow(VendedorNaoEncontradoException::new);
    }

    public Vendedor saveVendedor(VendedorDTO vendedorDTO) {
        return vendedorRepository.save(mapperUtil.mapTo(vendedorDTO, Vendedor.class));
    }

    public VendedorDTO updateVendedor(Long id, VendedorDTO vendedorDTO) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(VendedorNaoEncontradoException::new);
        BeanUtils.copyProperties(vendedorDTO, vendedor, "id");

        return mapperUtil.mapTo(vendedorRepository.save(vendedor), VendedorDTO.class);
    }

    public String deleteVendedor(Long id) {
        vendedorRepository.delete(vendedorRepository.findById(id)
                .orElseThrow(VendedorNaoEncontradoException::new));

        return "Vendedor Excluido";
    }

//    public List<Venda> rankingBestSellers(LocalDate fromDate,
//                                                LocalDate toDate) {
//
//
//    }
}
