/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import java.lang.reflect.Field;

/**
 * @since v.1 11/05/2012
 * @author Guilherme
 */
public class MakeShort extends MakeNumber {

    @Override
    public <T> void insertValue(final Field f, final T entity)
            throws IllegalArgumentException, IllegalAccessException {
        Number[] minMax = getMinMaxValues(f, Short.MIN_VALUE,
                Short.MAX_VALUE);
        short min = minMax[0].shortValue();
        short max = minMax[1].shortValue();
        if (f.getType().equals(Short.class)) {
            f.set(entity, MakeShort.getIntervalo(min, max));
        } else {
            f.set(entity, MakeShort.getIntervalo(min, max).shortValue());
        }
    }

    @Override
    public boolean isMyType(final Field f) {
        return isShort(f);
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Short limitado ao max.
     */
    public static Short getMax(final short max) {
        return MakeLong.getMax(max).shortValue();
    }

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Short getIntervalo(final short min, final short max) {
        return MakeLong.getIntervalo(min, max).shortValue();
    }

    /**
     * Retorna True para tipos Short ou short.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Short ou short, False para outros
     * tipos.
     */
    public static boolean isShort(final Field f) {
        if (f.getType().equals(Short.class)
                || f.getType().equals(short.class)) {
            return true;
        }
        return false;
    }
}
