package br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao;

import javax.persistence.*;
import javax.validation.*;
import java.util.*;

public class CampoUnicoValidator implements ConstraintValidator<CampoUnico, Object> {

    private Class<?> classeDominio;
    private String nomeCampo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(CampoUnico campoUnico) {
        this.classeDominio = campoUnico.classeDominio();
        this.nomeCampo = campoUnico.nomeCampo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 FROM " + classeDominio.getName() + " WHERE " + nomeCampo + " =:valor");
        query.setParameter("valor", value);

        List<?> possiveisResultados = query.getResultList();

        return possiveisResultados.isEmpty();
    }
}
