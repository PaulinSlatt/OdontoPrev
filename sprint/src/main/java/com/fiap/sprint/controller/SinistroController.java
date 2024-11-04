package com.fiap.sprint.controller;
import com.fiap.sprint.domain.paciente.Paciente;
import com.fiap.sprint.domain.paciente.PacienteRepository;
import com.fiap.sprint.domain.sinistro.*;
import com.fiap.sprint.domain.tratamento.*;
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
@RequestMapping("/sinistros")
public class SinistroController {

    @Autowired
    private SinistroService sinistroService;

    @PostMapping
    public ResponseEntity<SinistroDTO> cadastrar(@RequestBody @Valid SinistroDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var sinistro = sinistroService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/sinistros/{id}").buildAndExpand(sinistro.getId()).toUri();
        return ResponseEntity.created(uri).body(new SinistroDTO(sinistro));
    }

    @GetMapping
    public ResponseEntity<Page<ListaSinistroDTO>> listar(@PageableDefault(size = 10, sort = {"status"}) Pageable paginacao) {
        var page = sinistroService.listar(paginacao).map(ListaSinistroDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<ListaSinistroDTO> atualizar(@RequestBody @Valid AttSinistroDTO dados) {
        var sinistro = sinistroService.atualizar(dados);
        return ResponseEntity.ok(new ListaSinistroDTO(sinistro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        sinistroService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaSinistroDTO> detalhar(@PathVariable Long id) {
        var sinistro = sinistroService.detalhar(id);
        return ResponseEntity.ok(new ListaSinistroDTO(sinistro));
    }
}