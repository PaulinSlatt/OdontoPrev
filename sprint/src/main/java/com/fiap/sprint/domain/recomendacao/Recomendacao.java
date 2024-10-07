package com.fiap.sprint.domain.recomendacao;

import com.fiap.sprint.domain.paciente.Paciente;
import com.fiap.sprint.domain.tratamento.Tratamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "recomendacao")
@Entity(name = "Recomendacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratamento_id")
    private Tratamento tratamento;

    private LocalDateTime data_recomendacao = LocalDateTime.now();

    private String motivo;
    private boolean ativo;




    public Recomendacao(Paciente paciente, Tratamento tratamento, DTORecomendacao dados) {
        this.paciente = paciente;
        this.tratamento = tratamento;
        this.data_recomendacao = getData_recomendacao();
        this.motivo = dados.motivo();
        this.ativo = true;
    }

    public void atualizarInformacoes(DTOAttRecomendacao dados) {
        if (dados.motivo() != null) {
            this.motivo = dados.motivo();
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