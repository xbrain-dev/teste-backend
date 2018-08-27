package com.example.demo.repository;

import com.example.demo.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Long> {

  Optional<Venda> findById(Long id);

  List<Venda> findAllByDataVendaBetween(LocalDate dateStart, LocalDate dateEnd);

}
