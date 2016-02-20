package br.com.gbvbahia.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Guilherme
 */
public class EntityInTest {


  private Integer in_1_2_5;

  private BigDecimal in_10V30_10V50_10V80;

  private Long in_3000_5000_6000;

  private Float in_45V80_M45V80_100V10;

  private BigInteger in_500_5000_50000_500000_5000000;

  private String in_200_300_400;

  private String in_A_B_C;

  private String in_Comma_At_Percent;

  private byte only_30;

  public BigDecimal getIn_10V30_10V50_10V80() {
    return this.in_10V30_10V50_10V80;
  }

  public Integer getIn_1_2_5() {
    return this.in_1_2_5;
  }

  public String getIn_200_300_400() {
    return this.in_200_300_400;
  }

  public Long getIn_3000_5000_6000() {
    return this.in_3000_5000_6000;
  }

  public Float getIn_45V80_M45V80_100V10() {
    return this.in_45V80_M45V80_100V10;
  }

  public BigInteger getIn_500_5000_50000_500000_5000000() {
    return this.in_500_5000_50000_500000_5000000;
  }

  public String getIn_A_B_C() {
    return this.in_A_B_C;
  }

  public String getIn_Comma_At_Percent() {
    return this.in_Comma_At_Percent;
  }

  public byte getOnly_30() {
    return this.only_30;
  }

  @Override
  public String toString() {
    return "EntityInTest{" + " in_1_2_5=" + this.in_1_2_5 + ", in_10V30_10V50_10V80="
        + this.in_10V30_10V50_10V80 + ", in_3000_5000_6000=" + this.in_3000_5000_6000
        + ", in_45V80_M45V80_100V10=" + this.in_45V80_M45V80_100V10
        + ", in_500_5000_50000_500000_5000000=" + this.in_500_5000_50000_500000_5000000
        + ", in_200_300_400=" + this.in_200_300_400 + ", in_A_B_C=" + this.in_A_B_C
        + ", in_Comma_At_Percent=" + this.in_Comma_At_Percent + ", only_30=" + this.only_30 + '}';
  }
}
