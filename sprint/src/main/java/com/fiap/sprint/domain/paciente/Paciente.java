package com.fiap.sprint.domain.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.sprint.domain.endereço.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate data_nascimento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DTOCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.data_nascimento = dados.data_nascimento();
        this.genero = dados.genero();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public Paciente(Long aLong) {
    }


    public void AtualizarInformacoes(DTOAttPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    public void Excluir() {

        this.ativo = false;
    }

}
