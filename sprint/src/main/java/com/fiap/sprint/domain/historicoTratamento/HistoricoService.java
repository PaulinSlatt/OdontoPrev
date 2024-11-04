package com.fiap.sprint.domain.historicoTratamento;

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
public class HistoricoService {

    @Autowired
    private HistoricoTratamentoRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Transactional
    public HistoricoTratamento cadastrar(HistoricoDTO dados) {
        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Tratamento tratamento = tratamentoRepository.findById(dados.tratamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Tratamento não encontrado"));

        var historico = new HistoricoTratamento(paciente, tratamento, dados);
        return repository.save(historico);
    }

    public Page<HistoricoTratamento> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public HistoricoTratamento atualizar(AttHistoricoDTO dados) {
        var historico = repository.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Historico não encontrado"));
        historico.atualizarInformacoes(dados);
        return historico;
    }

    @Transactional
    public void excluir(Long id) {
        var historico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historico não encontrado"));
        historico.excluir();
    }

    public HistoricoTratamento detalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historico não encontrado"));
    }
}