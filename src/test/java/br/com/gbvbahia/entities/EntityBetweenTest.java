package br.com.gbvbahia.entities;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Guilherme
 */
public class EntityBetweenTest {
  @Min(-100)
  @Max(100)
  private int between5_10;
  @Min(-100)
  @Max(100)
  private Integer between10_20;
  @Min(-100)
  @Max(100)
  private BigDecimal between_M5V40_5V56;
  @Min(-100)
  @Max(100)
  private double between_M50V13_M20V15;
  @Min(-100)
  @Max(100)
  private Long between_M50_50;
  @Min(-100)
  @Max(100)
  private Short between5_23;
  @Min(-100)
  @Max(100)
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
