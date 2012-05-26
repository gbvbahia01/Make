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

/**
 * Factory para classes anotadas com @AssertTrue e/ou @AssertFalse da
 * JSR303.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class TrueFalseFactory implements ValueFactory {

    /**
     * Nome da entidade que está tendo um atributo fabricado.
     */
    private String entityName;

    @Override
    public <T> void makeValue(Field f, T entity)
            throws IllegalAccessException, IllegalArgumentException {
        this.entityName = entity.getClass().getSimpleName();
        if (f.getType().equals(Boolean.class)) {
            f.set(entity, valueToBoolean(f));
        } else if (f.getType().equals(boolean.class)) {
            f.set(entity, valueToBoolean(f).booleanValue());
        } else {
            throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoTrueFalse"));
        }
    }

    private Boolean valueToBoolean(Field f) {
        if (f.isAnnotationPresent(AssertTrue.class)) {
            return true;
        }
        if (f.isAnnotationPresent(AssertFalse.class)) {
            return false;
        }
        LogInfo.logDefaultValue(entityName, f, "TrueFalseFactory");
        return MakeInteger.getMax(2) == 2;
    }
}
