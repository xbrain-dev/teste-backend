package com.xbrain.app.repository;

import com.xbrain.app.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    // - Econtra todas as vendas com data entre - fromDate E toDate;
    List<Venda> findAllByDataVendaBetween(LocalDate fromDate, LocalDate toDate);
}
