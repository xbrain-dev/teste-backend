package com.lgdias.testexbrain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.lgdias.testexbrain.dto.VendedorRankingDTO;
import com.lgdias.testexbrain.exception.DataInvalidaException;
import com.lgdias.testexbrain.model.Venda;
import com.lgdias.testexbrain.model.Venda.VendaBuilder;
import com.lgdias.testexbrain.model.Vendedor;
import com.lgdias.testexbrain.model.Vendedor.VendedorBuilder;
import com.lgdias.testexbrain.repository.VendaRepository;
import com.lgdias.testexbrain.repository.VendedorRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VendedorServiceImplTest {

  @Autowired
  private VendedorService vendedorService;

  @MockBean
  private VendaRepository vendaRepository;

  @MockBean
  private VendedorRepository vendedorRepository;

  @Test
  public void recuperarRankingPorVendasNoPeriodo_deveRetornarRankingOrdenado() throws Exception {

    LocalDate initialDate = LocalDate.of(2018, 8, 1);
    LocalDate finalDate = LocalDate.of(2018, 8, 8);

    Vendedor vendedor1 = new VendedorBuilder()
        .id(1L)
        .nome("Raj Solomón")
        .build();

    Venda venda1 = new VendaBuilder()
        .valor(14000.00)
        .data(initialDate.plusDays(2))
        .vendedor(vendedor1)
        .build();

    Vendedor vendedor2 = new VendedorBuilder()
        .id(2L)
        .nome("Roberto Carlos")
        .build();

    Venda venda2 = new VendaBuilder()
        .valor(4000.00)
        .data(initialDate.plusDays(3))
        .vendedor(vendedor2)
        .build();

    Venda venda3 = new VendaBuilder()
        .valor(2000.00)
        .data(initialDate.plusDays(4))
        .vendedor(vendedor2)
        .build();

    Venda venda4 = new VendaBuilder()
        .valor(1000.00)
        .data(initialDate.plusDays(5))
        .vendedor(vendedor2)
        .build();

    List<Venda> vendas = Arrays.asList(venda1, venda2, venda3, venda4);

    doReturn(vendas).when(vendaRepository).findAllByDataBetween(any(Date.class), any(Date.class));

    List<VendedorRankingDTO> topBySalesInPeriod = vendedorService
        .recuperarRankingPorVendasNoPeriodo(10L, initialDate, finalDate);

    assertThat(topBySalesInPeriod.size()).isEqualTo(2);

    MatcherAssert.assertThat(topBySalesInPeriod, IsIterableContainingInOrder.contains(
        new VendedorRankingDTO(2000.0, vendedor1),
        new VendedorRankingDTO(1000.0, vendedor2)
    ));
  }

  @Test
  public void recuperarRankingPorVendasNoPeriodo_naoDeveContabilizarVendasForaDoPeriodo()
      throws Exception {
    LocalDate initialDate = LocalDate.of(2018, 8, 1);
    LocalDate finalDate = LocalDate.of(2018, 8, 8);

    Vendedor vendedor1 = new VendedorBuilder()
        .id(1L)
        .nome("Raj Solomón")
        .build();

    Venda venda1 = new VendaBuilder()
        .valor(14000.00)
        .data(initialDate.plusDays(2))
        .vendedor(vendedor1)
        .build();

    Vendedor vendedor2 = new VendedorBuilder()
        .id(2L)
        .nome("Roberto Carlos")
        .build();

    Venda venda2 = new VendaBuilder()
        .valor(7000.00)
        .data(initialDate.plusDays(3))
        .vendedor(vendedor2)
        .build();

    Venda venda3 = new VendaBuilder()
        .valor(2000.00)
        .data(initialDate.minusDays(4))
        .vendedor(vendedor2)
        .build();

    Venda venda4 = new VendaBuilder()
        .valor(1000.00)
        .data(initialDate.plusDays(10))
        .vendedor(vendedor2)
        .build();

    List<Venda> vendas = Arrays.asList(venda1, venda2, venda3, venda4);

    doReturn(vendas).when(vendaRepository).findAllByDataBetween(any(Date.class), any(Date.class));

    List<VendedorRankingDTO> topBySalesInPeriod = vendedorService
        .recuperarRankingPorVendasNoPeriodo(10L, initialDate, finalDate);

    assertThat(topBySalesInPeriod.size()).isEqualTo(2);

    MatcherAssert.assertThat(topBySalesInPeriod, IsIterableContainingInOrder.contains(
        new VendedorRankingDTO(2000.0, vendedor1),
        new VendedorRankingDTO(1000.0, vendedor2)
    ));
  }

  @Test(expected = DataInvalidaException.class)
  public void recuperarRankingPorVendasNoPeriodo_naoDeveAceitarIntervaloNegativo()
      throws Exception {
    LocalDate initialDate = LocalDate.of(2018, 8, 30);
    LocalDate finalDate = LocalDate.of(2018, 8, 1);

    vendedorService.recuperarRankingPorVendasNoPeriodo(10L, initialDate, finalDate);
  }

}