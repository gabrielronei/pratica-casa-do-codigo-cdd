package br.com.gabriels.praticacasadocodigocdd.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static org.springframework.util.Assert.hasText;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        hasText(nome, "O nome da categoria é obrigatorio!");

        this.nome = nome;
    }
}
