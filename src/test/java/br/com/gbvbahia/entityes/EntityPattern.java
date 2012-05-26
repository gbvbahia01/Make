/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Guilherme
 */
public class EntityPattern {

    @NotNull
    @Pattern(regexp = "[a-z]")
    private String az;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]")
    private String azAZ;
    @Pattern(regexp = "[a-zA-z0-9]+[@][a-z]+")
    private String letrarNumeros;

    public String getAz() {
        return az;
    }

    public void setAz(String az) {
        this.az = az;
    }

    @Override
    public String toString() {
        return "EntityPattern{"
                + " az=" + az
                + " azAZ=" + azAZ
                + " letrarNumeros=" + letrarNumeros
                + '}';
    }
}
