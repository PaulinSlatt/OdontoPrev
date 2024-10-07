package com.fiap.sprint.domain.historicoTratamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

//cLASSE PARA TRANSFERENCIA DE DADOS
public record DTOHistorico(@NotNull Long pacienteId, @NotNull Long tratamentoId, @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") LocalDate data_tratamento, @NotBlank String observacao){


    public DTOHistorico(HistoricoTratamento historico) {
        this(
                historico.getPaciente() != null ? historico.getPaciente().getId() : null,
                historico.getTratamento() != null ? historico.getTratamento().getId() : null,
                LocalDate.from(historico.getData_tratamento()),
                historico.getObservacao()
        );
    }
}
