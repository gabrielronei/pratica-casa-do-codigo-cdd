package br.com.gabriels.praticacasadocodigocdd.pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static org.springframework.util.Assert.hasText;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @Deprecated
    public Pais() {
    }

    public Pais(String nome) {
        hasText(nome, "O nome do pais é obrigatorio!");

        this.nome = nome;
    }
}
