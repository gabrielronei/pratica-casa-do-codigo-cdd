package br.com.gabriels.praticacasadocodigocdd.compra;

import org.springframework.validation.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class PrecoTotalCompraValidator implements Validator {

    private EntityManager entityManager;

    public PrecoTotalCompraValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }


    //TODO melhorar esse algoritmo depois
    @Override
    public void validate(Object object, Errors errors) {

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) object;

        Map<Long, BigDecimal> livros = entityManager.createQuery("SELECT l.id as livroId, l.preco as preco FROM Livro l where l.id in (:ids)", Tuple.class)
                .setParameter("ids", novaCompraRequest.getItensIds())
                .getResultStream()
                .collect(
                        Collectors.toMap(
                                tupla -> (Long) tupla.get("livroId"),
                                tupla -> (BigDecimal) tupla.get("preco")
                        )
                );

        if (livros.isEmpty()) {
            errors.rejectValue("itens", null, "Não existe nenhum livro cadastrado com esses ids em nosso banco!");
        }

        BigDecimal totalCalculado = novaCompraRequest.calculaValorTotal(livros);

        if (novaCompraRequest.totalDiferente(totalCalculado)) {
            errors.rejectValue("total", null, "Preço total calculado no servidor não bate com o especificado!");
        }
    }
}
