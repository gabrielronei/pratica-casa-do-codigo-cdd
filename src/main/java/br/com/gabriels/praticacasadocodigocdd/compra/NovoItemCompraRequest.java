package br.com.gabriels.praticacasadocodigocdd.compra;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.existeId.ExisteId;
import br.com.gabriels.praticacasadocodigocdd.livro.Livro;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;

class NovoItemCompraRequest {

    @NotNull
    @ExisteId(classeDominio = Livro.class)
    private Long livroId;

    @NotNull
    @Positive
    private int quantidade;

    public NovoItemCompraRequest(Long livroId, int quantidade) {
        this.livroId = livroId;
        this.quantidade = quantidade;
    }

    public Long getLivroId() {
        return livroId;
    }

    public BigDecimal calculaValor(BigDecimal precoOriginal) {
        return precoOriginal.multiply(new BigDecimal(quantidade));
    }

    public ItemCompra toModel(EntityManager entityManager) {
        Livro livro = entityManager.find(Livro.class, livroId);

        return new ItemCompra(livro, this.quantidade);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NovoItemCompraRequest that = (NovoItemCompraRequest) o;
        return livroId.equals(that.livroId);
    }

    @Override
    public int hashCode() {
        return 31 * livroId.hashCode();
    }
}
