/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import br.com.gbvbahia.maker.types.string.MakeString;
import java.lang.reflect.Field;

/**
 *
 * @author Guilherme
 */
public class MakeStringNumber extends MakeNumber {

    @Override
    public <T> void insertValue(final Field f, final T entity)
            throws IllegalArgumentException, IllegalAccessException {
        Number[] minMax = getMinMaxValues(f, -Double.MAX_VALUE,
                Double.MAX_VALUE);
        double min = minMax[0].doubleValue();
        double max = minMax[1].doubleValue();
        Double intervalo = MakeDouble.getIntervalo(min, max);
        f.set(entity, maxDecimal(f, intervalo).toString());
    }

    @Override
    public boolean isMyType(final Field f) {
        if (f.getType().equals(String.class)) {
            return true;
        }
        return false;
    }
}
