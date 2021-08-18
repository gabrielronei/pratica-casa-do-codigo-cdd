package br.com.gabriels.praticacasadocodigocdd.pais.estado;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;

@RestController
class EstadoController {

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder("novoEstadoRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UnicoNomeDeEstadoPorPaisValidator(entityManager));
    }

    @PostMapping("/estados")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoEstadoRequest novoEstadoRequest) {

        entityManager.persist(novoEstadoRequest.toModel(entityManager));
        return ResponseEntity.ok().build();
    }

}
