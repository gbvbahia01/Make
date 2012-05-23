/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityDecimalTest {
    
    @DecimalMax(value="3.5")
    @NotNull
    private Integer integerObjeto;
    
    @DecimalMax(value="-3.5")
    @NotNull
    private BigDecimal bigDecimal;
    
    public Integer getIntegerObjeto() {
        return integerObjeto;
    }

    public void setIntegerObjeto(Integer integerObjeto) {
        this.integerObjeto = integerObjeto;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    @Override
    public String toString() {
        return "EntityDecimalTest{" + "integerObjeto=" + integerObjeto + ", bigDecimal=" + bigDecimal + '}';
    }


}
