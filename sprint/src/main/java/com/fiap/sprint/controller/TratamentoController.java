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
    private TratamentoService tratamentoService;

    @PostMapping
    public ResponseEntity<TratamentoDTO> cadastrar(@RequestBody @Valid TratamentoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var tratamento = tratamentoService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/tratamentos/{id}").buildAndExpand(tratamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new TratamentoDTO(tratamento));
    }

    @GetMapping
    public ResponseEntity<Page<ListaTratamentoDTO>> listar(@PageableDefault(size = 10, sort = {"tipo"}) Pageable paginacao) {
        var page = tratamentoService.listar(paginacao).map(ListaTratamentoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<ListaTratamentoDTO> atualizar(@RequestBody @Valid AttTratamentoDTO dados) {
        var tratamento = tratamentoService.atualizar(dados);
        return ResponseEntity.ok(new ListaTratamentoDTO(tratamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tratamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaTratamentoDTO> detalhar(@PathVariable Long id) {
        var tratamento = tratamentoService.detalhar(id);
        return ResponseEntity.ok(new ListaTratamentoDTO(tratamento));
    }
}