package com.dbc.vemser.homework5_servicoEmail.repository;

import com.dbc.vemser.homework5_servicoEmail.entity.Endereco;
import com.dbc.vemser.homework5_servicoEmail.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {

    @Autowired
    PessoaRepository pessoaRepository;

    List<Endereco> enderecoList = new ArrayList<>();
    AtomicInteger COUNTER = new AtomicInteger();

    public EnderecoRepository(){
        enderecoList.add(new Endereco(COUNTER.incrementAndGet(), 1, "Rua Amapa", "24", "99999999", "Cachoeirinha", "RS", "Brasil", "APTO"));
        enderecoList.add(new Endereco(COUNTER.incrementAndGet(), 2, "Rua Santos", "1", "11111111", "Cachoeirinha", "RS", "Brasil", "Casa"));
        enderecoList.add(new Endereco(COUNTER.incrementAndGet(), 3, "Rua Criciuma", "125", "22222222", "Cachoeirinha", "RS", "Brasil", "Casa"));
        enderecoList.add(new Endereco(COUNTER.incrementAndGet(), 1, "Rua Tapajos", "78", "33333333", "Cachoeirinha", "RS", "Brasil", "APTO"));
        enderecoList.add(new Endereco(COUNTER.incrementAndGet(), 2, "Rua Dinamarca", "3", "44444444", "Cachoeirinha", "RS", "Brasil", "Casa"));
    }

    public Endereco create(Endereco endereco, Integer id) throws RegraDeNegocioException {
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        endereco.setIdPessoa(id);
        if(pessoaRepository.getById(endereco.getIdPessoa()) == null){
            throw new RegraDeNegocioException("Pessoa não encontrada!");
        }
        enderecoList.add(endereco);
        return endereco;
    }

    public void delete(Integer id) throws Exception {
        Endereco enderecoRecuperado = enderecoList.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        enderecoList.remove(enderecoRecuperado);
    }

    public List<Endereco> list(){
        return enderecoList;
    }

    public Endereco getByIdEndereco(Integer id) throws Exception {
        return enderecoList.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(Exception::new);
    }

    public List<Endereco> getByIdPessoa(Integer id) throws Exception {
        return enderecoList.stream()
                .filter(endereco -> endereco.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Endereco update(Endereco novoEndereco, Integer id) throws Exception {
        Endereco enderecoRecuperado = enderecoList.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        enderecoRecuperado.setIdPessoa(novoEndereco.getIdPessoa());
        enderecoRecuperado.setNumero(novoEndereco.getNumero());
        enderecoRecuperado.setCep(novoEndereco.getCep());
        enderecoRecuperado.setCidade(novoEndereco.getCidade());
        enderecoRecuperado.setLogradouro(novoEndereco.getLogradouro());
        enderecoRecuperado.setEstado(novoEndereco.getEstado());
        enderecoRecuperado.setPais(novoEndereco.getPais());
        enderecoRecuperado.setTipo(novoEndereco.getTipo());
        return enderecoRecuperado;
    }

}
