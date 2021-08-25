package br.com.gabriels.praticacasadocodigocdd.compra;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.cpfOuCnpj.CPFouCNPJ;
import br.com.gabriels.praticacasadocodigocdd.pais.Pais;
import br.com.gabriels.praticacasadocodigocdd.pais.estado.Estado;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

import static br.com.gabriels.praticacasadocodigocdd.compra.Status.INICIADA;
import static org.springframework.util.Assert.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CPFouCNPJ
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = INICIADA;

    @NotNull
    @Positive
    private BigDecimal precoTotal;

    @Size(min = 1)
    @NotNull
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "compra_id")
    private Set<ItemCompra> itens = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Compra(String email, String nome, String sobrenome, String documento,
                  String endereco, String complemento, String cidade, Pais pais,
                  String telefone, String cep, BigDecimal precoTotal, Set<ItemCompra> itens) {
        hasText(email, "O email não pode estar em branco!");
        hasText(nome, "O nome não pode estar em branco!");
        hasText(sobrenome, "O sobrenome não pode estar em branco!");
        hasText(documento, "O Documento não pode estar em branco!");
        hasText(endereco, "O Documento não pode estar em branco!");
        hasText(complemento, "O Documento não pode estar em branco!");
        hasText(cidade, "O Documento não pode estar em branco!");
        notNull(pais, "O Pais não pode estar em branco!");
        hasText(telefone, "O telefone não pode estar em branco!");
        hasText(cep, "O telefone não pode estar em branco!");
        notNull(precoTotal, "O precoTotal não pode estar em branco!");
        state(itens.size() > 0, "Quantidade precisa ser maior que 0!");


        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.precoTotal = precoTotal;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setEstado(Estado estado) {
        isTrue(estado.pertence(pais), "Desculpa, esse estado não pertence ao pais definido!");
        this.estado = estado;
    }
}
