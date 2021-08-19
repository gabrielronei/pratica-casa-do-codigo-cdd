package br.com.gabriels.praticacasadocodigocdd.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
class CompraController {

    @Autowired
    private EntityManager entityManager;

    @InitBinder("novaCompraRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new PaisTemEstadoPertencenteValidator(entityManager));
    }

    @PostMapping("/comprar")
    @Transactional
    public ResponseEntity comprar(@RequestBody @Valid NovaCompraRequest novaCompraRequest) {

        return ResponseEntity.ok().build();
    }

}
