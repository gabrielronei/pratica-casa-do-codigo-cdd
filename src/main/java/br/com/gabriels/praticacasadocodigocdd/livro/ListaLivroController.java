package br.com.gabriels.praticacasadocodigocdd.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.List;

@RestController
public class ListaLivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/livro")
    public ResponseEntity listar() {
        List<Livro> livros = entityManager.createQuery("SELECT l FROM livro l").getResultList();

        return ResponseEntity.ok().body(livros.stream().map(ListaLivroOutputDTO::new));
    }
}
