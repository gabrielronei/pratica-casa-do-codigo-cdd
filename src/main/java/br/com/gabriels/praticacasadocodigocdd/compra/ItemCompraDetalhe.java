package br.com.gabriels.praticacasadocodigocdd.compra;

import java.math.BigDecimal;

class ItemCompraDetalhe {

    private final String tituloLivro;
    private final int quantidade;
    private final BigDecimal precoUnitario;

    public ItemCompraDetalhe(ItemCompra itemCompra) {
        this.tituloLivro = itemCompra.getTituloLivro();
        this.quantidade = itemCompra.getQuantidade();
        this.precoUnitario = itemCompra.getPrecoUnitario();
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
}
