/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityMinTest {

    @NotNull
    @Min(4)
    public Integer inteiroObjeto;
    @NotNull
    @Min(-2)
    public Integer inteiroNegativoObjeto;
    @NotNull
    @Min(100)
    public int primitivoInt;
    @NotNull
    @Min(-1000000000)
    public Long longObjeto;
    @NotNull
    @Min(2000000000)
    public long primitivoLong;
    @NotNull
    @Min(126)
    public Byte byteObjeto;
    @NotNull
    @Min(22)
    public byte primitivoByte;
    @NotNull
    @Min(32000)
    public Short shortObjeto;
    @NotNull
    @Min(32050)
    public short primitivoShort;
    @NotNull
    @Min(320500000)
    public BigInteger bigInteger;
    @NotNull
    @Min(1)
    public BigDecimal bigDecimal;

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

    @Override
    public String toString() {
        return "EntityMinTest{" + "inteiroObjeto=" + inteiroObjeto + ", inteiroNegativoObjeto=" + inteiroNegativoObjeto + ", primitivoInt=" + primitivoInt + ", longObjeto=" + longObjeto + ", primitivoLong=" + primitivoLong + ", byteObjeto=" + byteObjeto + ", primitivoByte=" + primitivoByte + ", shortObjeto=" + shortObjeto + ", primitivoShort=" + primitivoShort + ", bigInteger=" + bigInteger + ", bigDecimal=" + bigDecimal + '}';
    }


}
