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
public class MakeByte extends MakeNumber {

    @Override
    public <T> void insertValue(final Field f, final T entity)
            throws IllegalArgumentException, IllegalAccessException {
           Number[] minMax = getMinMaxValues(f, Byte.MIN_VALUE,
                Byte.MAX_VALUE);
        byte min = minMax[0].byteValue();
        byte max = minMax[1].byteValue();
        if (f.getType().equals(Byte.class)) {
            f.set(entity, MakeByte.getIntervalo(min, max));
        } else {
            f.set(entity, MakeByte.getIntervalo(min, max).byteValue());
        }
    }

    @Override
    public boolean isMyType(final Field f) {
        return isByte(f);
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Byte limitado ao max.
     */
    public static Byte getMax(final byte max) {
        return MakeLong.getMax(max).byteValue();
    }

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Byte getIntervalo(final byte min, final byte max) {
        return MakeLong.getIntervalo(min, max).byteValue();
    }

    /**
     * Retorna True para tipos Byte ou byte.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Byte ou byte, False para outros tipos.
     */
    public static boolean isByte(final Field f) {
        if (f.getType().equals(Byte.class)
                || f.getType().equals(byte.class)) {
            return true;
        }
        return false;
    }
}
