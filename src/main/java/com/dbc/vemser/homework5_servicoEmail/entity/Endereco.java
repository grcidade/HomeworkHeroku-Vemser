package com.dbc.vemser.homework5_servicoEmail.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Endereco {

    private Integer idEndereco;
    private Integer idPessoa;
    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String tipo;

}
