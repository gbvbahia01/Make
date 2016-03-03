package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;

/**
 * Random Integer maker.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public class MakeInteger extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, Integer.MIN_VALUE, Integer.MAX_VALUE);
    int min = minMax[0].intValue();
    int max = minMax[1].intValue();
    this.insertValue(field, entity, MakeInteger.getRange(min, max).toString());
  }

  @Override
  public <T> void insertValue(Field field, T entity, String value) throws IllegalArgumentException,
      IllegalAccessException {
    if (field.getType().equals(Integer.class)) {
      field.set(entity, new Integer(value));
    } else {
      field.set(entity, new Integer(value).intValue());
    }
  }

  @Override
  public boolean isMyType(final Field field) {
    return isInteger(field);
  }

  /**
   * A random number between min and max parameters.
   *
   * @param min minimum aceptable.
   * @param max maximum acceptable.
   * @return A random number between min and max parameters.
   */
  public static Integer getRange(int min, int max) {
    return MakeLong.getRange(min, max).intValue();
  }

  /**
   * A random Ineger limited by max value.
   *
   * @param max minimum 1.
   * @return A random Integer limited by max value.
   */
  public static Integer getMax(int max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }
    return MakeLong.getRange(0, max).intValue();
  }

  /**
   * True if is a Integer false if is not.
   * 
   * @param field to be evaluated.
   * @return True if is a Integer false if is not.
   */
  public static boolean isInteger(final Field field) {
    if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
      return true;
    }
    return false;
  }
}
