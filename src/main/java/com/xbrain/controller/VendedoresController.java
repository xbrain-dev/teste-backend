package com.xbrain.controller;

import com.xbrain.dto.VendedorResultadoDTO;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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

    // Ranking de vendedores diário ordenado por valor total das vendas
    // TODO: Alterar para SqlResultSetMapping em vez de casting
    @GetMapping(value = "/ranking")
    public ArrayList<VendedorResultadoDTO> ranking(@RequestParam(value = "data") @DateTimeFormat(pattern="yyyy-MM-dd") Date data,
                                                   @RequestParam(value = "limite", defaultValue = "10") Integer limite) {
        ArrayList<VendedorResultadoDTO> ranking = new ArrayList<>();

        Query query = em.createNativeQuery(
                "SELECT a.vendedor_id, b.nome, b.cpf, count(*) as quantidade, sum(a.valor) as valor\n" +
                "FROM venda a\n" +
                "LEFT JOIN vendedor b\n" +
                "ON a.vendedor_id = b.id " +
                "WHERE a.data = :data " +
                "GROUP BY a.vendedor_id\n" +
                "ORDER BY sum(a.valor) DESC LIMIT :limite");

        query.setParameter("data", data);
        query.setParameter("limite", limite);

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            VendedorResultadoDTO resultado = new VendedorResultadoDTO();

            resultado.setId((BigInteger)record[0]);
            resultado.setNome((String)record[1]);
            resultado.setCpf((String)record[2]);
            resultado.setDataVendas(data);
            resultado.setQuantidadeVendas((BigInteger)record[3]);
            resultado.setValorTotalVendas((BigDecimal)record[4]);

            ranking.add(resultado);
        });

        return ranking;
    }
}
