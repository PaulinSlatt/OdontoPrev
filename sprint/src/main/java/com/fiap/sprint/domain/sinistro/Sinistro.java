package com.fiap.sprint.domain.sinistro;

import com.fiap.sprint.domain.paciente.Paciente;
import com.fiap.sprint.domain.tratamento.Tratamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "sinistros")
@Entity(name = "Sinistro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratamento_id")
    private Tratamento tratamento;

    private String descricao;
    private LocalDateTime data_ocorrencia = LocalDateTime.now();
    private String reembolso;
    private String status;
    private boolean ativo;




    public Sinistro(Paciente paciente, Tratamento tratamento, DTOSinistro dados) {
        this.paciente = paciente;
        this.tratamento = tratamento;
        this.descricao = dados.descricao();
        this.data_ocorrencia = getData_ocorrencia();
        this.reembolso = dados.reembolso();
        this.status = dados.status();
        this.ativo = true;
    }

    public void atualizarInformacoes(DTOAttSinistro dados) {
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }

        if (dados.reembolso() != null) {
           this.reembolso = dados.reembolso();
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