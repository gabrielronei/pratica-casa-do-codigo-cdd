package br.com.gabriels.praticacasadocodigocdd.cupom;

import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.campoUnico.CampoUnico;
import br.com.gabriels.praticacasadocodigocdd.compartilhado.anotacao.existeId.ExisteId;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.*;

class NovoCupomRequest {

    @Pattern(regexp = "^[a-z\\-]+$")
    @NotBlank
    @CampoUnico(classeDominio = Cupom.class, nomeCampo = "codigo", message = "Já existe um cupom com este código!")
    private String codigo;

    @Positive
    @NotNull
    @Max(100)
    private BigDecimal percentual;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;


    @JsonCreator(mode = PROPERTIES)
    public NovoCupomRequest(String codigo, BigDecimal percentual, LocalDate validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }


    public Cupom toModel() {
        return new Cupom(this.codigo, this.percentual, this.validade);
    }
}
