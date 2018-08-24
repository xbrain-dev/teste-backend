package com.example.demo;

import com.example.demo.controller.VendaController;
import com.example.demo.dto.VendedorDTO;
import com.example.demo.exceptions.DatasInvalidasException;
import com.example.demo.model.Venda;
import com.example.demo.model.Vendedor;
import com.example.demo.repository.VendaRepository;
import com.example.demo.repository.VendedorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendaTest {

  @Autowired
  private VendaRepository vendaRepository;

  @Autowired
  private VendedorRepository vendedorRepository;

  @Autowired
  private VendaController vendaController;

  @Autowired
  private ObjectMapper objectMapper;

  @Test(expected = ConstraintViolationException.class)
  public void salvarVendaSemVendedor() {
    LocalDate dataAtualVenda = LocalDate.of(2018, 8, 23);
    Venda venda = new Venda(1L, dataAtualVenda, 500.0, null);
    vendaRepository.save(venda);
  }

  @Test
  public void salvarVenda() {
    LocalDate dataAtualVenda = LocalDate.of(2018, 8, 23);
    Vendedor vendedor = new Vendedor(1L, "Marcelo", null);
    vendedorRepository.save(vendedor);
    Venda venda = new Venda(1L, dataAtualVenda, 500.0, vendedor);
    Venda vendaSalva = vendaRepository.save(venda);
    Assert.assertEquals(vendaSalva.getId(), venda.getId());
    vendaRepository.deleteAll();
  }

  @Test
  public void recuperarListaDeMelhoresVendedores() throws DatasInvalidasException {
    Vendedor vendedorPrimeiro = vendedorRepository.save(new Vendedor(1L, "Marcelo", null));
    Vendedor vendedorSegundo = vendedorRepository.save(new Vendedor(2L, "Surfista", null));
    Vendedor vendedorTerceiro = vendedorRepository.save(new Vendedor(3L, "Lubanco", null));

    LocalDate dataAtualVenda = LocalDate.of(2018, 8, 23);

    Venda venda1 = new Venda(1L, dataAtualVenda, 500.0, vendedorPrimeiro);
    Venda venda2 = new Venda(2L, dataAtualVenda, 2000.0, vendedorSegundo);
    Venda venda3 = new Venda(3L, dataAtualVenda, 150.0, vendedorTerceiro);

    vendaRepository.save(venda1);
    vendaRepository.save(venda2);
    vendaRepository.save(venda3);

    List<VendedorDTO> rankingVendedores =
            vendaController.rankingDezMelhores(dataAtualVenda.minusDays(1), dataAtualVenda.plusDays(2), 10L).collect(Collectors.toList());
    Optional<VendedorDTO> vendedorVencedor = rankingVendedores.stream().findFirst();
    Assert.assertEquals("Surfista", vendedorVencedor.get().getVendedor().getNome());

  }

  @Test(expected = DatasInvalidasException.class)
  public void validarVendasPartirDasDatas() throws DatasInvalidasException {
    LocalDate dataStart = LocalDate.of(2018, 8, 23);
    LocalDate dataEnd = LocalDate.of(2018, 8, 19);
    vendaController.rankingDezMelhores(dataStart, dataEnd, 10L);
  }

}
