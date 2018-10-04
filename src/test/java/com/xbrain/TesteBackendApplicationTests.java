package com.xbrain;

import com.xbrain.model.Venda;
import com.xbrain.model.Vendedor;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesteBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TesteBackendApplicationTests {

	@LocalServerPort
	private int porta;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();

	@Test
	public void buscarVendedores() {
		ResponseEntity<String> response = testRestTemplate.exchange(
				criarURLComPorta("/vendedores"),
				HttpMethod.GET, new HttpEntity<>(headers), String.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void buscarVendas() {
		ResponseEntity<String> response = testRestTemplate.exchange(
				criarURLComPorta("/vendas"),
				HttpMethod.GET, new HttpEntity<>(headers), String.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void criarVendedorComVenda() {
		Vendedor vendedor = new Vendedor(0L, "KEYLLA CRISTINA GOMES BRANDAO", "07049519324");

		ResponseEntity<Vendedor> responseVendedor = testRestTemplate.exchange(
				criarURLComPorta("/vendedores"),
				HttpMethod.POST,
				new HttpEntity<>(vendedor, headers),
				Vendedor.class);

		Assertions.assertThat(responseVendedor.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(responseVendedor.getBody().getId()).isGreaterThan(0L);

		vendedor = responseVendedor.getBody();
		Venda venda = new Venda(0L, LocalDate.now(),99.90d, vendedor);

		ResponseEntity<Venda> responseVenda = testRestTemplate.exchange(
				criarURLComPorta("/vendas"),
				HttpMethod.POST,
				new HttpEntity<>(venda, headers),
				Venda.class);

		Assertions.assertThat(responseVenda.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(responseVenda.getBody().getId()).isGreaterThan(0L);
	}

	@Test
	public void buscarRankingVendedoresHoje() {
		LocalDate dataHoje = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dataHojeFormatada = dataHoje.format(formatter);

		ResponseEntity<String> responseVendedor = testRestTemplate.exchange(
				criarURLComPorta("/vendedores/ranking?dataInicial="+ dataHojeFormatada+"&dataFinal="+ dataHojeFormatada),
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertThat(responseVendedor.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private String criarURLComPorta(String uri) {
		return "http://localhost:" + porta + uri;
	}
}
