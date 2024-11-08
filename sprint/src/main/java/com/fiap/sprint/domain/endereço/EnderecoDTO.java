package com.fiap.sprint.domain.endereço;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//cLASSE PARA TRANSFERENCIA DE DADOS
public record EnderecoDTO(@NotBlank
                          String logradouro,
                          @NotBlank
                          String bairro,
                          @NotBlank
                          @Pattern(regexp = "\\d{8}")
                          String cep,
                          @NotBlank
                          String cidade,
                          @NotBlank
                          String uf,
                          String complemento,
                          String numero) {
}
