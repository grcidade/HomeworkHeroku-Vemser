package com.dbc.vemser.homework5_servicoEmail.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {

    @ApiModelProperty(value = "Insira o nome!")
    @NotBlank
    @Size(min = 2, max = 20, message = "Tamanho Invalido")
    private String nome;
    @ApiModelProperty(value = "Insira a data de nascimento!")
    @Past
    @NotNull
    private LocalDate dataNascimento;
    @ApiModelProperty(value = "Insira um CPF valido!")
    @CPF
    private String cpf;
    @ApiModelProperty(value = "Insira um email valido!")
    @Email
    private String email;

}
