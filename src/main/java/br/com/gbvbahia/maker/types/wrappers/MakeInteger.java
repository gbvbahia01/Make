/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import java.lang.reflect.Field;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public class MakeInteger extends MakeNumber {

    @Override
    public <T> void insertValue(final Field f, final T entity)
            throws IllegalArgumentException, IllegalAccessException {
        Number[] minMax = getMinMaxValues(f, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
        int min = minMax[0].intValue();
        int max = minMax[1].intValue();
        if (f.getType().equals(Integer.class)) {
            f.set(entity, MakeInteger.getIntervalo(min, max));
        } else {
            f.set(entity, MakeInteger.getIntervalo(min, max).intValue());
        }
    }

    @Override
    public boolean isMyType(final Field f) {
        return isInteger(f);
    }

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
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }
        return MakeLong.getIntervalo(0, max).intValue();
    }

    /**
     * Retorna True para tipos Integer ou int.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Integer ou int, False para outros
     * tipos.
     */
    public static boolean isInteger(final Field f) {
        if (f.getType().equals(Integer.class)
                || f.getType().equals(int.class)) {
            return true;
        }
        return false;
    }
}
