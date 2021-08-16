package br.com.gabriels.praticacasadocodigocdd.autor;

import javax.persistence.*;
import javax.validation.constraints.*;

import static org.springframework.util.Assert.*;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    @Deprecated
    public Autor() {
    }

    public Autor(String email, String nome, String descricao) {
        hasText(email, "Email precisa ser preenchido!");
        hasText(nome, "Nome precisa ser preenchido!");
        hasText(descricao, "Descricao precisa ser preenchido!");
        state(descricao.length() <= 400, "A descrição pode ter no maximo 400 caracteres");

        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }
}
