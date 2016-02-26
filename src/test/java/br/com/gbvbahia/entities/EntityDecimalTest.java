package br.com.gbvbahia.entities;

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
    return this.maxBigDecimal;
  }

  public void setMaxBigDecimal(BigDecimal maxBigDecima) {
    this.maxBigDecimal = maxBigDecima;
  }

  public Integer getIntegerObjeto() {
    return this.integerObjeto;
  }

  public void setIntegerObjeto(Integer integerObjeto) {
    this.integerObjeto = integerObjeto;
  }

  public BigDecimal getBigDecimal() {
    return this.bigDecimal;
  }

  public void setBigDecimal(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

  public BigDecimal getMinBigDecimal() {
    return this.minBigDecimal;
  }

  public void setMinBigDecimal(BigDecimal minBigDecimal) {
    this.minBigDecimal = minBigDecimal;
  }

  public Short getShortObjeto() {
    return this.shortObjeto;
  }

  public void setShortObjeto(Short shortObjeto) {
    this.shortObjeto = shortObjeto;
  }

  @Override
  public String toString() {
    return "EntityDecimalTest{" + "integerObjeto=" + this.integerObjeto + ", bigDecimal="
        + this.bigDecimal + ", shortObjeto=" + this.shortObjeto + ", minBigDecimal="
        + this.minBigDecimal + ", maxBigDecima=" + this.maxBigDecimal + '}';
  }
}
