package com.lgdias.testexbrain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgdias.testexbrain.exception.VendedorNaoEncontradoException;
import com.lgdias.testexbrain.model.Venda;
import com.lgdias.testexbrain.model.Venda.VendaBuilder;
import com.lgdias.testexbrain.model.Vendedor;
import com.lgdias.testexbrain.model.Vendedor.VendedorBuilder;
import com.lgdias.testexbrain.repository.VendaRepository;
import com.lgdias.testexbrain.repository.VendedorRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VendaServiceImplTest {

  @Autowired
  private VendaService vendaService;

  @MockBean
  private VendaRepository vendaRepository;

  @MockBean
  private VendedorRepository vendedorRepository;

  /**
   * Representa o ID dos elementos que estão sendo "salvos".
   */
  private static Long ID = 0L;

  @Before
  public void setup() {

    Vendedor vendedor = new VendedorBuilder()
        .id(1L)
        .nome("Raj Solomón")
        .build();

    when(vendedorRepository.findByIdAndNome(any(), any()))
        .thenAnswer((Answer<Optional<Vendedor>>) invocationOnMock -> {
          Long id = invocationOnMock.getArgument(0);
          String nome = invocationOnMock.getArgument(1);
          if ((id != null && id.equals(vendedor.getId())) &&
              (nome != null && nome.equals(vendedor.getNome()))) {
            return Optional.of(vendedor);
          }
          return Optional.empty();
        });

    when(vendaRepository.save(any(Venda.class)))
        .thenAnswer((Answer<Venda>) invocationOnMock -> {
          Venda argument = invocationOnMock.getArgument(0);
          if (argument.getId() == null) {
            argument.setId(++ID);
          }
          return argument;
        });
  }

  @Test
  public void salvar_deveSalvarVendaComTodosOsCampos() throws Exception {

    LocalDate diaDaVenda = LocalDate.of(2018, 8, 10);

    Vendedor vendedor = new VendedorBuilder()
        .id(1L)
        .nome("Raj Solomón")
        .build();

    Venda vendaParaSalvar = new VendaBuilder()
        .valor(5000.00)
        .data(diaDaVenda)
        .vendedor(vendedor)
        .build();

    Venda vendaSalva = vendaService.salvar(vendaParaSalvar);

    assertThat(vendaSalva.getId()).isEqualTo(1L);
    assertThat(vendaSalva.getData()).isEqualTo(diaDaVenda);
    assertThat(vendaSalva.getValor()).isEqualTo(5000.00);
    assertThat(vendaSalva.getVendedor().getId()).isEqualTo(vendedor.getId());
    assertThat(vendaSalva.getVendedor().getNome()).isEqualTo(vendedor.getNome());
  }

  @Test(expected = VendedorNaoEncontradoException.class)
  public void salvar_naoDeveSalvarVendaSemVendedor() throws Exception {

    LocalDate diaDaVenda = LocalDate.of(2018, 8, 10);

    Venda vendaParaSalvar = new VendaBuilder()
        .valor(5000.00)
        .data(diaDaVenda)
        .build();

    vendaService.salvar(vendaParaSalvar);
  }

  @Test(expected = VendedorNaoEncontradoException.class)
  public void salvar_naoDeveSalvarVendaComFuncionarioInexistente() throws Exception {

    LocalDate diaDaVenda = LocalDate.of(2018, 8, 10);

    Vendedor vendedor = new VendedorBuilder()
        .nome("Raj Solomón Júnior")
        .build();

    Venda vendaParaSalvar = new VendaBuilder()
        .valor(5000.00)
        .data(diaDaVenda)
        .vendedor(vendedor)
        .build();

    vendaService.salvar(vendaParaSalvar);
  }

  @Test
  public void calcularMediaVendas_deveRetornarMediaDeVendasPorDia() {
    Venda venda1 = new VendaBuilder()
        .valor(4000.00)
        .build();

    Venda venda2 = new VendaBuilder()
        .valor(2000.00)
        .build();

    Venda venda3 = new VendaBuilder()
        .valor(1000.00)
        .build();

    Double media = vendaService.calcularMediaVendas(Arrays.asList(venda1, venda2, venda3), 7);

    assertThat(media).isEqualTo(1000.00);

  }

}