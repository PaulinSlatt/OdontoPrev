package com.fiap.sprint.controller;

import com.fiap.sprint.domain.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroPacienteDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = pacienteService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ListaPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListaPacienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = pacienteService.listar(paginacao).map(ListaPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody @Valid AttPacienteDTO dados) {
        var paciente = pacienteService.atualizar(dados);
        return ResponseEntity.ok(new DetalharPacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        pacienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var paciente = pacienteService.detalhar(id);
        return ResponseEntity.ok(new DetalharPacienteDTO(paciente));
    }
}