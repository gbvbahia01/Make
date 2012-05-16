/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityNotNullTest {

    private Integer inteiro;
    @NotNull
    private Integer inteiroObjeto;
    @NotNull
    private Long longObjeto;
    @NotNull
    private Byte byteObjeto;
    @NotNull
    private Short shortObjeto;
    @NotNull
    private BigInteger bigInteger;
    @NotNull
    private BigDecimal bigDecimal;
    @NotNull
    private String string;
    @NotNull
    private Double doubleObjeto;
    @NotNull
    private Float floatObjeto;
    
    public Integer getInteiro() {
        return inteiro;
    }

    public void setInteiro(Integer inteiro) {
        this.inteiro = inteiro;
    }

    public Long getLongObjeto() {
        return longObjeto;
    }

    public void setLongObjeto(Long longObjeto) {
        this.longObjeto = longObjeto;
    }

    public Byte getByteObjeto() {
        return byteObjeto;
    }

    public void setByteObjeto(Byte byteObjeto) {
        this.byteObjeto = byteObjeto;
    }

    public Integer getInteiroObjeto() {
        return inteiroObjeto;
    }

    public void setInteiroObjeto(Integer inteiroObjeto) {
        this.inteiroObjeto = inteiroObjeto;
    }

    public Short getShortObjeto() {
        return shortObjeto;
    }

    public void setShortObjeto(Short shortObjeto) {
        this.shortObjeto = shortObjeto;
    }

    public BigInteger getBigInteger() {
        return bigInteger;
    }

    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Double getDoubleObjeto() {
        return doubleObjeto;
    }

    public void setDoubleObjeto(Double doubleObjeto) {
        this.doubleObjeto = doubleObjeto;
    }

    public Float getFloatObjeto() {
        return floatObjeto;
    }

    public void setFloatObjeto(Float floatObjeto) {
        this.floatObjeto = floatObjeto;
    }

    @Override
    public String toString() {
        return "EntityNotNullTest{" + "inteiro=" + inteiro 
                + ", inteiroObjeto=" + inteiroObjeto 
                + ", longObjeto=" + longObjeto 
                + ", byteObjeto=" + byteObjeto 
                + ", shortObjeto=" + shortObjeto 
                + ", bigInteger=" + bigInteger 
                + ", bigDecimal=" + bigDecimal 
                + ", doubleObjeto=" + doubleObjeto 
                + ", floatObjeto=" + floatObjeto 
                + ", string=" + string + '}';
    }


}
