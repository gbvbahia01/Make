/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.lang.reflect.Field;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Factory para classes anotadas com @AssertTrue e/ou @AssertFalse da
 * JSR303.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class TrueFalseFactory implements ValueFactory {

    private static Log logger = LogFactory.getLog("TrueFalseFactory");

    @Override
    public <T> void makeValue(Field f, T entity)
            throws IllegalAccessException, IllegalArgumentException {
        if (f.getType().equals(Boolean.class)) {
            f.set(entity, valueToBoolean(f));
        } else if (f.getType().equals(boolean.class)) {
            f.set(entity, valueToBoolean(f).booleanValue());
        } else {
            throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoTrueFalse"));
        }
    }

    private static Boolean valueToBoolean(Field f) {
        if (f.isAnnotationPresent(AssertTrue.class)) {
            return true;
        }
        if (f.isAnnotationPresent(AssertFalse.class)) {
            return false;
        }
        logger.debug(I18N.getMsg("defaultValue",
                f.getType().getSimpleName()));
        return MakeInteger.getMax(2) == 2;
    }
}
