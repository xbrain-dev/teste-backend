package com.xbrain.app.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VendaDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private BigDecimal quantidade;

    private LocalDate dataVenda;

    @NotBlank
    private BigDecimal valor;

    private BigDecimal total;

    @NotBlank
    private Long idVendedor;
}