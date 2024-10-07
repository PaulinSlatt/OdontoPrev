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
    private RecomendacaoRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DTORecomendacao> Cadastrar(@RequestBody @Valid DTORecomendacao dados, UriComponentsBuilder uriComponentsBuilder) {

        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Tratamento tratamento = tratamentoRepository.findById(dados.tratamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Tratamento não encontrado"));

        var recomendacao = new Recomendacao(paciente, tratamento, dados);

        repository.save(recomendacao);

        var uri = uriComponentsBuilder.path("/recomendacao/{id}").buildAndExpand(recomendacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTORecomendacao(recomendacao));
    }


    @GetMapping
    public ResponseEntity<Page<DTOListaRecomendacao>> Listar(@PageableDefault(size = 10, sort = {"motivo"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DTOListaRecomendacao::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListaRecomendacao> Atualizar(@RequestBody @Valid DTOAttRecomendacao dados) {
        var recomendacao = repository.findById(dados.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recomendacao não encontrado"));
        recomendacao.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DTOListaRecomendacao(recomendacao));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Excluir(@PathVariable Long id) {
        var recomendacao = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recomendacao não encontrado"));
        recomendacao.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListaRecomendacao> Detalhar(@PathVariable Long id) {
        var recomendacao = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recomendacao não encontrado"));
        return ResponseEntity.ok(new DTOListaRecomendacao(recomendacao));
    }
}