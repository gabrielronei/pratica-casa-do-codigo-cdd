package br.com.gabriels.praticacasadocodigocdd.compra;

import java.math.BigDecimal;
import java.util.*;

class CompraDetalhe {

    private final String email;
    private final String nome;
    private final String sobrenome;
    private final String documento;
    private final String endereco;
    private final String complemento;
    private final String cidade;
    private final String pais;
    private final String estado;
    private final String telefone;
    private final String cep;
    private final BigDecimal precoTotalBruto;
    private final BigDecimal precoTotalLiquido;
    private final List<ItemCompraDetalhe> itens;
    private final boolean temCupom;
    private final String cupomAplicado;

    public CompraDetalhe(Compra compra) {
        this.email = compra.getEmail();
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.documento = compra.getDocumento();
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.cidade = compra.getCidade();
        this.pais = compra.getNomePais();
        this.estado = compra.getNomeEstado().orElse("");
        this.telefone = compra.getTelefone();
        this.cep = compra.getCep();
        this.precoTotalBruto = compra.getPrecoTotalBruto();
        this.temCupom = compra.temCupom();
        this.precoTotalLiquido = compra.getPrecoTotalLiquido().orElse(null);
        this.cupomAplicado = compra.getCupomAplicado().orElse("");
        this.itens = compra.getItens().stream().map(ItemCompraDetalhe::new).toList();
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

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
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

    public BigDecimal getPrecoTotalLiquido() {
        return precoTotalLiquido;
    }

    public List<ItemCompraDetalhe> getItens() {
        return itens;
    }

    public String getCupomAplicado() {
        return cupomAplicado;
    }

    public boolean isTemCupom() {
        return temCupom;
    }
}
