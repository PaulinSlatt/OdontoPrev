package com.fiap.sprint.domain.tratamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//cLASSE PARA TRANSFERENCIA DE DADOS
public record DTOTratamento( @NotBlank String descricao, @NotBlank String custo, @NotNull Tipo tipo){


    public DTOTratamento(Tratamento tratamento) {
        this( tratamento.getDescricao(), tratamento.getCusto(), tratamento.getTipo());
    }
}
