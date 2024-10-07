package com.fiap.sprint.domain.tratamento;


import jakarta.validation.constraints.NotNull;

public record DTOAttTratamento(@NotNull Long id, String descricao, String custo, Tipo tipo) {






}
