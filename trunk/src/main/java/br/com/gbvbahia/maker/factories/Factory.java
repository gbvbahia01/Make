/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.factories.types.*;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;
import org.apache.commons.logging.Log;

/**
 *
 * @author Guilherme
 */
public final class Factory {

    private static Log logger = LogInfo.getLog("Factory");

    public static ValueFactory makeFactory(Field f,
            Map<String, List<String>> patternsList) {
        if (f.isAnnotationPresent(Pattern.class)) {
            return new PatternFactory(patternsList);
        }
        if (f.isAnnotationPresent(Size.class)) {
            return new SizeFactory();
        }
        if (f.isAnnotationPresent(Min.class)
                || f.isAnnotationPresent(Max.class)
                || f.isAnnotationPresent(DecimalMin.class)
                || f.isAnnotationPresent(DecimalMax.class)) {
            return new NumberFactory();
        }
        if (f.isAnnotationPresent(Future.class)
                || f.isAnnotationPresent(Past.class)) {
            return new DateFactory();
        }
        if (f.isAnnotationPresent(AssertTrue.class)
                || f.isAnnotationPresent(AssertFalse.class)) {
            return new TrueFalseFactory();
        }
        if (f.getType().isEnum()) {
            return new EnumFactory();
        }
        
        return new DefaultFactory();
    }
}
