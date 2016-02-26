/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.complex;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;

/**
 * A number factory to work with BigDecimal fields.
 * 
 * @since v1 12/2012
 * @author Guilherme
 */
public class MakeBigDecimal extends MakeNumber {

  @Override
  public <T> void insertValue(Field field, T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, -Double.MAX_VALUE, Double.MAX_VALUE);
    double min = minMax[0].doubleValue();
    double max = minMax[1].doubleValue();
    Double intervalo = new Double(MakeDouble.getIntervalo(min, max));
    this.insertValue(field, entity, this.maxDecimal(field, intervalo).toString());
  }

  @Override
  public <T> void insertValue(Field field, T entity, String value) throws IllegalArgumentException,
      IllegalAccessException {
    field.set(entity, new BigDecimal(value));
  }

  @Override
  public boolean isMyType(final Field field) {
    return isBigDecimal(field);
  }

  /**
   * Makes a number between min parameter and max parameter.
   *
   * @param min minimum acceptable number.
   * @param max maximum acceptable number.
   * @return a random number between min and max parameters.
   */
  public static BigDecimal getIntervalo(final double min, final double max) {
    return new BigDecimal(MakeDouble.getIntervalo(min, max).toString());
  }

  /**
   * Creates a number up max parameter.<br>
   * The minimum acceptable as parameter max is 1.
   *
   * @param max the maximum value the return can be. Must be at least 1.
   * @return a value up max.
   */
  public static BigDecimal getMax(final double max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }

    return getIntervalo(0, max);
  }

  /**
   * Checks if the field is a type BigDecimal.
   *
   * @param field to be evaluated.
   * @return true if is BigDecimal type false if not.
   */
  public static boolean isBigDecimal(final Field field) {
    if (field.getType().equals(BigDecimal.class)) {
      return true;
    }
    return false;
  }
}
