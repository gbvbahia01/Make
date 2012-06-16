/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.lang.reflect.Field;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

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

    public <T> void makeValue(String testName, Field f, T entity, boolean makeRelationships)
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

    @Override
    public <T> boolean isWorkWith(Field f, T entity) {
        if (f.isAnnotationPresent(NotNull.class)) {
            if (isBoolean(f)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o field é tratado com anotações booleanas da
     * JSR303.
     *
     * @param f Field a ser avaliado.
     * @return True para possui anotação booleana False para não
     * possui.
     */
    private boolean isBoolean(Field f) {
        if (f.isAnnotationPresent(AssertTrue.class)
                || f.isAnnotationPresent(AssertFalse.class)) {
            return true;
        }
        return false;
    }
}
