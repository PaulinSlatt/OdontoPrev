package com.fiap.sprint.domain.sinistro;

public enum Status {
    APROVADO,
    NEGADO,
    EM_ANALISE;


    public static Status fromString(String status) {
        switch (status.toUpperCase()) {
            case "APROVADO":
                return APROVADO;
            case "NEGADO":
                return NEGADO;
            case "EM ANÁLISE":
            case "EM_ANALISE":
                return EM_ANALISE;
            default:
                throw new IllegalArgumentException("Status desconhecido: " + status);
        }
    }
}
