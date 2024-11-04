package com.fiap.sprint.domain.paciente;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    public Paciente cadastrar(CadastroPacienteDTO dados) {
        var paciente = new Paciente(dados);
        return repository.save(paciente);
    }

    public Page<Paciente> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public Paciente atualizar(AttPacienteDTO dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return paciente;
    }

    @Transactional
    public void excluir(Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }

    public Paciente detalhar(Long id) {
        return repository.getReferenceById(id);
    }
}