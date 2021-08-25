package br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.existeId;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.*;
import java.util.Objects;

@Component
public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    private Class classeDominio;
    private String nomeCampo;
    private boolean obrigatorio;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.classeDominio = constraintAnnotation.classeDominio();
        this.nomeCampo = constraintAnnotation.nomeCampo();
        this.obrigatorio = constraintAnnotation.obrigatorio();
    }

    @Override
    public boolean isValid(Object id, ConstraintValidatorContext context) {
        if (!(this.obrigatorio) && Objects.isNull(id)) return true;

        Boolean exists = entityManager.createQuery("SELECT count(entidade) > 0 FROM " + classeDominio.getName() + " entidade WHERE "
                        + this.nomeCampo + " = :id", Boolean.class)
                .setParameter("id", id).getSingleResult();

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Não existe um(a) " + classeDominio.getSimpleName() + " com este id!")
                .addConstraintViolation();
        return exists;
    }
}
