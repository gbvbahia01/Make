/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import java.lang.reflect.Field;
import java.math.BigInteger;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since v.1 10/06/2012
 * @author Guilherme
 */
public class MakeBigInteger extends MakeNumber {

    @Override
    public <T> void insertValue(final Field f, final T entity)
            throws IllegalArgumentException, IllegalAccessException {
        Number[] minMax = getMinMaxValues(f, Long.MIN_VALUE,
                Long.MAX_VALUE);
        long min = minMax[0].longValue();
        long max = minMax[1].longValue();
        f.set(entity, new BigInteger((MakeLong.getIntervalo(min,
                max)).toString()));
    }

    @Override
    public boolean isMyType(final Field f) {
        return isBigInteger(f);
    }

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static BigInteger getIntervalo(final long min, final long max) {
        return new BigInteger(MakeLong.getIntervalo(min, max).toString());
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Integer limitado ao max.
     */
    public static BigInteger getMax(final long max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }
        return new BigInteger(MakeLong.getIntervalo(0, max).toString());
    }

    /**
     * Retorna True para tipos Integer ou int.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Integer ou int, False para outros
     * tipos.
     */
    public static boolean isBigInteger(final Field f) {
        if (f.getType().equals(BigInteger.class)) {
            return true;
        }
        return false;
    }
}
