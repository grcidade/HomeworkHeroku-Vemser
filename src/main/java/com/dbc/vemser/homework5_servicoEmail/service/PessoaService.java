package com.dbc.vemser.homework5_servicoEmail.service;

import com.dbc.vemser.homework5_servicoEmail.dto.PessoaCreateDTO;
import com.dbc.vemser.homework5_servicoEmail.dto.PessoaDTO;
import com.dbc.vemser.homework5_servicoEmail.entity.Pessoa;
import com.dbc.vemser.homework5_servicoEmail.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmailService emailService;

    public PessoaDTO create(PessoaCreateDTO pessoaCreate) throws Exception {
        log.info("Chamou criar pessoa");
        Pessoa pessoa = objectMapper.convertValue(pessoaCreate, Pessoa.class);
        Pessoa pessoaCriada = pessoaRepository.create(pessoa);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaCriada, PessoaDTO.class);
        //emailService.sendSimpleMessage(pessoaCriada);
        //emailService.sendWithAttachment(pessoaCriada);
//        emailService.sendEmail(pessoaDTO, "create");
       return pessoaDTO;
    }

    public List<PessoaDTO> list(){
        log.info("Chamou listar pessoa");
        return pessoaRepository.list().stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public PessoaDTO update(Integer id, PessoaCreateDTO pessoaAtualizar) throws Exception {
        log.info("Chamou atualizar pessoa");
        Pessoa pessoa = objectMapper.convertValue(pessoaAtualizar, Pessoa.class);
        Pessoa pessoaAtualizada = pessoaRepository.update(id, pessoa);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaAtualizada, PessoaDTO.class);
//        emailService.sendEmail(pessoaDTO, "update");
        return pessoaDTO;
    }

    public void delete(Integer id) throws Exception {
        log.info("Chamou deletar pessoa");
        Pessoa pessoaParaMandarEmail = pessoaRepository.getById(id);
        PessoaDTO pessoaDTOparaMandarEmail = objectMapper.convertValue(pessoaParaMandarEmail, PessoaDTO.class);
//        emailService.sendEmail(pessoaDTOparaMandarEmail, "delete");
        pessoaRepository.delete(id);
    }

    public List<PessoaDTO> listByName(String nome) {
        log.info("Chamou listar pessoa por nome");
        return pessoaRepository.listByName(nome).stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }
}
