/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityDecimalTest {

    @DecimalMax(value = "3.5")
    @NotNull
    private Integer integerObjeto;
    @DecimalMax(value = "-3.5")
    @NotNull
    private BigDecimal bigDecimal;
    @NotNull
    @DecimalMax("-1.79769313486231570E+307")
    private BigDecimal maxBigDecimal;
    @NotNull
    @DecimalMin("1.79769313486231570E+307")
    private BigDecimal minBigDecimal;
    @NotNull
    @DecimalMin("32760")
    @DecimalMax("32765")
    private Short shortObjeto;

    public BigDecimal getMaxBigDecimal() {
        return maxBigDecimal;
    }

    public void setMaxBigDecimal(BigDecimal maxBigDecima) {
        this.maxBigDecimal = maxBigDecima;
    }

    public Integer getIntegerObjeto() {
        return integerObjeto;
    }

    public void setIntegerObjeto(Integer integerObjeto) {
        this.integerObjeto = integerObjeto;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public BigDecimal getMinBigDecimal() {
        return minBigDecimal;
    }

    public void setMinBigDecimal(BigDecimal minBigDecimal) {
        this.minBigDecimal = minBigDecimal;
    }

    public Short getShortObjeto() {
        return shortObjeto;
    }

    public void setShortObjeto(Short shortObjeto) {
        this.shortObjeto = shortObjeto;
    }

    @Override
    public String toString() {
        return "EntityDecimalTest{" + "integerObjeto=" + integerObjeto
                + ", bigDecimal=" + bigDecimal
                + ", shortObjeto=" + shortObjeto
                + ", minBigDecimal=" + minBigDecimal
                + ", maxBigDecima=" + maxBigDecimal + '}';
    }
}
