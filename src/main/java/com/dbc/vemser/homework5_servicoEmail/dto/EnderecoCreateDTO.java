package com.dbc.vemser.homework5_servicoEmail.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnderecoCreateDTO {

    private Integer idPessoa;
    @NotEmpty
    @Size(max = 250)
    private String logradouro;
    @NotEmpty
    private String numero;
    @NotEmpty
    @Size(max = 8)
    private String cep;
    @NotEmpty
    @Size(max = 250)
    private String cidade;
    @NotEmpty
    private String estado;
    @NotEmpty
    private String pais;
    @NotNull
    private String tipo;

}
