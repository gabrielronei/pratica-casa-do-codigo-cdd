package br.com.gabriels.praticacasadocodigocdd.categoria;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.CampoUnico;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

final class NovaCategoriaRequest {

    @NotBlank
    @CampoUnico(classeDominio = Categoria.class, nomeCampo = "nome", message = "Ja existe uma Categoria com este nome!")
    private final String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCategoriaRequest(String nome) {
        this.nome = nome;
    }

    Categoria toModel() {
        return new Categoria(this.nome);
    }
}
