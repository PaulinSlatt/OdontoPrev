package com.fiap.sprint.domain.paciente;
import com.fiap.sprint.domain.endereço.DTOEndereco;
import com.fiap.sprint.domain.endereço.Endereco;

import java.time.LocalDate;

public record DTODetalharPaciente(String nome, String email, LocalDate data_nascimento, Genero genero , String telefone, Endereco endereco) {
    public DTODetalharPaciente(Paciente paciente) {
       this(paciente.getNome(), paciente.getEmail(), paciente.getData_nascimento(), paciente.getGenero(), paciente.getTelefone(), paciente.getEndereco());
    }


}
