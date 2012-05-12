/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityTest {

    public Integer inteiro;
    @NotNull
    @Min(3)
    @Max(4)
    public Integer inteiroObjeto;
    @NotNull
    @Min(0)
    @Max(100)
    public int primitivoInt;
    @NotNull
    public Long longObjeto;
    @NotNull
    @Min(1000000000)
    @Max(2000000000)
    public long primitivoLong;
    @NotNull
    @Min(50)
    @Max(126)
    public Byte byteObjeto;
    @NotNull
    @Min(11)
    @Max(22)
    public byte primitivoByte;
    @NotNull
    @Min(30000)
    @Max(32000)
    public Short shortObjeto;
    @NotNull
    @Min(32000)
    @Max(32050)
    public short primitivoShort;

    public Integer getInteiro() {
        return inteiro;
    }

    public void setInteiro(Integer inteiro) {
        this.inteiro = inteiro;
    }

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

    @Override
    public String toString() {
        return "EntityTest{" + "inteiro=" + inteiro + ", inteiroObjeto=" + inteiroObjeto + ", primitivoInt=" + primitivoInt + ", longObjeto=" + longObjeto + ", primitivoLong=" + primitivoLong + ", byteObjeto=" + byteObjeto + ", primitivoByte=" + primitivoByte + ", shortObjeto=" + shortObjeto + ", primitivoShort=" + primitivoShort + '}';
    }
}
