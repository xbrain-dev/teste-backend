package com.xbrain.controller;

import com.xbrain.dto.VendedorResumoDTO;
import com.xbrain.model.Vendedor;
import com.xbrain.util.ValidadorCPF;
import com.xbrain.repository.VendedorJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedoresController {

    @Autowired
    private VendedorJpaRepository vendedorJpaRepository;

    @PersistenceContext
    private EntityManager em;

    // Listar todos os vendedores
    @GetMapping
    public List<Vendedor> buscar() {
        return vendedorJpaRepository.findAll();
    }

    // Criar um novo vendedor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor salvar(@RequestBody final Vendedor vendedor) throws Exception {
        // Verificar o número do CPF informado
        if (!ValidadorCPF.validar(vendedor.getCpf())) {
            throw new Exception( "O número de CPF não foi informado corretamente.");
        }
        // Verificar se o nome foi preenchido
        if (vendedor.getNome() == null || vendedor.getCpf().isEmpty()) {
            throw new Exception( "É obrigatório informar o nome do vendedor.");
        }
        return vendedorJpaRepository.save(vendedor);
    }

    // Atualizar um vendedor
    @PutMapping(value = "/{id}")
    public Vendedor atualizar(@PathVariable("id") Long id, @RequestBody final Vendedor vendedor) throws Exception {
        vendedor.setId(id);
        return vendedorJpaRepository.save(vendedor);
    }

    // Remover um vendedor
    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Long id) {
        vendedorJpaRepository.deleteById(id);
    }

    // Resumo dos valores de venda dos vendedores por período ordenado
    @GetMapping(value = "/ranking")
    public ArrayList<VendedorResumoDTO> ranking(@RequestParam(value = "dataInicial") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dataInicial,
                                                @RequestParam(value = "dataFinal") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dataFinal,
                                                @RequestParam(value = "limite", defaultValue = "10") Integer limite)
                                                throws Exception {

        Period periodo = Period.between(dataInicial, dataFinal);
        Integer quantidadeDias = periodo.getDays() + 1;

        if (quantidadeDias < 1) {
            throw new Exception("Período informado é inválido.");
        }

        ArrayList<VendedorResumoDTO> ranking = new ArrayList<>();

        Query query = em.createNativeQuery(
                "SELECT a.vendedor_id, b.nome, b.cpf, count(*) as quantidade, sum(a.valor) as valor\n" +
                "FROM venda a\n" +
                "LEFT JOIN vendedor b\n" +
                "ON a.vendedor_id = b.id\n" +
                "WHERE a.data >= :dataInicial AND a.data <= :dataFinal\n" +
                "GROUP BY a.vendedor_id\n" +
                "ORDER BY sum(a.valor) DESC LIMIT :limite");

        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);
        query.setParameter("limite", limite);

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            VendedorResumoDTO resumo = new VendedorResumoDTO();

            resumo.setId((BigInteger)record[0]);
            resumo.setNome((String)record[1]);
            resumo.setCpf((String)record[2]);
            resumo.setDataInicial(dataInicial);
            resumo.setDataFinal(dataFinal);
            resumo.setQuantidadeVendas((BigInteger)record[3]);
            resumo.setValorTotalPeriodo((Double)record[4]);
            resumo.setValorMedioDiario(resumo.getValorTotalPeriodo() / quantidadeDias.doubleValue());

            ranking.add(resumo);
        });

        return ranking;
    }
}
