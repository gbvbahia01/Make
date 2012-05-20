/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.wrappers;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public class MakeInteger {

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Integer getIntervalo(final int min, final int max) {
        return MakeLong.getIntervalo(min, max).intValue();
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Integer limitado ao max.
     */
    public static Integer getMax(final int max) {
        return MakeLong.getIntervalo(1, max).intValue();
    }
}
