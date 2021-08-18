package br.com.gabriels.praticacasadocodigocdd.pais;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.CampoUnico;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

class NovoPaisRequest {

    @NotBlank
    @CampoUnico(classeDominio = Pais.class, nomeCampo = "nome", message = "Já existe um pais com este nome!")
    private String nome;

    @JsonCreator(mode = PROPERTIES)
    public NovoPaisRequest(String nome) {
        this.nome = nome;
    }

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
