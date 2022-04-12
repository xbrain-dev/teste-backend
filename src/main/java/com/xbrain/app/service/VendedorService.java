package com.xbrain.app.service;

import com.xbrain.app.dto.VendedorDTO;
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

    public Optional<Vendedor> findById(Long id) {
        return vendedorRepository.findById(id);
    }

    public Vendedor saveVendedor(VendedorDTO vendedorDTO) {
        return vendedorRepository
                .save(mapperUtil.mapTo(vendedorDTO, Vendedor.class));
    }

    public VendedorDTO updateVendedor(Long id, VendedorDTO vendedorDTO) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(VendedorNaoEncontradoException::new);
        BeanUtils.copyProperties(vendedorDTO, vendedor, "id");

        return mapperUtil.mapTo(vendedorRepository.save(vendedor), VendedorDTO.class);
    }

    public void deleteVendedor(Long id) {
        vendedorRepository.delete(vendedorRepository.findById(id)
                .orElseThrow(VendedorNaoEncontradoException::new));
    }
}
