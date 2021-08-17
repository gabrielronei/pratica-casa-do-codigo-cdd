package br.com.gabriels.praticacasadocodigocdd.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;

@RestController
class NovoAutorController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/autores")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {

        manager.persist(novoAutorRequest.toModel());
        return ResponseEntity.ok().build();
    }
}
