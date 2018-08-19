package com.lgdias.testexbrain.repository;

import com.lgdias.testexbrain.model.Vendedor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

  Optional<Vendedor> findByIdAndNome(Long id, String nome);
}
