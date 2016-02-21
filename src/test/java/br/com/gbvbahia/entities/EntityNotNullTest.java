package br.com.gbvbahia.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
@Entity
public class EntityNotNullTest {

  @Null
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
  @NotNull
  private Character characterObjeto;

  public Integer getInteiro() {
    return this.inteiro;
  }

  public void setInteiro(Integer inteiro) {
    this.inteiro = inteiro;
  }

  public Long getLongObjeto() {
    return this.longObjeto;
  }

  public void setLongObjeto(Long longObjeto) {
    this.longObjeto = longObjeto;
  }

  public Byte getByteObjeto() {
    return this.byteObjeto;
  }

  public void setByteObjeto(Byte byteObjeto) {
    this.byteObjeto = byteObjeto;
  }

  public Integer getInteiroObjeto() {
    return this.inteiroObjeto;
  }

  public void setInteiroObjeto(Integer inteiroObjeto) {
    this.inteiroObjeto = inteiroObjeto;
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

  public Character getCharacterObjeto() {
    return this.characterObjeto;
  }

  public void setCharacterObjeto(Character characterObjeto) {
    this.characterObjeto = characterObjeto;
  }

  @Override
  public String toString() {
    return "EntityNotNullTest{" + "inteiro=" + this.inteiro + ", inteiroObjeto="
        + this.inteiroObjeto + ", longObjeto=" + this.longObjeto + ", byteObjeto=" + this.byteObjeto
        + ", shortObjeto=" + this.shortObjeto + ", bigInteger=" + this.bigInteger + ", bigDecimal="
        + this.bigDecimal + ", doubleObjeto=" + this.doubleObjeto + ", floatObjeto="
        + this.floatObjeto + ", characterObjeto=" + this.characterObjeto + ", string=" + this.string
        + '}';
  }


}
