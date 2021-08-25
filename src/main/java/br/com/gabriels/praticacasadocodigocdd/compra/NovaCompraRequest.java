package br.com.gabriels.praticacasadocodigocdd.compra;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.cpfOuCnpj.CPFouCNPJ;
import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.existeId.ExisteId;
import br.com.gabriels.praticacasadocodigocdd.cupom.Cupom;
import br.com.gabriels.praticacasadocodigocdd.pais.Pais;
import br.com.gabriels.praticacasadocodigocdd.pais.estado.Estado;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static java.util.function.Predicate.not;

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

    @NotNull
    @Positive
    @Min(1)
    private BigDecimal total;

    @Valid
    @Size(min = 1)
    private Set<NovoItemCompraRequest> itens = new HashSet<>();

    @ExisteId(classeDominio = Cupom.class, nomeCampo = "codigo", obrigatorio = false)
    private String codigoCupom;

    public NovaCompraRequest(String email, String nome, String sobrenome, String documento, String endereco, String complemento,
                             String cidade, Long paisId, Long estadoId, String telefone, String cep, BigDecimal total,
                             Set<NovoItemCompraRequest> itens, String codigoCupom) {
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
        this.total = total;
        this.itens = itens;
        this.codigoCupom = codigoCupom;
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

    public Set<Long> getItensIds() {
        return itens.stream().map(NovoItemCompraRequest::getLivroId).collect(Collectors.toSet());
    }

    public Set<NovoItemCompraRequest> getItens() {
        return itens;
    }

    public BigDecimal calculaValorTotal(Map<Long, BigDecimal> livros) {
        return this.itens.stream().map(item -> item.calculaValor(livros.get(item.getLivroId())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean totalDiferente(BigDecimal totalServidor) {
        return this.total.compareTo(totalServidor) != 0;
    }

    public Optional<String> getCodigoCupom() {
        return Optional.ofNullable(codigoCupom).filter(not(String::isBlank));
    }

    public Compra toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, this.paisId);

        Set<ItemCompra> itens = this.getItens().stream().map(novoItem -> novoItem.toModel(manager)).collect(Collectors.toSet());

        Compra compra = new Compra(this.email, this.nome, this.sobrenome, this.documento, this.endereco, this.complemento, this.cidade, pais,
                this.telefone, this.cep, this.total, itens);

        if (this.temEstadoId()) {
            Estado estado = manager.find(Estado.class, this.estadoId);
            compra.setEstado(estado);
        }

        if (this.getCodigoCupom().isPresent()) {
            Cupom cupom = manager.createQuery("SELECT c FROM Cupom c where c.codigo = :codigo", Cupom.class)
                    .setParameter("codigo", this.codigoCupom).getSingleResult();
            compra.aplica(cupom);
        }

        return compra;
    }
}
