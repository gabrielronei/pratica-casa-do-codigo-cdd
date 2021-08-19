package br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.cpfOuCnpj;

import org.hibernate.validator.constraints.*;
import org.hibernate.validator.constraints.br.*;

import javax.validation.*;
import java.lang.annotation.*;

@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFouCNPJ {

    String message() default "Precisa ser um CPF ou um CNPJ valido!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
