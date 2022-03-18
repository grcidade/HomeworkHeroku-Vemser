package com.dbc.vemser.homework5_servicoEmail.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoCreateDTO {

    private Integer idPessoa;
    @NotNull
    @NotBlank
    private String tipoContato;
    @NotEmpty
    @Size(min = 5, max = 13)
    private String numero;
    @NotEmpty
    private String descricao;

}
