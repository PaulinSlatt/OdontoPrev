package com.fiap.sprint.controller;

import com.fiap.sprint.domain.paciente.Paciente;
import com.fiap.sprint.domain.paciente.PacienteRepository;
import com.fiap.sprint.domain.recomendacao.*;
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
@RequestMapping("/recomendacao")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoService recomendacaoService;

    @PostMapping
    public ResponseEntity<RecomendacaoDTO> cadastrar(@RequestBody @Valid RecomendacaoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var recomendacao = recomendacaoService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/recomendacao/{id}").buildAndExpand(recomendacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new RecomendacaoDTO(recomendacao));
    }

    @GetMapping
    public ResponseEntity<Page<ListaRecomendacaoDTO>> listar(@PageableDefault(size = 10, sort = {"motivo"}) Pageable paginacao) {
        var page = recomendacaoService.listar(paginacao).map(ListaRecomendacaoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<ListaRecomendacaoDTO> atualizar(@RequestBody @Valid AttRecomendacaoDTO dados) {
        var recomendacao = recomendacaoService.atualizar(dados);
        return ResponseEntity.ok(new ListaRecomendacaoDTO(recomendacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        recomendacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaRecomendacaoDTO> detalhar(@PathVariable Long id) {
        var recomendacao = recomendacaoService.detalhar(id);
        return ResponseEntity.ok(new ListaRecomendacaoDTO(recomendacao));
    }
}