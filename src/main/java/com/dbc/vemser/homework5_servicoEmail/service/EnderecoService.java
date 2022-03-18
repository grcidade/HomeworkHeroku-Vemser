package com.dbc.vemser.homework5_servicoEmail.service;

import com.dbc.vemser.homework5_servicoEmail.dto.EnderecoCreateDTO;
import com.dbc.vemser.homework5_servicoEmail.dto.EnderecoDTO;
import com.dbc.vemser.homework5_servicoEmail.dto.PessoaDTO;
import com.dbc.vemser.homework5_servicoEmail.entity.Endereco;
import com.dbc.vemser.homework5_servicoEmail.exceptions.RegraDeNegocioException;
import com.dbc.vemser.homework5_servicoEmail.repository.EnderecoRepository;
import com.dbc.vemser.homework5_servicoEmail.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmailService emailService;

    public EnderecoDTO criarEndereco(Integer id, EnderecoCreateDTO enderecoCriar) throws Exception {
        if(pessoaRepository.getById(id) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        log.info("Chamou criar endereço");
        PessoaDTO pessoaParaReceberEmail= objectMapper.convertValue(pessoaRepository.getById(id), PessoaDTO.class);
        Endereco endereco = objectMapper.convertValue(enderecoCriar, Endereco.class);
        Endereco enderecoCriado = enderecoRepository.create(endereco, id);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoCriado, EnderecoDTO.class);
//        emailService.sendEmailWithAddress(pessoaParaReceberEmail, enderecoDTO, "create");
        return enderecoDTO;
    }

    public void deletarEndereco(Integer id) throws Exception {
        log.info("Chamou deletar endereço");
        PessoaDTO pessoaParaReceberEmail= objectMapper.convertValue(pessoaRepository.getById(enderecoRepository.getByIdEndereco(id).getIdPessoa()), PessoaDTO.class);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRepository.getByIdEndereco(id), EnderecoDTO.class);
//        emailService.sendEmailWithAddress(pessoaParaReceberEmail, enderecoDTO, "delete");
        enderecoRepository.delete(id);
    }

    public EnderecoDTO editarEndereco(Integer id, EnderecoCreateDTO novoEndereco) throws Exception {
        if(pessoaRepository.getById(novoEndereco.getIdPessoa()) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        log.info("Chamou editar endereço");
        Endereco endereco = objectMapper.convertValue(novoEndereco, Endereco.class);
        Endereco enderecoAtualizado = enderecoRepository.update(endereco, id);
        PessoaDTO pessoaParaReceberEmail = objectMapper.convertValue(pessoaRepository.getById(enderecoRepository.getByIdEndereco(enderecoAtualizado.getIdEndereco()).getIdPessoa()), PessoaDTO.class);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoAtualizado, EnderecoDTO.class);
//        emailService.sendEmailWithAddress(pessoaParaReceberEmail, enderecoDTO, "update");
        return enderecoDTO;
    }


    public EnderecoDTO getByIdEndereco(Integer id) throws Exception {
        log.info("Chamou achar endereço por ID");
        return objectMapper.convertValue(enderecoRepository.getByIdEndereco(id), EnderecoDTO.class);
    }

    public List<EnderecoDTO> getByIdPessoa(Integer id) throws Exception {
        if(pessoaRepository.getById(id) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        log.info("Chamou achar endereço por ID PESSOA");
        return enderecoRepository.getByIdPessoa(id).stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> listarEnderecos(){
        log.info("Chamou listar endereços");
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

}
