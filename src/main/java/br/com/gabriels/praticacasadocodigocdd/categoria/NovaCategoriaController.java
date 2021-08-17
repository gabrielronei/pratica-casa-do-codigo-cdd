package br.com.gabriels.praticacasadocodigocdd.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;

@RestController
class NovaCategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/categorias")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {

        entityManager.persist(novaCategoriaRequest.toModel());
        return ResponseEntity.ok().build();
    }
}
