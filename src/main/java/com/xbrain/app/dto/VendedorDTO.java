package com.xbrain.app.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class VendedorDTO {

    private Long id;

    @NotBlank(message = "O campo nome é obrigatorio.")
    @Size(max = 50, message = "O campo deve ter no maximo {max} caracteres.")
    private String nome;

    @CPF(message = "CPF invalido ou Cancelado")
    @NotBlank(message = "O campo cpf é obrigatorio.")
    @Size(max = 15, message = "O campo deve ter no maximo {max} caracteres.")
    private String cpf;

    // - O campo salesAverage não deve ser inserido pois o set já é feito pelo sistema;
    private BigDecimal mediaDeVendas;
    private List<VendaDTO> vendas = new ArrayList<>();
}
