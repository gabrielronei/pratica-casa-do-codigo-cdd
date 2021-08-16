package br.com.gabriels.praticacasadocodigocdd.livro;

import br.com.gabriels.praticacasadocodigocdd.autor.Autor;
import br.com.gabriels.praticacasadocodigocdd.categoria.Categoria;
import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.CampoUnico;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.util.Objects.nonNull;
import static org.springframework.util.Assert.state;

public class NovoLivroRequest {

    @NotBlank
    @CampoUnico(classeDominio = Livro.class, nomeCampo = "titulo", message = "Já existe um livro com este titulo!")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    @NotBlank
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @Min(100)
    private int numeroPaginas;

    @NotBlank
    @CampoUnico(classeDominio = Livro.class, nomeCampo = "isbn", message = "Já existe um livro com este isbn!")
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long autorId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoLivroRequest(String titulo, String resumo, String sumario, BigDecimal preco,
                            int numeroPaginas, String isbn, LocalDate dataLancamento, Long categoriaId, Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro toModel(EntityManager entityManager) {
        Categoria categoria = entityManager.find(Categoria.class, this.categoriaId);
        Autor autor = entityManager.find(Autor.class, this.autorId);

        state(nonNull(categoria), "A categoria não pode estar nula!");
        state(nonNull(autor), "O autor não pode estar nulo!");

        return new Livro(this.titulo, this.resumo, this.sumario, this.preco,
                this.numeroPaginas, this.isbn, this.dataLancamento, categoria, autor);
    }
}
