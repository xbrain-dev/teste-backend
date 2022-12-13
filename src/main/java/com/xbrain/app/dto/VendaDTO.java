package com.xbrain.app.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VendaDTO {

    private Long id;

    @NotBlank(message = "O campo nome do produto é obrigatorio!")
    @Size(max = 30,message = "O campo deve conter no maximo {max} caracteres")
    private String nomeProduto;

    @NotBlank(message = "O campo quantidade é obrigatorio!")
    @Size(max = 30, message = "O campo deve ter no maximo {max} caracteres")
    private BigDecimal quantidade;

    // - A data não precisa ser inserida porque o sistema ja faz o set automatico;
    private LocalDate dataVenda;

    @NotBlank(message = "O campo valor é obrigatorio!")
    @Size(max=30, message = "O campo deve conter no maximo {max} caracteres")
    private BigDecimal valor;

    // - valor total não deve ser inserido porque o sistema ja faz o calculo;
    private BigDecimal valorTotal;

    // - O ID faz o relacionamento com o vendedor
    @NotBlank(message = "O campo ID é necessario para o relacionamento com a entidade vendedor")
    private Long idVendedor;
}