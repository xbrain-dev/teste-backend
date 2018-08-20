package com.lgdias.testexbrain.repository;

import com.lgdias.testexbrain.model.Venda;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {

  List<Venda> findAllByDataBetween(LocalDate start, LocalDate end);
}
