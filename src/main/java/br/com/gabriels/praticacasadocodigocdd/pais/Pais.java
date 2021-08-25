package br.com.gabriels.praticacasadocodigocdd.pais;

import br.com.gabriels.praticacasadocodigocdd.pais.estado.Estado;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

import static org.springframework.util.Assert.hasText;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @OneToMany
    @JoinColumn(name = "pais_id")
    private List<Estado> estados = new ArrayList();

    @Deprecated
    public Pais() {
    }

    public Pais(String nome) {
        hasText(nome, "O nome do pais é obrigatorio!");

        this.nome = nome;
    }

    public boolean possuiEstados() {
        return !this.estados.isEmpty();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return nome.equals(pais.nome);
    }

    @Override
    public int hashCode() {
        return 31 * this.nome.hashCode();
    }
}
