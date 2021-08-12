package br.com.gabriels.praticacasadocodigocdd.compartilhado.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class ErrosValidacaoHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroValidacaoOutputDTO handle(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        List<ObjectError> errosGerais = result.getGlobalErrors();
        List<FieldError> errosDeCampo = result.getFieldErrors();

        return new ErroValidacaoOutputDTO(errosGerais, errosDeCampo);
    }
}
