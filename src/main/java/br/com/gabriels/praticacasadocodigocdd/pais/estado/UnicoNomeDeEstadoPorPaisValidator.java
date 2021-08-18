package br.com.gabriels.praticacasadocodigocdd.pais.estado;

import org.springframework.validation.*;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.Assert.state;

class UnicoNomeDeEstadoPorPaisValidator implements Validator {

    private EntityManager entityManager;

    public UnicoNomeDeEstadoPorPaisValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoEstadoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        NovoEstadoRequest novoEstado = (NovoEstadoRequest) object;

        List resultList = entityManager.createQuery("SELECT 1 FROM Estado e WHERE e.nome = :nome AND e.pais.id = :id")
                .setParameter("nome", novoEstado.getNome())
                .setParameter("id", novoEstado.getPaisId())
                .getResultList();

        state(resultList.size() <= 1, "Existe mais de um estado com o mesmo nome pertencente ao pais!");

        if (!resultList.isEmpty()) {
            errors.rejectValue("nome", null, "Já existe um estado com esse nome dentro desse pais!");
        }
    }
}
