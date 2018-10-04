package com.xbrain.repository;

import com.xbrain.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface VendaJpaRepository extends JpaRepository<Venda, Long> {

}
