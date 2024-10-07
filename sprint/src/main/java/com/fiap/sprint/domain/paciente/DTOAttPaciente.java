package com.fiap.sprint.domain.paciente;


import com.fiap.sprint.domain.endereço.DTOEndereco;
import jakarta.validation.constraints.NotNull;

public record DTOAttPaciente(@NotNull Long id, String nome, String telefone, String email, DTOEndereco endereco) {


}
