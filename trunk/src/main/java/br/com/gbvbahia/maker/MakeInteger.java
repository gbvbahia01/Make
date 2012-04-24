/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import java.util.Random;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public class MakeInteger {

    /**
     * Gerador de números aleatórios.
     */
    private static Random r = new Random();

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Integer getIntervalo(final int min, final int max) {
        if (min > max) {
            throw new IllegalArgumentException(I18N.getMsg("nimMaiormax",
                    new Object[]{min, max}));
        }
        double ale = r.nextDouble();
        int numero = min + ((int) (ale * (max -min)));
        return numero;
    }

    public static Integer getMax(final int max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }
        return r.nextInt(max);
    }
}
