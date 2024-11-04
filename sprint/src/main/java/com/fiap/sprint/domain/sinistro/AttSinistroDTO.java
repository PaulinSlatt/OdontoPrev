package com.fiap.sprint.domain.sinistro;


import jakarta.validation.constraints.NotNull;

public record AttSinistroDTO(@NotNull Long id, String descricao, String reembolso , String status) {






}
