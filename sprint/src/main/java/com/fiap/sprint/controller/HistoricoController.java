package com.fiap.sprint.controller;

import com.fiap.sprint.domain.historicoTratamento.*;
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
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoTratamentoRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DTOHistorico> Cadastrar(@RequestBody @Valid DTOHistorico dados, UriComponentsBuilder uriComponentsBuilder) {

        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Tratamento tratamento = tratamentoRepository.findById(dados.tratamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Tratamento não encontrado"));

        var historico = new HistoricoTratamento(paciente, tratamento, dados);

        repository.save(historico);

        var uri = uriComponentsBuilder.path("/historico/{id}").buildAndExpand(historico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTOHistorico(historico));
    }


    @GetMapping
    public ResponseEntity<Page<DTOListaHistorico>> Listar(@PageableDefault(size = 10, sort = {"observacao"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DTOListaHistorico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListaHistorico> Atualizar(@RequestBody @Valid DTOAttHistorico dados) {
        var historico = repository.findById(dados.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historico não encontrado"));
        historico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DTOListaHistorico(historico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Excluir(@PathVariable Long id) {
        var historico = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historico não encontrado"));
        historico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListaHistorico> Detalhar(@PathVariable Long id) {
        var historico = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historico não encontrado"));
        return ResponseEntity.ok(new DTOListaHistorico(historico));
    }
}