package com.dbc.vemser.homework5_servicoEmail.controller;

import com.dbc.vemser.homework5_servicoEmail.dto.ContatoCreateDTO;
import com.dbc.vemser.homework5_servicoEmail.dto.ContatoDTO;
import com.dbc.vemser.homework5_servicoEmail.exceptions.RegraDeNegocioException;
import com.dbc.vemser.homework5_servicoEmail.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
@Validated
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<ContatoDTO>> list(){
        return new ResponseEntity<>(contatoService.listarContatos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> findById(@PathVariable("idContato") Integer id) throws Exception {
        return new ResponseEntity<>(contatoService.findById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/byIdPessoa/{idPessoa}")
    public ResponseEntity<List<ContatoDTO>> findByIdPessoa(@PathVariable("idPessoa") Integer id) {
        return new ResponseEntity<>(contatoService.findContatoByIdPessoa(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer id,
                                             @Valid @RequestBody ContatoCreateDTO contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.criarContato(id, contato), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idContato}")
    public void delete(@PathVariable("idContato") Integer id) throws Exception {
        contatoService.deletarContatos(id);
    }

    @PutMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id,
                          @Valid @RequestBody ContatoCreateDTO contatoAtualizar) throws Exception {
        return new ResponseEntity<>(contatoService.atualizarContato(id, contatoAtualizar), HttpStatus.ACCEPTED);
    }
}
