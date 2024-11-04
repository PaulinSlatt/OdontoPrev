package com.fiap.sprint.domain.sinistro;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

//cLASSE PARA TRANSFERENCIA DE DADOS
public record SinistroDTO(@NotNull Long pacienteId, @NotNull Long tratamentoId, @NotBlank String descricao, @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") LocalDate data_ocorrido, @NotBlank String reembolso, @NotBlank String status){


    public SinistroDTO(Sinistro sinistro) {
        this(
                sinistro.getPaciente() != null ? sinistro.getPaciente().getId() : null,
                sinistro.getTratamento() != null ? sinistro.getTratamento().getId() : null,
                sinistro.getDescricao(),
                LocalDate.from(sinistro.getData_ocorrencia()),
                sinistro.getReembolso() != null ? sinistro.getReembolso().toString() : null,
                sinistro.getStatus().toString()
        );
    }
}
