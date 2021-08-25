package br.com.gabriels.praticacasadocodigocdd.compra;

import br.com.gabriels.praticacasadocodigocdd.livro.Livro;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.util.Assert.*;

@Entity
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @NotNull
    @Positive
    private int quantidade;

    @NotNull
    private BigDecimal precoUnitario;

    @Deprecated
    public ItemCompra() {
    }

    public ItemCompra(Livro livro, int quantidade) {
        notNull(livro, "Livro não pode estar nulo!");
        state(quantidade > 0, "Quantidade precisa ser maior que 0!");

        this.livro = livro;
        this.precoUnitario = livro.getPreco();
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCompra that = (ItemCompra) o;
        return livro.equals(that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro);
    }
}
