package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {

    Optional<Vendedor> findById(Long id);



}
