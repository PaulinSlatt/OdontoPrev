package com.fiap.sprint.domain.tratamento;


import jakarta.validation.constraints.NotNull;

public record AttTratamentoDTO(@NotNull Long id, String descricao, String custo, Tipo tipo) {






}
