package com.dbc.vemser.homework5_servicoEmail.repository;

import com.dbc.vemser.homework5_servicoEmail.entity.Contato;
import com.dbc.vemser.homework5_servicoEmail.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContatoRepository {

    private final List<Contato> listaContatos = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository(){
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, "residencial", "993838346", "WhatsApp"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 2, "comercial", "993838346", "Telefone loja 1"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 3, "residencial", "993838346", "WhatsApp"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 4, "residencial", "993838346", "Telefone Fixo"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 5, "comercial", "993838346", "Telefone Empresa"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, "comercial", "993838346", "Telefone Empresa1"));
    }

    public Contato create(Integer id, Contato contato){
        contato.setIdContato(COUNTER.incrementAndGet());
        contato.setIdPessoa(id);
        listaContatos.add(contato);
        return contato;
    }

    public List<Contato> list(){
        return listaContatos;
    }

    public Contato update(Integer id, Contato contatoAtualizado) throws Exception {
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        contatoRecuperado.setIdPessoa(contatoAtualizado.getIdPessoa());
        contatoRecuperado.setDescricao(contatoAtualizado.getDescricao());
        contatoRecuperado.setTipoContato(contatoAtualizado.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizado.getNumero());
        return contatoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        listaContatos.remove(contatoRecuperado);
    }

    public List<Contato> findContatoByIdPessoa(Integer id){
        return listaContatos.stream()
                .filter(contato -> contato.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Contato findContatoById(Integer id) throws Exception {
        return listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
    }
}
