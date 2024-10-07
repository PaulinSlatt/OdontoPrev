package com.fiap.sprint.domain.sinistro;


import jakarta.validation.constraints.NotNull;

public record DTOAttSinistro(@NotNull Long id, String descricao, String reembolso ,String status) {






}
