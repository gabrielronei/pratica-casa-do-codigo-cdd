package br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.existeId;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.*;
import java.util.Objects;

@Component
public class ExisteIdValidator implements ConstraintValidator<ExisteId, Long> {

    private Class classeDominio;
    private boolean obrigatorio;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.classeDominio = constraintAnnotation.classeDominio();
        this.obrigatorio = constraintAnnotation.obrigatorio();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if (!(this.obrigatorio) && Objects.isNull(id)) return true;

        Boolean exists = entityManager.createQuery("SELECT count(entidade) > 0 FROM " + classeDominio.getName() + " entidade WHERE id = :id", Boolean.class)
                .setParameter("id", id).getSingleResult();

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Não existe um(a) " + classeDominio.getSimpleName() + " com este id!")
                .addConstraintViolation();
        return exists;
    }
}
