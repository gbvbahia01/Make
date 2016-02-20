package br.com.gbvbahia.maker.factories.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.complex.MakeBigDecimal;
import br.com.gbvbahia.maker.types.complex.MakeBigInteger;
import br.com.gbvbahia.maker.types.complex.MakeStringNumber;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeByte;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeFloat;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeLong;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeShort;

/**
 * Factory para classes anotadas com @Min e/ou @Max da JSR303.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class MaxMinFactory implements ValueFactory {

  /**
   * Contém as fabricas que trabalham com números.
   */
  public static final List<MakeNumber> NUMBERS_FACTORYS = new ArrayList<MakeNumber>();

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
  public <T> void makeValue(final Field field, final T entity, final String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    for (MakeNumber makeNumber : NUMBERS_FACTORYS) {
      if (makeNumber.isMyType(field)) {
        makeNumber.insertValue(field, entity);
        return;
      }
    }
    throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoMinMax"));
  }

  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    if (this.isNumber(field)) {
      return true;
    }
    return false;
  }

  /**
   * Verifica se o field é tratado com anotações numéricas da JSR303.
   *
   * @param field Field a ser avaliado.
   * @return True para possui anotação numérica False para não possui.
   */
  private boolean isNumber(final Field field) {
    if (field.isAnnotationPresent(Min.class) || field.isAnnotationPresent(Max.class)
        || field.isAnnotationPresent(DecimalMin.class)
        || field.isAnnotationPresent(DecimalMax.class)) {
      return true;
    }
    return false;
  }
}
