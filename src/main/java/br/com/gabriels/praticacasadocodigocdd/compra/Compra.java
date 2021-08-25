package br.com.gabriels.praticacasadocodigocdd.compra;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.cpfOuCnpj.CPFouCNPJ;
import br.com.gabriels.praticacasadocodigocdd.cupom.Cupom;
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
    private BigDecimal precoTotalBruto;

    @Size(min = 1)
    @NotNull
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "compra_id")
    private Set<ItemCompra> itens = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    @Deprecated
    public Compra() {
    }

    public Compra(String email, String nome, String sobrenome, String documento,
                  String endereco, String complemento, String cidade, Pais pais,
                  String telefone, String cep, BigDecimal precoTotalBruto, Set<ItemCompra> itens) {
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
        notNull(precoTotalBruto, "O precoTotal não pode estar em branco!");
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
        this.precoTotalBruto = precoTotalBruto;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getNomePais() {
        return pais.getNome();
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public BigDecimal getPrecoTotalBruto() {
        return precoTotalBruto;
    }

    public Set<ItemCompra> getItens() {
        return itens;
    }

    public Optional<String> getNomeEstado() {
        return Optional.ofNullable(estado).map(Estado::getNome);
    }


    public Optional<String> getCupomAplicado() {
        return Optional.ofNullable(cupom).map(Cupom::getCodigo);
    }

    public boolean temCupom() {
        return Optional.ofNullable(cupom).isPresent();
    }

    public Optional<BigDecimal> getPrecoTotalLiquido() {
        return Optional.ofNullable(cupom).map(c -> c.getValorDescontado(precoTotalBruto));
    }

    public void setEstado(Estado estado) {
        isTrue(estado.pertence(pais), "Desculpa, esse estado não pertence ao pais definido!");
        this.estado = estado;
    }

    public void aplica(Cupom cupom) {
        isNull(this.cupom, "Essa compra já possui um cupom, você não pode definir outro!");
        notNull(cupom, "O cupom passado não pode estar nulo!");
        isTrue(cupom.estaValido(), "O cupom precisa estar valido!");

        this.cupom = cupom;
    }
}
