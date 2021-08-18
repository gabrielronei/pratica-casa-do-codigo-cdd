package br.com.gabriels.praticacasadocodigocdd.pais.estado;

import br.com.gabriels.praticacasadocodigocdd.pais.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;

class NovoEstadoRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long paisId;

    public NovoEstadoRequest(String nome, Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Estado toModel(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, this.paisId);

        return new Estado(this.nome, pais);
    }
}
