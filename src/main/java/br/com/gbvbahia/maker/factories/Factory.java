/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories;

import br.com.gbvbahia.maker.factories.types.DefaultFactory;
import br.com.gbvbahia.maker.factories.types.NumberFactory;
import br.com.gbvbahia.maker.factories.types.TrueFalseFactory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import java.lang.reflect.Field;
import javax.validation.constraints.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Guilherme
 */
public final class Factory {

    private static Log logger = LogFactory.getLog("Factory");

    public static ValueFactory makeFactory(Field f) {
        if (f.isAnnotationPresent(Min.class)
                || f.isAnnotationPresent(Max.class)
                || f.isAnnotationPresent(DecimalMin.class)
                || f.isAnnotationPresent(DecimalMax.class)) {
            return new NumberFactory();
        }
        if (f.isAnnotationPresent(AssertTrue.class)
                || f.isAnnotationPresent(AssertFalse.class)) {
            return new TrueFalseFactory();
        }
        return new DefaultFactory();
    }
}