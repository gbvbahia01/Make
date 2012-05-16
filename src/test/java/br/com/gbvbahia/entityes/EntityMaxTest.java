
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

//~--- JDK imports ------------------------------------------------------------
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityMaxTest {

    @NotNull
    @Max(1)
    private BigDecimal bigDecimal;
    @NotNull
    @Max(320500000)
    private BigInteger bigInteger;
    @NotNull
    @Max(126)
    private Byte byteObjeto;
    @NotNull
    @Max(-2)
    private Integer inteiroNegativoObjeto;
    @NotNull
    @Max(4)
    private Integer inteiroObjeto;
    @NotNull
    @Max(-1000000000)
    private Long longObjeto;
    @NotNull
    @Max(22)
    private byte primitivoByte;
    @NotNull
    @Max(100)
    private int primitivoInt;
    @NotNull
    @Max(2000000000)
    private long primitivoLong;
    @NotNull
    @Max(32050)
    private short primitivoShort;
    @NotNull
    @Max(32000)
    private Short shortObjeto;
    @Max(10)
    @NotNull
    private String string;
    @Max(-10)
    @NotNull
    private Double doubleObjeto;
    @Max(-15)
    @NotNull
    private double primitivoDouble;
    @NotNull
    @Max(5000)
    private Float floatObjeto;
    @NotNull
    @Max(1)
    private float primitivoFloat;

    public int getPrimitivoInt() {
        return primitivoInt;
    }

    public void setPrimitivoInt(int primitivoInt) {
        this.primitivoInt = primitivoInt;
    }

    public Long getLongObjeto() {
        return longObjeto;
    }

    public void setLongObjeto(Long longObjeto) {
        this.longObjeto = longObjeto;
    }

    public long getPrimitivoLong() {
        return primitivoLong;
    }

    public void setPrimitivoLong(long primitivoLong) {
        this.primitivoLong = primitivoLong;
    }

    public Byte getByteObjeto() {
        return byteObjeto;
    }

    public void setByteObjeto(Byte byteObjeto) {
        this.byteObjeto = byteObjeto;
    }

    public byte getPrimitivoByte() {
        return primitivoByte;
    }

    public void setPrimitivoByte(byte primitivoByte) {
        this.primitivoByte = primitivoByte;
    }

    public Integer getInteiroObjeto() {
        return inteiroObjeto;
    }

    public void setInteiroObjeto(Integer inteiroObjeto) {
        this.inteiroObjeto = inteiroObjeto;
    }

    public short getPrimitivoShort() {
        return primitivoShort;
    }

    public void setPrimitivoShort(short primitivoShort) {
        this.primitivoShort = primitivoShort;
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

    public Integer getInteiroNegativoObjeto() {
        return inteiroNegativoObjeto;
    }

    public void setInteiroNegativoObjeto(Integer inteiroNegativoObjeto) {
        this.inteiroNegativoObjeto = inteiroNegativoObjeto;
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

    public double getPrimitivoDouble() {
        return primitivoDouble;
    }

    public void setPrimitivoDouble(double primitivoDouble) {
        this.primitivoDouble = primitivoDouble;
    }

    public float getPrimitivoFloat() {
        return primitivoFloat;
    }

    public void setPrimitivoFloat(float primitivoFloat) {
        this.primitivoFloat = primitivoFloat;
    }

    @Override
    public String toString() {
        return "EntityMaxTest{" + "bigDecimal=" + bigDecimal
                + ", bigInteger=" + bigInteger
                + ", byteObjeto=" + byteObjeto
                + ", inteiroNegativoObjeto=" + inteiroNegativoObjeto
                + ", inteiroObjeto=" + inteiroObjeto
                + ", longObjeto=" + longObjeto
                + ", primitivoByte=" + primitivoByte
                + ", primitivoInt=" + primitivoInt
                + ", primitivoLong=" + primitivoLong
                + ", primitivoShort=" + primitivoShort
                + ", shortObjeto=" + shortObjeto
                + ", string=" + string
                + ", doubleObjeto=" + doubleObjeto
                + ", primitivoDouble=" + primitivoDouble
                + ", floatObjeto=" + floatObjeto
                + ", primitivoFloat=" + primitivoFloat
                + '}';
    }
}
