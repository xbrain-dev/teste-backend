package com.example.demo;

import com.example.demo.controller.VendaController;
import com.example.demo.model.Venda;
import com.example.demo.model.Vendedor;
import com.example.demo.repository.VendaRepository;
import com.example.demo.repository.VendedorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendaTest {

    @MockBean
    private VendaRepository vendaRepository;

    @MockBean
    private VendedorRepository vendedorRepository;

    @Autowired
    private MockMvc mock;

    @Autowired
    private VendaController vendaController;

    @Before
    public void setUp() {
      Vendedor vendedorPronto = new Vendedor(1L,"Marcelo", null);
      vendedorRepository.save(vendedorPronto);
      Vendedor vendedor = (Vendedor) vendedorRepository.findAll();
      System.out.println("O vendedor é " + vendedor.getNome());
    }

    @Test
    public void salvarVenda() throws Exception {
      Vendedor vendedor =  new Vendedor(1L,"Marcelo", null);
      LocalDate dataAtualVenda = LocalDate.of(2018,8,23);
      Venda novaVenda = new Venda(1L, dataAtualVenda, 2500D, vendedor);
      List<Venda> vendas = Arrays.asList(novaVenda);
      given(vendaRepository.findAll()).willReturn(vendas);
      mock.perform(get("/api/vendas")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect((ResultMatcher) jsonPath("$[0].id", is(novaVenda.getId())));
    }

    @Test
    public void salvarNaoSalvarVendaSemVendedor() throws Exception {
      Vendedor vendedor =  new Vendedor(1L,"Marcelo", null);
      LocalDate dataAtualVenda = LocalDate.of(2018,8,23);
      Venda novaVenda = new Venda(1L, dataAtualVenda, 2500D, vendedor);
      List<Venda> vendas = Arrays.asList(novaVenda);
      given(vendaRepository.findAll()).willReturn(vendas);
      mock.perform(get("/api/vendas")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect((ResultMatcher) jsonPath("$[0].id", is(novaVenda.getId())));
    }




}
