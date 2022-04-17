package com.xbrain.app.repository;

import com.xbrain.app.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    List<Vendedor> findAllBySellDateBetween(LocalDate fromDate, LocalDate toDate);
    // - Aonde encontrei a solução para a dificuldade que tive no query:
    // - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
}
