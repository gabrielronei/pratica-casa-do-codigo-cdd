package br.com.gabriels.praticacasadocodigocdd.pais;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;

@RestController
class PaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/paises")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoPaisRequest novoPaisRequest) {

        entityManager.persist(novoPaisRequest.toModel());
        return ResponseEntity.ok().build();
    }

}
