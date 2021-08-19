package br.com.gabriels.praticacasadocodigocdd.autor;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.campoUnico.CampoUnico;

import javax.validation.constraints.*;

final class NovoAutorRequest {

    @NotBlank
    @Email
    @CampoUnico(classeDominio = Autor.class, nomeCampo = "email", message = "Ja existe um autor com este nome!")
    private final String email;

    @NotBlank
    private final String nome;

    @NotBlank
    @Size(max = 400)
    private final String descricao;

    public NovoAutorRequest(String email, String nome, String descricao) {
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    Autor toModel() {
        return new Autor(this.email, this.nome, this.descricao);
    }
}
