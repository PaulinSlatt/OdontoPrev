package com.fiap.sprint.domain.recomendacao;

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
public class RecomendacaoService {

    @Autowired
    private RecomendacaoRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Transactional
    public Recomendacao cadastrar(RecomendacaoDTO dados) {
        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Tratamento tratamento = tratamentoRepository.findById(dados.tratamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Tratamento não encontrado"));

        var recomendacao = new Recomendacao(paciente, tratamento, dados);
        return repository.save(recomendacao);
    }

    public Page<Recomendacao> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public Recomendacao atualizar(AttRecomendacaoDTO dados) {
        var recomendacao = repository.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Recomendacao não encontrada"));
        recomendacao.atualizarInformacoes(dados);
        return recomendacao;
    }

    @Transactional
    public void excluir(Long id) {
        var recomendacao = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recomendacao não encontrada"));
        recomendacao.excluir();
    }

    public Recomendacao detalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recomendacao não encontrada"));
    }
}