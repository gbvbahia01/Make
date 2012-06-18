/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

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
    private int entre5_10;
    @Min(-100)
    @Max(100)
    private Integer entre10_20;
    @Min(-100)
    @Max(100)
    private BigDecimal entre_M5V40_5V56;
    @Min(-100)
    @Max(100)
    private double entre_M50V13_M20V15;
    @Min(-100)
    @Max(100)
    private Long entre_M50_50;
    @Min(-100)
    @Max(100)
    private Short entre5_23;
    @Min(-100)
    @Max(100)
    private byte entre_M5_5;

    public Integer getEntre10_20() {
        return entre10_20;
    }

    public int getEntre5_10() {
        return entre5_10;
    }

    public Short getEntre5_23() {
        return entre5_23;
    }

    public double getEntre_M50V13_M20V15() {
        return entre_M50V13_M20V15;
    }

    public Long getEntre_M50_50() {
        return entre_M50_50;
    }

    public BigDecimal getEntre_M5V40_5V56() {
        return entre_M5V40_5V56;
    }

    public byte getEntre_M5_5() {
        return entre_M5_5;
    }

    @Override
    public String toString() {
        return "EntityBetweenTest{" + "entre5_10=" + entre5_10 + ", entre10_20=" + entre10_20 + ", entre_M5V40_5V56=" + entre_M5V40_5V56 + ", entre_M50V13_M20V15=" + entre_M50V13_M20V15 + ", entre_M50_50=" + entre_M50_50 + ", entre5_23=" + entre5_23 + ", entre_M5_5=" + entre_M5_5 + '}';
    }
    
    
}
