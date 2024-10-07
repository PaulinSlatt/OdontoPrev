package com.fiap.sprint.controller;
import com.fiap.sprint.domain.tratamento.*;
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
@RequestMapping("/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOTratamento> Cadastrar(@RequestBody @Valid DTOTratamento dados, UriComponentsBuilder uriComponentsBuilder) {
        var tratamento = new Tratamento(dados);
        repository.save(tratamento);
        var uri = uriComponentsBuilder.path("/tratamentos/{id}").buildAndExpand(tratamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DTOTratamento(tratamento));
    }


    @GetMapping
    public ResponseEntity<Page<DTOListaTratamento>> Listar(@PageableDefault(size = 10, sort = {"tipo"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DTOListaTratamento::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListaTratamento> Atualizar(@RequestBody @Valid DTOAttTratamento dados) {
        var tratamento = repository.findById(dados.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratamento não encontrado"));
        tratamento.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DTOListaTratamento(tratamento));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Excluir(@PathVariable Long id) {
        var tratamento = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratamento não encontrado"));
        tratamento.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListaTratamento> Detalhar(@PathVariable Long id) {
        var tratamento = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratamento não encontrado"));
        return ResponseEntity.ok(new DTOListaTratamento(tratamento));
    }
}