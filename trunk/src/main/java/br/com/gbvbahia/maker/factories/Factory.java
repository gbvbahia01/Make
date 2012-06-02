/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories;

import br.com.gbvbahia.maker.factories.types.*;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;

/**
 *
 * @author Guilherme
 */
public final class Factory {

    public static ValueFactory makeFactory(Field f,
            Map<String, List<String>> patternsList) {
        if (f.isAnnotationPresent(Pattern.class)) {
            return new PatternFactory(patternsList);
        }
        if (f.isAnnotationPresent(Size.class)) {
            return new SizeFactory();
        }
        if (isNumber(f)) {
            return new NumberFactory();
        }
        if (isDate(f)) {
            return new DateFactory();
        }
        if (isBoolean(f)) {
            return new TrueFalseFactory();
        }
        if (f.getType().isEnum()) {
            return new EnumFactory();
        }
        return new DefaultFactory();
    }

    /**
     * Verifica se o field é tratado com anotações de tempo da JSR303.
     *
     * @param f Field a ser avaliado.
     * @return True para possui anotação de tempo False para não
     * possui.
     */
    private static boolean isDate(Field f) {
        if (f.isAnnotationPresent(Future.class)
                || f.isAnnotationPresent(Past.class)) {
            return true;
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
    private static boolean isBoolean(Field f) {
        if (f.isAnnotationPresent(AssertTrue.class)
                || f.isAnnotationPresent(AssertFalse.class)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o field é tratado com anotações numéricas da
     * JSR303.
     *
     * @param f Field a ser avaliado.
     * @return True para possui anotação numérica False para não
     * possui.
     */
    private static boolean isNumber(Field f) {
        if (f.isAnnotationPresent(Min.class)
                || f.isAnnotationPresent(Max.class)
                || f.isAnnotationPresent(DecimalMin.class)
                || f.isAnnotationPresent(DecimalMax.class)) {
            return true;
        }
        return false;
    }

    /**
     * Não pode ser instânciado.
     */
    private Factory() {
    }
}
