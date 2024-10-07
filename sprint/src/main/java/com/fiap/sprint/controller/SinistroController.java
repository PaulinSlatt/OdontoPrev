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
    private SinistroRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DTOSinistro> Cadastrar(@RequestBody @Valid DTOSinistro dados, UriComponentsBuilder uriComponentsBuilder) {

        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Tratamento tratamento = tratamentoRepository.findById(dados.tratamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Tratamento não encontrado"));

        var sinistro = new Sinistro(paciente, tratamento, dados);

        repository.save(sinistro);

        var uri = uriComponentsBuilder.path("/sinistros/{id}").buildAndExpand(sinistro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTOSinistro(sinistro));
    }


    @GetMapping
    public ResponseEntity<Page<DTOListaSinistro>> Listar(@PageableDefault(size = 10, sort = {"status"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DTOListaSinistro::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListaSinistro> Atualizar(@RequestBody @Valid DTOAttSinistro dados) {
        var sinistro = repository.findById(dados.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sinistro não encontrado"));
        sinistro.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DTOListaSinistro(sinistro));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Excluir(@PathVariable Long id) {
        var sinistro = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sinistro não encontrado"));
        sinistro.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListaSinistro> Detalhar(@PathVariable Long id) {
        var sinistro = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sinistro não encontrado"));
        return ResponseEntity.ok(new DTOListaSinistro(sinistro));
    }
}