package com.fiap.sprint.domain.tratamento;


import jakarta.persistence.*;
import lombok.*;



// Classe para representar o BD e fazer a persistência JPA

@Table(name = "tratamentos")
@Entity(name = "Tratamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String custo;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private boolean ativo;



    public Tratamento(DTOTratamento dados) {

        this.descricao = dados.descricao();
        this.custo = dados.custo();
        this.tipo = dados.tipo();
        this.ativo = true;
    }

    public Tratamento(Long aLong) {
    }


    public void atualizarInformacoes(DTOAttTratamento dados) {
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.custo() != null) {
            this.custo = dados.custo();
        }
        if (dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
    }

    public void excluir() {
        this.ativo = false;
    }


}