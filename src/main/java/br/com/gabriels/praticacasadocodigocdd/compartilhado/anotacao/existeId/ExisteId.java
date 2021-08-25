package br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.existeId;


import javax.validation.*;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExisteIdValidator.class)
public @interface ExisteId {

    Class<?> classeDominio();

    boolean obrigatorio() default true;

    String nomeCampo() default "id";

    String message() default "{gabriel.existe.id.mensagem}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
