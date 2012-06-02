/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import java.lang.reflect.Field;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public final class MakeFloat {

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Float getIntervalo(final float min, final float max) {
        if (min > max) {
            throw new IllegalArgumentException(I18N.getMsg("nimMaiormax",
                    new Object[]{min, max}));
        }
        float numero = MakeDouble.getIntervalo(new Float(min).doubleValue(),
                new Float(max).floatValue()).floatValue();
        return numero;
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Double limitado ao max.
     */
    public static Float getMax(final float max) {
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

    /**
     * Retorna True para tipos Float ou float.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Float ou float, False para outros
     * tipos.
     */
    public static boolean isFloat(Field f) {
        if (f.getType().equals(Float.class)
                || f.getType().equals(float.class)) {
            return true;
        }
        return false;
    }
}
