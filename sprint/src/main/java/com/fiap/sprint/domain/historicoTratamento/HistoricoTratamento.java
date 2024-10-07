package com.fiap.sprint.domain.historicoTratamento;

import com.fiap.sprint.domain.paciente.Paciente;
import com.fiap.sprint.domain.tratamento.Tratamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "historico_tratamento")
@Entity(name = "HistoricoTratamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class HistoricoTratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratamento_id")
    private Tratamento tratamento;


    private LocalDateTime data_tratamento = LocalDateTime.now();

    private String observacao;
    private boolean ativo;




    public HistoricoTratamento(Paciente paciente, Tratamento tratamento, DTOHistorico dados) {
        this.paciente = paciente;
        this.tratamento = tratamento;
        this.data_tratamento = getData_tratamento();
        this.observacao = dados.observacao();
        this.ativo = true;
    }

    public void atualizarInformacoes(DTOAttHistorico dados) {
        if (dados.observacao() != null) {
            this.observacao = dados.observacao();
        }
    }


    public void excluir() {
        this.ativo = false;
    }


    public Long getPacienteId() {
        return paciente != null ? paciente.getId() : null;
    }

    public Long getTratamentoId() {
        return tratamento != null ? tratamento.getId() : null;
    }
}