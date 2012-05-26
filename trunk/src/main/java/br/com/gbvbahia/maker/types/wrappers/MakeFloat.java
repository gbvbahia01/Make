/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import java.util.Random;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeFloat {

    /**
     * Gerador de números aleatórios.
     */
    public static Random r = new Random();

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Float getIntervalo(final double min, final double max) {
        if (min > max) {
            throw new IllegalArgumentException(I18N.getMsg("nimMaiormax",
                    new Object[]{min, max}));
        }
        double ale = r.nextDouble();
        float numero = (float) (min + ((ale * (max - min))));
        return numero;
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Double limitado ao max.
     */
    public static Float getMax(final double max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }

        return getIntervalo(0, max);
    }

    /**
     * Não pode ser instânciada.
     */
    private MakeFloat() {
    }
}
