package br.com.gabriels.praticacasadocodigocdd.compra;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.cpfOuCnpj.CPFouCNPJ;
import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.existeId.ExisteId;
import br.com.gabriels.praticacasadocodigocdd.pais.Pais;
import br.com.gabriels.praticacasadocodigocdd.pais.estado.Estado;

import javax.validation.constraints.*;

import static java.util.Objects.nonNull;

class NovaCompraRequest {

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
    @ExisteId(classeDominio = Pais.class)
    private Long paisId;

    @ExisteId(classeDominio = Estado.class, obrigatorio = false)
    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;


    public NovaCompraRequest(String email, String nome, String sobrenome, String documento, String endereco, String complemento,
                             String cidade, Long paisId, Long estadoId, String telefone, String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public boolean temEstadoId() {
        return nonNull(estadoId);
    }
}
