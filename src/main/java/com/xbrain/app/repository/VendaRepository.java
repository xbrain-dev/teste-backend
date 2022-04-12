package com.xbrain.app.repository;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.xbrain.app.model.Venda;
import com.xbrain.app.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
