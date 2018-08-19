package com.lgdias.testexbrain.repository;

import com.lgdias.testexbrain.model.Venda;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {

  List<Venda> findAllByDataBetween(Date start, Date end);
}
