package com.xbrain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {

    private Long id;

    private LocalDateTime dataVenda;

    private BigDecimal valor;

    @Valid
    private VendedorDTO vendedor;
}
