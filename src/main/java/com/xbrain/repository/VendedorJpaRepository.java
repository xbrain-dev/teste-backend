package com.xbrain.repository;

import com.xbrain.model.Vendedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface VendedorJpaRepository extends JpaRepository<Vendedor, Long> { }
