package com.example.demo.repository;

import com.example.demo.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {

    Optional<Vendedor> findById(Long id);



}
