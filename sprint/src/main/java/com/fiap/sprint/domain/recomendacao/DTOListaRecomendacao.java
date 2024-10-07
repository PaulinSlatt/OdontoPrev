package com.fiap.sprint.domain.recomendacao;

import java.time.LocalDate;

public record DTOListaRecomendacao(
        Long id,
        Long pacienteId,
        Long tratamentoId,
        LocalDate data_recomendacao,
        String motivo) {

    public DTOListaRecomendacao(Recomendacao recomendacao) {
        this(
                recomendacao.getId(),
                recomendacao.getPaciente() != null ? recomendacao.getPaciente().getId() : null,
                recomendacao.getTratamento() != null ? recomendacao.getTratamento().getId() : null,
                LocalDate.from(recomendacao.getData_recomendacao()),
                recomendacao.getMotivo() != null ? recomendacao.getMotivo().toString() : null
        );
    }
}