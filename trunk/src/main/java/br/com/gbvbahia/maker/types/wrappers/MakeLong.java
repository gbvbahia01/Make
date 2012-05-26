/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public static Long getIntervalo(final long min, final long max) {
        if (min > max) {
            throw new IllegalArgumentException(I18N.getMsg("nimMaiormax",
                    new Object[]{min, max}));
        }
        if (min == max) {
            return min;
        }
        double ale = r.nextDouble();
        long numero = 0L;
        if (min < 0 && max > 0) {
            long longValue;
            if (max + min == 0) {
                if (r.nextInt() % 2 == 0) {
                    longValue = new BigDecimal((ale * (max / 2 + min))).setScale(0, RoundingMode.HALF_EVEN).longValue();
                } else {
                    longValue = new BigDecimal((ale * (max + min / 2))).setScale(0, RoundingMode.HALF_EVEN).longValue();
                }
            } else {
                longValue = new BigDecimal((ale * (max + min))).setScale(0, RoundingMode.HALF_EVEN).longValue();
            }
            if (longValue > 0) {
                numero = min + longValue;
            } else {
                numero = max + longValue;
            }
        } else {
            numero = min + new BigDecimal((ale * (max - min))).setScale(0, RoundingMode.HALF_EVEN).longValue();
        }
        return numero;
    }

    /**
     * Retorna um número aleatório limitado ao max passado. Este
     * método embora receba long como parámetro trabalha com Inteiro e
     * o maior número a retornar será o Integer.MAX_VALUE.
     *
     * @param max Minimo 1.
     * @return Long limitado ao max, minimo é zero.
     */
    public static Long getMax(final long max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }
        return getIntervalo(0, max);
    }

    /**
     * Não pode ser instânciada.
     */
    private MakeLong() {
    }
}
