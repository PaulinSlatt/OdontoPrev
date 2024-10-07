package com.fiap.sprint.domain.historicoTratamento;

import java.time.LocalDate;

public record DTOListaHistorico(
        Long id,
        Long pacienteId,
        Long tratamentoId,
        LocalDate data_tratamento,
        String motivo) {

    public DTOListaHistorico(HistoricoTratamento historico) {
        this(
                historico.getId(),
                historico.getPaciente() != null ? historico.getPaciente().getId() : null,
                historico.getTratamento() != null ? historico.getTratamento().getId() : null,
                LocalDate.from(historico.getData_tratamento()),
                historico.getObservacao() != null ? historico.getObservacao().toString() : null
        );
    }
}