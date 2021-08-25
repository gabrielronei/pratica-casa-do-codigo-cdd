package br.com.gabriels.praticacasadocodigocdd.cupom;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;

@RestController
class CupomController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/coupons")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoCupomRequest novoCupomRequest) {

        entityManager.persist(novoCupomRequest.toModel());
        return ResponseEntity.ok().build();
    }

}
