/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

/**
 * Cria um valor booleano aleatório.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class MakeBoolean {

    /**
     * Devolve um valor booleano, true ou false.
     *
     * @return True ou False.
     */
    public static Boolean getBoolean() {
        return MakeInteger.getMax(2) == 2;
    }
}
