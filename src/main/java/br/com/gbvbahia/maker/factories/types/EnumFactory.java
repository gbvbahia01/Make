/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.lang.reflect.Field;

/**
 *
 * @author Guilherme
 */
public class EnumFactory implements ValueFactory {

    public <T> void makeValue(Field f, T entity) throws IllegalAccessException, IllegalArgumentException {
        Object[] enumConstants = f.getType().getEnumConstants();
        int enumSize = enumConstants.length;
        if (enumSize < 0){
            throw new IllegalArgumentException(I18N.getMsg("enumInvalida", f.getType().getSimpleName()));
        }
        f.set(entity,
                enumConstants[MakeInteger.getMax(enumSize) - 1]);
    }
}
