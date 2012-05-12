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
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeLong {

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
    public static long getIntervalo(final long min, final long max) {
        if (min > max) {
            throw new IllegalArgumentException(I18N.getMsg("nimMaiormax",
                    new Object[]{min, max}));
        }
        double ale = r.nextDouble();
        long numero = min + ((long) (ale * (max - min)));
        return numero;
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     * Este método embora receba long como parámetro trabalha com
     * Inteiro e o maior número a retornar será o Integer.MAX_VALUE.
     * @param max Minimo 1.
     * @return Long limitado ao max.
     */
    public static Long getMax(final long max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }
        
        return new Long(r.nextInt(new Long(max).intValue()));
    }
}
