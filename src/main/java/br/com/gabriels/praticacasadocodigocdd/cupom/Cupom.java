package br.com.gabriels.praticacasadocodigocdd.cupom;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

import static java.time.LocalDate.now;
import static org.springframework.util.Assert.*;

@Entity
public class Cupom {

    private static final java.util.regex.Pattern CODIGO_REGEX = Pattern.compile("^[a-z\\-]+$");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @javax.validation.constraints.Pattern(regexp = "^[a-z\\-]+$")
    @NotBlank
    @Column(unique = true)
    private String codigo;

    @Positive
    @NotNull
    @Max(100)
    private BigDecimal percentual;

    @Future
    @NotNull
    private LocalDate validade;

    @Deprecated
    public Cupom() {
    }

    public Cupom(String codigo, BigDecimal percentual, LocalDate validade) {
        hasText(codigo, "O código não pode estar em branco!");
        notNull(percentual, "O percentual não pode ser nulo!");
        notNull(validade, "A data de validade não pode ser nulo!");
        isTrue(now().isBefore(validade), "A data de validade precisa ser no futuro!");
        isTrue(CODIGO_REGEX.matcher(codigo).matches(), "O código precisa estar em um formato com letras minusculas e separado por '-'!");

        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }
}
