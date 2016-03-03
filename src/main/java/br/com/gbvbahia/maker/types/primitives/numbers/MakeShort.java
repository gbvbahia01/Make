package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;

/**
 * @since v.1 11/05/2012
 * @author Guilherme
 */
public class MakeShort extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, Short.MIN_VALUE, Short.MAX_VALUE);
    short min = minMax[0].shortValue();
    short max = minMax[1].shortValue();
    this.insertValue(field, entity, MakeShort.getRange(min, max).toString());
  }

  @Override
  public <T> void insertValue(final Field field, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    if (field.getType().equals(Short.class)) {
      field.set(entity, new Short(value));
    } else {
      field.set(entity, new Short(value).shortValue());
    }
  }

  @Override
  public boolean isMyType(final Field field) {
    return isShort(field);
  }

  /**
   * A random number limited by max value.
   *
   * @param max maximum acceptable.
   * @return A random byte limited by max value.
   */
  public static Short getMax(short max) {
    return MakeLong.getMax(max).shortValue();
  }

  /**
   * A random number between min and max parameters.
   *
   * @param min minimum number acceptable.
   * @param max maximum number acceptable.
   * @return A random number between min and max parameters.
   */
  public static Short getRange(short min, short max) {
    return MakeLong.getRange(min, max).shortValue();
  }

  /**
   * True is the field type is byte false if is not.
   *
   * @param field that will be evaluated.
   * @return True is the field type is byte false if is not.
   */
  public static boolean isShort(final Field field) {
    if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
      return true;
    }
    return false;
  }
}
