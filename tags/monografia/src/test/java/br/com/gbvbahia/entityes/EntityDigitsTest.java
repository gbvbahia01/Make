/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityDigitsTest {

    @NotNull
    @Digits(integer = 3, fraction = 5)
    private BigDecimal bigDecimal;
    @NotNull
    @Digits(integer = 5, fraction = 3)
    private Integer integerObjeto;
    @NotNull
    @Max(10)
    @Min(0)
    @Digits(integer = 2, fraction = 1)
    private Double positivo;
    @NotNull
    @Max(100)
    @Min(-150)
    @Digits(integer = 3, fraction = 2)
    private Float misturado;
    @NotNull
    @Max(-100)
    @Min(-150)
    @Digits(integer = 3, fraction = 2)
    private Float negativo;
    @NotNull
    @Min(0)
    @Digits(fraction = 2, integer = 17)
    private Double saldo = 0.00;

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Integer getIntegerObjeto() {
        return integerObjeto;
    }

    public void setIntegerObjeto(Integer integerObjeto) {
        this.integerObjeto = integerObjeto;
    }

    public Double getMisturado() {
        return positivo;
    }

    public void setMisturado(Double misturado) {
        this.positivo = misturado;
    }

    @Override
    public String toString() {
        return "EntityDigits{"
                + " integerObjeto=" + integerObjeto
                + " misturado=" + positivo
                + " misturado=" + misturado
                + " saldo=" + saldo
                + " negativo=" + negativo
                + " bigDecimal=" + bigDecimal + '}';
    }
}
