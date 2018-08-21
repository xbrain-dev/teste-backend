package com.example.demo.repository;

import com.example.demo.model.Venda;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    Optional<Venda> findById(Long id);
}
