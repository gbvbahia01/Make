/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.wrappers.*;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Factory para classes anotadas com @Min e/ou @Max da JSR303.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class NumberFactory implements ValueFactory {

    /**
     * Contém as fabricas que trabalham com números.
     */
    private static final List<MakeNumber> NUMBERS_FACTORYS =
            new ArrayList<MakeNumber>();

    static {
        NUMBERS_FACTORYS.add(new MakeBigDecimal());
        NUMBERS_FACTORYS.add(new MakeBigInteger());
        NUMBERS_FACTORYS.add(new MakeByte());
        NUMBERS_FACTORYS.add(new MakeDouble());
        NUMBERS_FACTORYS.add(new MakeFloat());
        NUMBERS_FACTORYS.add(new MakeInteger());
        NUMBERS_FACTORYS.add(new MakeLong());
        NUMBERS_FACTORYS.add(new MakeShort());
        NUMBERS_FACTORYS.add(new MakeStringNumber());
    }

    @Override
    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        for (MakeNumber makeNumber : NUMBERS_FACTORYS) {
            if (makeNumber.isMyType(f)) {
                makeNumber.insertValue(f, entity);
                return;
            }
        }
        throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoMinMax"));
    }

    @Override
    public <T> boolean isWorkWith(final Field f, final T entity) {
        if (f.isAnnotationPresent(NotNull.class)) {
            if (isNumber(f)) {
                return true;
            }
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
    private boolean isNumber(final Field f) {
        if (f.isAnnotationPresent(Min.class)
                || f.isAnnotationPresent(Max.class)
                || f.isAnnotationPresent(DecimalMin.class)
                || f.isAnnotationPresent(DecimalMax.class)) {
            return true;
        }
        return false;
    }
}
