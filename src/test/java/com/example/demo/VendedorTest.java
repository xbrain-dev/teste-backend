package com.example.demo;

import com.example.demo.controller.VendaController;
import com.example.demo.model.Vendedor;
import com.example.demo.repository.VendaRepository;
import com.example.demo.repository.VendedorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendedorTest {

  @Autowired
  private VendaRepository vendaRepository;

  @Autowired
  private VendedorRepository vendedorRepository;

  @Autowired
  private VendaController vendaController;

  @Test
  public void salvarCadastroVendedor() {
    Vendedor vendedor = new Vendedor(1L, "Surfista", null);
    Vendedor vendedorSalvo = vendedorRepository.save(vendedor);
    Assert.assertEquals(vendedorSalvo.getNome(), vendedor.getNome());
  }

  @Test(expected = TransactionSystemException.class)
  public void salvarCadastroVendedorSemNome() {
    Vendedor vendedor = new Vendedor(1L, null, null);
    vendedorRepository.save(vendedor);
  }

  @Test
  public void recuperarTodosOsVendedores() {
    Vendedor vendedorPrimeiro = new Vendedor(1L, "Marcelo", null);
    Vendedor vendedorSegundo = new Vendedor(2L, "Surfista", null);
    Vendedor vendedorTerceiro = new Vendedor(3L, "Lubanco", null);

    vendedorRepository.save(vendedorPrimeiro);
    vendedorRepository.save(vendedorSegundo);
    vendedorRepository.save(vendedorTerceiro);
    List<Vendedor> vendedoresSalvos = vendedorRepository.findAll();

    Assert.assertEquals(vendedoresSalvos.size(), 3);
  }

}

