/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.lang.reflect.Field;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EnumFactory implements ValueFactory {

    public <T> void makeValue(String testName, Field f, T entity,
            boolean makeRelationships) throws IllegalAccessException,
            IllegalArgumentException {
        Object[] enumConstants = f.getType().getEnumConstants();
        int enumSize = enumConstants.length;
        if (enumSize <= 0) {
            throw new UnsupportedOperationException(I18N.getMsg("enumInvalida", f.getType().getSimpleName()));
        }
        f.set(entity,
                enumConstants[MakeInteger.getIntervalo(0, enumSize - 1)]);
    }

    public <T> boolean isWorkWith(Field f, T entity) {
        if (f.isAnnotationPresent(NotNull.class)) {
            if (f.getType().isEnum()) {
                return true;
            }
        }
        return false;
    }
}
