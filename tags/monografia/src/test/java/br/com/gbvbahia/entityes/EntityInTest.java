/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

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
    
    private String in_Virgula_Arroba_Percentual;
    
    private byte only_30;

    public BigDecimal getIn_10V30_10V50_10V80() {
        return in_10V30_10V50_10V80;
    }

    public Integer getIn_1_2_5() {
        return in_1_2_5;
    }

    public String getIn_200_300_400() {
        return in_200_300_400;
    }

    public Long getIn_3000_5000_6000() {
        return in_3000_5000_6000;
    }

    public Float getIn_45V80_M45V80_100V10() {
        return in_45V80_M45V80_100V10;
    }

    public BigInteger getIn_500_5000_50000_500000_5000000() {
        return in_500_5000_50000_500000_5000000;
    }

    public String getIn_A_B_C() {
        return in_A_B_C;
    }

    public String getIn_Virgula_Arroba_Percentual() {
        return in_Virgula_Arroba_Percentual;
    }

    public byte getOnly_30() {
        return only_30;
    }

    @Override
    public String toString() {
        return "EntityInTest{"
                + " in_1_2_5=" + in_1_2_5
                + ", in_10V30_10V50_10V80=" + in_10V30_10V50_10V80
                + ", in_3000_5000_6000=" + in_3000_5000_6000
                + ", in_45V80_M45V80_100V10=" + in_45V80_M45V80_100V10
                + ", in_500_5000_50000_500000_5000000=" + in_500_5000_50000_500000_5000000
                + ", in_200_300_400=" + in_200_300_400
                + ", in_A_B_C=" + in_A_B_C
                + ", in_Virgula_Arroba_Percentual=" + in_Virgula_Arroba_Percentual
                + ", only_30=" + only_30
                + '}';
    }
}
