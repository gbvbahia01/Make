/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityTest {
    
    @NotNull
    public Integer inteiro;

    public Integer getInteiro() {
        return inteiro;
    }

    public void setInteiro(Integer inteiro) {
        this.inteiro = inteiro;
    }
}
