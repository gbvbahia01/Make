package br.com.gbvbahia.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityMinTest {

  @NotNull
  @Min(4)
  private Integer inteiroObjeto;
  @NotNull
  @Min(-2)
  private Integer inteiroNegativoObjeto;
  @NotNull
  @Min(100)
  private int primitivoInt;
  @NotNull
  @Min(-1000000000)
  private Long longObjeto;
  @NotNull
  @Min(2000000000)
  private long primitivoLong;
  @NotNull
  @Min(126)
  private Byte byteObjeto;
  @NotNull
  @Min(22)
  private byte primitivoByte;
  @NotNull
  @Min(32000)
  private Short shortObjeto;
  @NotNull
  @Min(32050)
  private short primitivoShort;
  @NotNull
  @Min(320500000)
  private BigInteger bigInteger;
  @NotNull
  @Min(1)
  private BigDecimal bigDecimal;
  @Min(10)
  @NotNull
  private String string;
  @Min(-500)
  @NotNull
  private Double doubleObjeto;
  @Min(512)
  @NotNull
  private double primitivoDouble;
  @NotNull
  @Min(5000)
  private Float floatObjeto;
  @NotNull
  @Min(1)
  private float primitivoFloat;
  @NotNull
  @Min(Long.MAX_VALUE - 1)
  private Long longObjetoMinMaxValue;

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

  public Long getLongObjetoMinMaxValue() {
    return this.longObjetoMinMaxValue;
  }

  public void setLongObjetoMinMaxValue(Long longObjetoMinMaxValue) {
    this.longObjetoMinMaxValue = longObjetoMinMaxValue;
  }

  @Override
  public String toString() {
    return "EntityMinTest{" + "inteiroObjeto=" + this.inteiroObjeto + ", inteiroNegativoObjeto="
        + this.inteiroNegativoObjeto + ", primitivoInt=" + this.primitivoInt + ", longObjeto="
        + this.longObjeto + ", primitivoLong=" + this.primitivoLong + ", byteObjeto="
        + this.byteObjeto + ", primitivoByte=" + this.primitivoByte + ", shortObjeto="
        + this.shortObjeto + ", primitivoShort=" + this.primitivoShort + ", bigInteger="
        + this.bigInteger + ", bigDecimal=" + this.bigDecimal + ", string=" + this.string
        + ", doubleObjeto=" + this.doubleObjeto + ", primitivoDouble=" + this.primitivoDouble
        + ", floatObjeto=" + this.floatObjeto + ", primitivoFloat=" + this.primitivoFloat
        + ", longObjetoMinMaxValue=" + this.longObjetoMinMaxValue + '}';
  }
}
