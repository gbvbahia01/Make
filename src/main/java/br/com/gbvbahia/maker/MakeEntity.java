/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEntity {

    private static Log logger = LogFactory.getLog("MakeEntity");

    public static <T> T makeEntity(Class<T> entity) {
        try {
            T toReturn = entity.newInstance();
            for (Field f : toReturn.getClass().getDeclaredFields()) {
                logger.debug("GenericType: " + f.getGenericType().toString());
                logger.debug("Type: " + f.getType().toString());
                boolean accessField = f.isAccessible();
                f.setAccessible(true);
                if (f.isAnnotationPresent(NotNull.class)) {
                    insertValue(f, toReturn);
                }
                f.setAccessible(accessField);
            }
            return toReturn;
        } catch (InstantiationException ex) {
            logger.error(I18N.getMsg("instantiationException",
                    entity.getName()), ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            logger.error(I18N.getMsg("illegalAccessException",
                    entity.getName()), ex);
            throw new RuntimeException(ex);
        }
    }

    private static <T> void insertValue(Field f, T toReturn)
            throws IllegalAccessException, IllegalArgumentException {
        if (f.getType().equals(Integer.class)) {
            f.set(toReturn, valueToInteger(f));
        } else if (f.getType().equals(int.class)) {
            f.set(toReturn, valueToInteger(f).intValue());
        } else if (f.getType().equals(Long.class)) {
            f.set(toReturn, valueToLong(f));
        } else if (f.getType().equals(long.class)) {
            f.set(toReturn, valueToLong(f).longValue());
        } else if (f.getType().equals(Byte.class)) {
            f.set(toReturn, valueToByte(f));
        } else if (f.getType().equals(byte.class)) {
            f.set(toReturn, valueToByte(f).byteValue());
        } else if (f.getType().equals(Short.class)) {
            f.set(toReturn, valueToShort(f));
        } else if (f.getType().equals(short.class)) {
            f.set(toReturn, valueToShort(f).shortValue());
        } else if (f.getType().equals(BigInteger.class)) {
            f.set(toReturn, valueToBigInteger(f));
        } else if (f.getType().equals(BigDecimal.class)) {
            f.set(toReturn, valueToBigDecimal(f));
        }
    }

    private static Integer valueToInteger(Field f) {
        Long[] minMax = getMinMaxLongValues(f, Integer.MAX_VALUE);
        int min = minMax[0].intValue();
        int max = minMax[1].intValue();
        return MakeInteger.getIntervalo(min, max);
    }

    private static Long valueToLong(Field f) {
        Long[] minMax = getMinMaxLongValues(f, Long.MAX_VALUE);
        long min = minMax[0];
        long max = minMax[1];
        return MakeLong.getIntervalo(min, max);
    }

    private static BigInteger valueToBigInteger(Field f) {
        Long[] minMax = getMinMaxLongValues(f, Long.MAX_VALUE);
        long min = minMax[0];
        long max = minMax[1];
        return new BigInteger(new Long(MakeLong.getIntervalo(min,
                max)).toString());
    }

    private static BigDecimal valueToBigDecimal(Field f) {
        Long[] minMax = getMinMaxLongValues(f, Long.MAX_VALUE);
        long min = minMax[0];
        long max = minMax[1];
        return new BigDecimal(new Double(MakeDouble.getIntervalo(min,
                max)).toString());
    }

    private static Byte valueToByte(Field f) {
        Long[] minMax = getMinMaxLongValues(f, Byte.MAX_VALUE);
        byte min = minMax[0].byteValue();
        byte max = minMax[1].byteValue();
        return MakeByte.getIntervalo(min, max);
    }

    private static Short valueToShort(Field f) {
        Long[] minMax = getMinMaxLongValues(f, Short.MAX_VALUE);
        short min = minMax[0].shortValue();
        short max = minMax[1].shortValue();
        return MakeShort.getIntervalo(min, max);
    }

    private static Long[] getMinMaxLongValues(Field f, Number maxValue) {
        Long[] toReturn = new Long[2];
        if (f.isAnnotationPresent(Min.class)) {
            toReturn[0] = new Long(f.getAnnotation(Min.class).value());
        } else {
            toReturn[0] = 0l;
        }
        if (f.isAnnotationPresent(Max.class)) {
            toReturn[1] = new Long(f.getAnnotation(Max.class).value());
        } else {
            toReturn[1] = maxValue.longValue();
        }
        return toReturn;
    }
}
