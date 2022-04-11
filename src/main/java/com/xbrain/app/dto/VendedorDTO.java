package com.xbrain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {

    private Long id;

    @NotBlank(message = "O campo nome é obrigatorio.")
    @Size(max = 50, message = "O campo deve ter no maximo {max} caracteres.")
    private String nome;

    @NotBlank(message = "O campo cpf é obrigatorio.")
    @Size(max = 10, message = "O campo deve ter no maximo {max} caracteres.")
    private String cpf;
}
