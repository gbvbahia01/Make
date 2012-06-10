/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 *
 * @author Guilherme
 */
public class MakeBigDecimal extends MakeNumber {

    @Override
    public <T> void insertValue(final Field f, final T entity)
            throws IllegalArgumentException, IllegalAccessException {
        Number[] minMax = getMinMaxValues(f, -Double.MAX_VALUE,
                Double.MAX_VALUE);
        double min = minMax[0].doubleValue();
        double max = minMax[1].doubleValue();
        Double intervalo = new Double(MakeDouble.getIntervalo(min, max));
         f.set(entity,
                 new BigDecimal(maxDecimal(f, intervalo).toString()));
    }

    @Override
    public boolean isMyType(final Field f) {
        return isBigDecimal(f);
    }

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static BigDecimal getIntervalo(final double min, final double max) {
        return new BigDecimal(MakeDouble.getIntervalo(min, max).toString());
    }

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Double limitado ao max.
     */
    public static BigDecimal getMax(final double max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }

        return getIntervalo(0, max);
    }

    /**
     * Retorna True para tipos Float ou float.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Float ou float, False para outros
     * tipos.
     */
    public static boolean isBigDecimal(final Field f) {
        if (f.getType().equals(BigDecimal.class)) {
            return true;
        }
        return false;
    }
}
