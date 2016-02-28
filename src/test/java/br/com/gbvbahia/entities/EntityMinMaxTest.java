package br.com.gbvbahia.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @since v.1
 * @author Guilherme
 */
public class EntityMinMaxTest {

  @NotNull
  @Min(0)
  @Max(1)
  private BigDecimal bigDecimal;
  @NotNull
  @Min(300000000)
  @Max(320500000)
  private BigInteger bigInteger;
  @NotNull
  @Min(50)
  @Max(126)
  private Byte byteObjeto;
  @Null
  private Integer inteiro;
  @NotNull
  @Min(-3)
  @Max(-2)
  private Integer inteiroNegativoObjeto;
  @NotNull
  @Min(3)
  @Max(4)
  private Integer inteiroObjeto;
  @NotNull
  @Min(-2000000000)
  @Max(-1000000000)
  private Long longObjeto;
  @NotNull
  @Min(11)
  @Max(22)
  private byte primitivoByte;
  @NotNull
  @Min(0)
  @Max(100)
  private int primitivoInt;
  @NotNull
  @Min(1000000000)
  @Max(2000000000)
  private long primitivoLong;
  @NotNull
  @Min(32000)
  @Max(32050)
  private short primitivoShort;
  @NotNull
  @Min(30000)
  @Max(32000)
  private Short shortObjeto;
  @Max(10)
  @Min(5)
  @NotNull
  private String string;
  @Max(10)
  @Min(-50)
  @NotNull
  private Double doubleObjeto;
  @Min(512)
  @Max(515)
  @NotNull
  private double primitivoDouble;
  @NotNull
  @Min(5000)
  @Max(5150)
  private Float floatObjeto;
  @NotNull
  @Min(1)
  @Max(3)
  private float primitivoFloat;

  public Integer getInteiro() {
    return this.inteiro;
  }

  public void setInteiro(Integer inteiro) {
    this.inteiro = inteiro;
  }

  public int getPrimitivoInt() {
    return this.primitivoInt;
  }

  public void setPrimitivoInt(int primitivoInt) {
    this.primitivoInt = primitivoInt;
  }

  public Long getLongObjeto() {
    return this.longObjeto;
  }

  public void setLongObjeto(Long longObjeto) {
    this.longObjeto = longObjeto;
  }

  public long getPrimitivoLong() {
    return this.primitivoLong;
  }

  public void setPrimitivoLong(long primitivoLong) {
    this.primitivoLong = primitivoLong;
  }

  public Byte getByteObjeto() {
    return this.byteObjeto;
  }

  public void setByteObjeto(Byte byteObjeto) {
    this.byteObjeto = byteObjeto;
  }

  public byte getPrimitivoByte() {
    return this.primitivoByte;
  }

  public void setPrimitivoByte(byte primitivoByte) {
    this.primitivoByte = primitivoByte;
  }

  public Integer getInteiroObjeto() {
    return this.inteiroObjeto;
  }

  public void setInteiroObjeto(Integer inteiroObjeto) {
    this.inteiroObjeto = inteiroObjeto;
  }

  public short getPrimitivoShort() {
    return this.primitivoShort;
  }

  public void setPrimitivoShort(short primitivoShort) {
    this.primitivoShort = primitivoShort;
  }

  public Short getShortObjeto() {
    return this.shortObjeto;
  }

  public void setShortObjeto(Short shortObjeto) {
    this.shortObjeto = shortObjeto;
  }

  public BigInteger getBigInteger() {
    return this.bigInteger;
  }

  public void setBigInteger(BigInteger bigInteger) {
    this.bigInteger = bigInteger;
  }

  public BigDecimal getBigDecimal() {
    return this.bigDecimal;
  }

  public void setBigDecimal(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

  public Integer getInteiroNegativoObjeto() {
    return this.inteiroNegativoObjeto;
  }

  public void setInteiroNegativoObjeto(Integer inteiroNegativoObjeto) {
    this.inteiroNegativoObjeto = inteiroNegativoObjeto;
  }

  public String getString() {
    return this.string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public Double getDoubleObjeto() {
    return this.doubleObjeto;
  }

  public void setDoubleObjeto(Double doubleObjeto) {
    this.doubleObjeto = doubleObjeto;
  }

  public Float getFloatObjeto() {
    return this.floatObjeto;
  }

  public void setFloatObjeto(Float floatObjeto) {
    this.floatObjeto = floatObjeto;
  }

  public double getPrimitivoDouble() {
    return this.primitivoDouble;
  }

  public void setPrimitivoDouble(double primitivoDouble) {
    this.primitivoDouble = primitivoDouble;
  }

  public float getPrimitivoFloat() {
    return this.primitivoFloat;
  }

  public void setPrimitivoFloat(float primitivoFloat) {
    this.primitivoFloat = primitivoFloat;
  }

  @Override
  public String toString() {
    return "EntityMinMaxTest{" + "bigDecimal=" + this.bigDecimal + ", bigInteger=" + this.bigInteger
        + ", byteObjeto=" + this.byteObjeto + ", inteiro=" + this.inteiro
        + ", inteiroNegativoObjeto=" + this.inteiroNegativoObjeto + ", inteiroObjeto="
        + this.inteiroObjeto + ", longObjeto=" + this.longObjeto + ", primitivoByte="
        + this.primitivoByte + ", primitivoInt=" + this.primitivoInt + ", primitivoLong="
        + this.primitivoLong + ", primitivoShort=" + this.primitivoShort + ", shortObjeto="
        + this.shortObjeto + ", string=" + this.string + ", doubleObjeto=" + this.doubleObjeto
        + ", primitivoDouble=" + this.primitivoDouble + ", floatObjeto=" + this.floatObjeto
        + ", primitivoFloat=" + this.primitivoFloat + '}';
  }


}
