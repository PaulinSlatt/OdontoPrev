package com.fiap.sprint.domain.sinistro;

import com.fiap.sprint.domain.paciente.Paciente;
import com.fiap.sprint.domain.paciente.PacienteRepository;
import com.fiap.sprint.domain.tratamento.Tratamento;
import com.fiap.sprint.domain.tratamento.TratamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SinistroService {

    @Autowired
    private SinistroRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Transactional
    public Sinistro cadastrar(SinistroDTO dados) {
        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Tratamento tratamento = tratamentoRepository.findById(dados.tratamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Tratamento não encontrado"));

        var sinistro = new Sinistro(paciente, tratamento, dados);
        return repository.save(sinistro);
    }

    public Page<Sinistro> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public Sinistro atualizar(AttSinistroDTO dados) {
        var sinistro = repository.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Sinistro não encontrado"));
        sinistro.atualizarInformacoes(dados);
        return sinistro;
    }

    @Transactional
    public void excluir(Long id) {
        var sinistro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sinistro não encontrado"));
        sinistro.excluir();
    }

    public Sinistro detalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sinistro não encontrado"));
    }
}