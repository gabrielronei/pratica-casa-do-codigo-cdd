package br.com.gabriels.praticacasadocodigocdd.autor;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.CampoUnico;

import javax.validation.constraints.*;

class NovoAutorRequest {

    @NotBlank
    @Email
    @CampoUnico(classeDominio = Autor.class, nomeCampo = "email", message = "Ja existe um autor com este nome!")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public NovoAutorRequest(String email, String nome, String descricao) {
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    Autor toModel() {
        return new Autor(this.email, this.nome, this.descricao);
    }
}
