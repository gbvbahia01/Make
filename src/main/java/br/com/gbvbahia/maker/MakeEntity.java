/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
                    }
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

    private static Integer valueToInteger(Field f) {
        int min = 0;
        int max = Integer.MAX_VALUE;
        if (f.isAnnotationPresent(Min.class)) {
            min = new Long(f.getAnnotation(Min.class).value()).intValue();
        }
        if (f.isAnnotationPresent(Max.class)) {
            max = new Long(f.getAnnotation(Max.class).value()).intValue();
        }
        return MakeInteger.getIntervalo(min, max);
    }

    private static Long valueToLong(Field f) {
        long min = 0;
        long max = Long.MAX_VALUE;
        if (f.isAnnotationPresent(Min.class)) {
            min = new Long(f.getAnnotation(Min.class).value()).intValue();
        }
        if (f.isAnnotationPresent(Max.class)) {
            max = new Long(f.getAnnotation(Max.class).value()).intValue();
        }
        return MakeLong.getIntervalo(min, max);
    }

    private static Byte valueToByte(Field f) {
        byte min = 0;
        byte max = Byte.MAX_VALUE;
        if (f.isAnnotationPresent(Min.class)) {
            min = new Long(f.getAnnotation(Min.class).value()).byteValue();
        }
        if (f.isAnnotationPresent(Max.class)) {
            max = new Long(f.getAnnotation(Max.class).value()).byteValue();
        }
        return MakeByte.getIntervalo(min, max);
    }

    private static Short valueToShort(Field f) {
        short min = 0;
        short max = Short.MAX_VALUE;
        if (f.isAnnotationPresent(Min.class)) {
            min = new Long(f.getAnnotation(Min.class).value()).shortValue();
        }
        if (f.isAnnotationPresent(Max.class)) {
            max = new Long(f.getAnnotation(Max.class).value()).shortValue();
        }
        return MakeShort.getIntervalo(min, max);
    }
}
