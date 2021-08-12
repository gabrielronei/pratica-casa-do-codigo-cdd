package br.com.gabriels.praticacasadocodigocdd.compartilhado.validacao;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.*;

import java.util.*;
import java.util.stream.Collectors;

public class ErroValidacaoOutputDTO {

    private final List<String> mensagensDeErro;
    private final Map<String, String> errosDeCampo;

    public ErroValidacaoOutputDTO(List<ObjectError> mensagensDeErro, List<FieldError> errosDeCampo) {
        this.mensagensDeErro = mensagensDeErro.stream().map(ObjectError::toString).toList();
        this.errosDeCampo = errosDeCampo.stream().collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
    }

    public List<String> getMensagensDeErro() {
        return mensagensDeErro;
    }

    public Map<String, String> getErrosDeCampo() {
        return errosDeCampo;
    }
}
