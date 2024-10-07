package com.fiap.sprint.domain.paciente;

public record DTOListaPaciente(Long id, String nome, String email) {

    public DTOListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }


}
