package com.fiap.sprint.domain.paciente;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.sprint.domain.endereço.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record CadastroPacienteDTO(@NotBlank String nome, @NotBlank @Email String email, @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") LocalDate data_nascimento, Genero genero , @NotBlank String telefone, @NotNull @Valid EnderecoDTO endereco) {



}
