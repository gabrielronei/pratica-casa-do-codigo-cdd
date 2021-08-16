package br.com.gabriels.praticacasadocodigocdd.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;

@RestController
public class NovoLivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/livro")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoLivroRequest novoLivroRequest) {
        entityManager.persist(novoLivroRequest.toModel(entityManager));
        return ResponseEntity.ok().build();
    }

}
