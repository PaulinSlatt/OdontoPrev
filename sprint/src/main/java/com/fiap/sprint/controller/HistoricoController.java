package com.fiap.sprint.controller;

import com.fiap.sprint.domain.historicoTratamento.*;
import com.fiap.sprint.domain.paciente.Paciente;
import com.fiap.sprint.domain.paciente.PacienteRepository;
import com.fiap.sprint.domain.tratamento.Tratamento;
import com.fiap.sprint.domain.tratamento.TratamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;


// Classe que recebe as requisições do CRUD
@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @PostMapping
    public ResponseEntity<HistoricoDTO> cadastrar(@RequestBody @Valid HistoricoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var historico = historicoService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/historico/{id}").buildAndExpand(historico.getId()).toUri();
        return ResponseEntity.created(uri).body(new HistoricoDTO(historico));
    }

    @GetMapping
    public ResponseEntity<Page<ListaHistoricoDTO>> listar(@PageableDefault(size = 10, sort = {"observacao"}) Pageable paginacao) {
        var page = historicoService.listar(paginacao).map(ListaHistoricoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<ListaHistoricoDTO> atualizar(@RequestBody @Valid AttHistoricoDTO dados) {
        var historico = historicoService.atualizar(dados);
        return ResponseEntity.ok(new ListaHistoricoDTO(historico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        historicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaHistoricoDTO> detalhar(@PathVariable Long id) {
        var historico = historicoService.detalhar(id);
        return ResponseEntity.ok(new ListaHistoricoDTO(historico));
    }
}