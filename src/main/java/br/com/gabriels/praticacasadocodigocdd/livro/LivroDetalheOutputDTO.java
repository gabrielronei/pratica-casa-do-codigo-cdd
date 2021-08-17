package br.com.gabriels.praticacasadocodigocdd.livro;

import java.math.BigDecimal;

final class LivroDetalheOutputDTO {

    private final String titulo;
    private final String resumo;
    private final BigDecimal preco;
    private final String sumario;
    private final String nomeAutor;
    private final String descricaoAutor;
    private final int numeroPaginas;
    private final String isbn;

    public LivroDetalheOutputDTO(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.preco = livro.getPreco();
        this.sumario = livro.getSumario();
        this.nomeAutor = livro.getNomeAutor();
        this.descricaoAutor = livro.getDescricaoAutor();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getSumario() {
        return sumario;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getDescricaoAutor() {
        return descricaoAutor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }
}
