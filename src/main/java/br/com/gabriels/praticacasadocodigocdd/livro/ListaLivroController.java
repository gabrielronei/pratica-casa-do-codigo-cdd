package br.com.gabriels.praticacasadocodigocdd.livro;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.validacao.exception.NaoEcontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.*;

@RestController
public class ListaLivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/livros")
    public ResponseEntity listar() {
        List<Livro> livros = entityManager.createQuery("SELECT l FROM Livro l").getResultList();

        return ResponseEntity.ok(livros.stream().map(ListaLivroOutputDTO::new));
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity listar(@PathVariable("id") Long id) {
        Livro livro = Optional.ofNullable(entityManager.find(Livro.class, id))
                .orElseThrow(NaoEcontradoException::new);

        return ResponseEntity.ok(new LivroDetalheOutputDTO(livro));
    }
}
