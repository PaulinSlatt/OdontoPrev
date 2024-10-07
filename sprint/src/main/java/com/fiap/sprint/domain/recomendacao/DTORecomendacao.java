package com.fiap.sprint.domain.recomendacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

//cLASSE PARA TRANSFERENCIA DE DADOS
public record DTORecomendacao(@NotNull Long pacienteId, @NotNull Long tratamentoId, @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") LocalDate data_recomendacao, @NotBlank String motivo){


    public DTORecomendacao(Recomendacao recomendacao) {
        this(
                recomendacao.getPaciente() != null ? recomendacao.getPaciente().getId() : null,
                recomendacao.getTratamento() != null ? recomendacao.getTratamento().getId() : null,
                LocalDate.from(recomendacao.getData_recomendacao()),
                recomendacao.getMotivo()
        );
    }
}
