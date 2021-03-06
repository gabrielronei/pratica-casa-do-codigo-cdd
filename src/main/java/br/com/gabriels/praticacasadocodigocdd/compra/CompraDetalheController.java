package br.com.gabriels.praticacasadocodigocdd.compra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
public class CompraDetalheController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/compra/{id}")
    public ResponseEntity listar(@PathVariable("id") Long id) {

        Optional<Compra> possivelCompra = entityManager.createQuery("SELECT c FROM Compra c WHERE c.id = :id", Compra.class)
                .setParameter("id", id).getResultStream().findFirst();

        return possivelCompra.map(compra -> ResponseEntity.ok(new CompraDetalhe(compra))).orElseGet(notFound()::build);
    }
}
