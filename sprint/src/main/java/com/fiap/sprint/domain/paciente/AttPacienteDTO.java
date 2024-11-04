package com.fiap.sprint.domain.paciente;


import com.fiap.sprint.domain.endereço.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AttPacienteDTO(@NotNull Long id, String nome, String telefone, String email, EnderecoDTO endereco) {


}
