package com.fiap.sprint.domain.paciente;
import com.fiap.sprint.domain.endereço.Endereco;

import java.time.LocalDate;

public record DetalharPacienteDTO(String nome, String email, LocalDate data_nascimento, Genero genero , String telefone, Endereco endereco) {
    public DetalharPacienteDTO(Paciente paciente) {
       this(paciente.getNome(), paciente.getEmail(), paciente.getData_nascimento(), paciente.getGenero(), paciente.getTelefone(), paciente.getEndereco());
    }


}
