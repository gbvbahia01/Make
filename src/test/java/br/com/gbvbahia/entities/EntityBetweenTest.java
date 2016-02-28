package br.com.gbvbahia.entities;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @since v.1
 * @author Guilherme
 */
public class EntityBetweenTest {
  @Min(5)
  @Max(10)
  private int between5_10;
  @Min(10)
  @Max(20)
  private Integer between10_20;
  @DecimalMin("-5.4")
  @DecimalMax("5.56")
  private BigDecimal between_M5V40_5V56;
  @DecimalMin("-50.13")
  @DecimalMax("-20.15")
  private double between_M50V13_M20V15;
  @Min(-50)
  @Max(50)
  private Long between_M50_50;
  @Min(5)
  @Max(23)
  private Short between5_23;
  @Min(-5)
  @Max(5)
  private byte between_M5_5;

  public Integer getBetween10_20() {
    return this.between10_20;
  }

  public int getBetween5_10() {
    return this.between5_10;
  }

  public Short getBetween5_23() {
    return this.between5_23;
  }

  public double getBetween_M50V13_M20V15() {
    return this.between_M50V13_M20V15;
  }

  public Long getBetween_M50_50() {
    return this.between_M50_50;
  }

  public BigDecimal getBetween_M5V40_5V56() {
    return this.between_M5V40_5V56;
  }

  public byte getBetween_M5_5() {
    return this.between_M5_5;
  }

  @Override
  public String toString() {
    return "EntityBetweenTest{" + "entre5_10=" + this.between5_10 + ", Between10_20="
        + this.between10_20 + ", entre_M5V40_5V56=" + this.between_M5V40_5V56
        + ", Between_M50V13_M20V15=" + this.between_M50V13_M20V15 + ", Between_M50_50="
        + this.between_M50_50 + ", Between5_23=" + this.between5_23 + ", Between_M5_5="
        + this.between_M5_5 + '}';
  }


}
