package br.com.gabriels.praticacasadocodigocdd.pais.estado;

import br.com.gabriels.praticacasadocodigocdd.pais.Pais;

import javax.persistence.*;
import javax.validation.constraints.*;

import static org.springframework.util.Assert.*;

@Entity
class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        hasText(nome, "O nome do estado é obrigatorio!");
        notNull(pais, "O pais é obrigatorio!");

        this.nome = nome;
        this.pais = pais;
    }
}
