package br.com.gabriels.praticacasadocodigocdd.livro;

final class ListaLivroOutputDTO {

    private final Long id;
    private final String titulo;

    public ListaLivroOutputDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
