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
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Guilherme
 */
public final class Factory {
    
    public static ValueFactory makeFactory(Field f) {
        if (f.isAnnotationPresent(Min.class)
                || f.isAnnotationPresent(Max.class)){
            return new NumberFactory();
        }
        if (f.isAnnotationPresent(AssertTrue.class)
                || f.isAnnotationPresent(AssertFalse.class)){
            return new TrueFalseFactory();
        }
        return new DefaultFactory();
    }
}
