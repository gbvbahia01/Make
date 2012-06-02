/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeDouble {


    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Double getIntervalo(final double min, final double max) {
        if (min > max) {
            throw new IllegalArgumentException(I18N.getMsg("nimMaiormax",
                    new Object[]{min, max}));
        }
        double ale = MakeLong.RANDOM.nextDouble();
        double numero;
        if (min < 0 && max > 0) {
            if (MakeLong.RANDOM.nextInt() % 2 == 0) {
                numero = new BigDecimal((ale * (max + 0))).doubleValue();
            }else {
                numero = new BigDecimal((ale * (0 + min))).doubleValue();
            }
        } else {
            numero = min + new BigDecimal((ale * (max - min))).doubleValue();
        }
        return numero;
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Double limitado ao max. Minimo é zero.
     */
    public static Double getMax(final double max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }

        return getIntervalo(0, max);
    }

    /**
     * Não pode ser instânciada.
     */
    private MakeDouble() {
    }

    /**
     * Retorna True para tipos Double ou double.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Double ou double, False para outros
     * tipos.
     */
    public static boolean isDouble(Field f) {
        if (f.getType().equals(Double.class)
                || f.getType().equals(double.class)) {
            return true;
        }
        return false;
    }
}
