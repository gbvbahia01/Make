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
public class EntityPatternTest {

    @NotNull
    @Pattern(regexp = "[0-9]")
    private String num;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]")
    private String azAZ;
    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`"
    + "{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:"
    + "[a-z0-9-]*[a-z0-9])?")
    private String email;
    
    @Pattern(regexp="[2-4]")
    private String nulo;

    @Override
    public String toString() {
        return "EntityPattern{"
                + " num=" + num
                + " azAZ=" + azAZ
                + " email=" + email
                + " nulo=" + nulo
                + '}';
    }
}
