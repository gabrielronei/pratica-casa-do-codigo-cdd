package br.com.gabriels.praticacasadocodigocdd.livro;

import br.com.gabriels.praticacasadocodigocdd.autor.Autor;
import br.com.gabriels.praticacasadocodigocdd.categoria.Categoria;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.util.Assert.*;

@Entity
class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
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
    @Column(unique = true, nullable = false)
    private String isbn;

    @NotNull
    @Future
    private LocalDate dataLancamento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(String titulo, String resumo, String sumario, BigDecimal preco,
                 int numeroPaginas, String isbn, LocalDate dataLancamento, Categoria categoria, Autor autor) {
        hasText(titulo, "Titulo precisa ser preenchido!");
        hasText(resumo, "Resumo precisa ser preenchido!");
        hasText(sumario, "Sumario precisa ser preenchido!");
        notNull(preco, "O preço não pode ser nulo!");
        hasText(isbn, "Isbn não pode estar em branco!");
        notNull(dataLancamento, "Data Lançamento não pode ser nula!");
        notNull(categoria, "Categoria não pode ser nula!");
        notNull(autor, "Autor não pode ser nula!");

        state(resumo.length() <= 500, "O resumo pode ter no maximo 500 caracteres");
        state(BigDecimal.valueOf(20).compareTo(preco) < 1, "O preco tem que ser de no minimo 20!");
        state(numeroPaginas >= 100, "O numero de paginas tem que ser no minimo 100!");


        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNomeAutor() {
        return autor.getNome();
    }

    public String getDescricaoAutor() {
        return autor.getDescricao();
    }
}
