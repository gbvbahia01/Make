/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import java.lang.reflect.Field;
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
                logger.debug("Type: " + f.getType().toString());
                boolean accessField = f.isAccessible();
                try {
                    f.setAccessible(true);
                    if (!f.isAnnotationPresent(Null.class)) {
                        ValueFactory valueFactory = Factory.makeFactory(f);
                        valueFactory.makeValue(f, toReturn);
                    }
                    logger.debug("GenericString: "
                            + f.toGenericString()
                            + " value: "
                            + f.get(toReturn));
                } finally {
                    f.setAccessible(accessField);
                }
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
}
