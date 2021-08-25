package br.com.gabriels.praticacasadocodigocdd.compra;

import br.com.gabriels.praticacasadocodigocdd.pais.Pais;
import br.com.gabriels.praticacasadocodigocdd.pais.estado.Estado;
import org.springframework.validation.*;

import javax.persistence.EntityManager;

public class PaisTemEstadoPertencenteValidator implements Validator {

    private final EntityManager entityManager;

    public PaisTemEstadoPertencenteValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (errors.hasErrors()) return;

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) object;

        Pais pais = entityManager.find(Pais.class, novaCompraRequest.getPaisId());

        if (pais.possuiEstados()) {

            if (!novaCompraRequest.temEstadoId()) {
                errors.rejectValue("estadoId", null, "É necessario o id de um estado para este pais!");
            } else if (novaCompraRequest.temEstadoId()) {
                Estado estado = entityManager.find(Estado.class, novaCompraRequest.getEstadoId());

                if (!estado.pertence(pais)) {
                    errors.rejectValue("estadoId", null, "Opa, este estado não pertence a este pais!");
                }
            }
        }
    }
}