package com.fiap.sprint.domain.tratamento;

public record DTOListaTratamento(Long id, String descricao, String custo, Tipo tipo) {

    public DTOListaTratamento(Tratamento tratamento) {
        this(tratamento.getId(), tratamento.getDescricao(), tratamento.getCusto(), tratamento.getTipo());
    }

}
