package com.fiap.sprint.domain.paciente;

public record ListaPacienteDTO(Long id, String nome, String email) {

    public ListaPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }


}
