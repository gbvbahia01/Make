/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import java.lang.reflect.Field;

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

    /**
     * Retorna True para tipos Boolean ou boolean.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Boolean ou boolean, False para outros
     * tipos.
     */
    public static boolean isBoolean(Field f) {
        if (f.getType().equals(Boolean.class)
                || f.getType().equals(boolean.class)) {
            return true;
        }
        return false;
    }
}
