package br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.campoUnico;

import javax.validation.*;
import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CampoUnicoValidator.class)
public @interface CampoUnico {

    Class<?> classeDominio();

    String nomeCampo();

    String message() default "{gabriel.campo.unico.mensagem}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
