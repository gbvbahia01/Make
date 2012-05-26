/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Guilherme
 */
public class EntitySizeTest {

    @NotNull
    private String limiteDefault;
    @NotNull
    @Size(max = 15)
    private String minDefaultMax15;
    @NotNull
    @Size(min = 15)
    private String maxDefaultMin15;
    @NotNull
    @Size(min = 15, max = 30)
    private String max30Min15;
    @NotNull
    @Size(min = 1, max = 1)
    private String max1Min1;

    public String getLimiteDefault() {
        return limiteDefault;
    }

    public void setLimiteDefault(String limiteDefault) {
        this.limiteDefault = limiteDefault;
    }

    public String getMaxDefaultMin15() {
        return maxDefaultMin15;
    }

    public void setMaxDefaultMin15(String maxDefaultMin15) {
        this.maxDefaultMin15 = maxDefaultMin15;
    }

    public String getMinDefaultMax15() {
        return minDefaultMax15;
    }

    public void setMinDefaultMax15(String minDefaultMax15) {
        this.minDefaultMax15 = minDefaultMax15;
    }

    public String getMax30Min15() {
        return max30Min15;
    }

    public void setMax30Min15(String max30Min15) {
        this.max30Min15 = max30Min15;
    }

    @Override
    public String toString() {
        return "EntitySizeTest{"
                + ", max1Min1=" + max1Min1
                + ", minDefaultMax15=" + minDefaultMax15
                + ", max30Min15=" + max30Min15
                + ", maxDefaultMin15=" + maxDefaultMin15
                + "limiteDefault=" + limiteDefault
                + '}';
    }
}
