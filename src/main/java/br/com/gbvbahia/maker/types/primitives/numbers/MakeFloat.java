package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;

/**
 * Random Long maker.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeFloat extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, -Float.MAX_VALUE, Float.MAX_VALUE);
    float min = minMax[0].floatValue();
    float max = minMax[1].floatValue();
    Float intervalo = MakeFloat.getRange(min, max);
    this.insertValue(field, entity,
        new Float(this.maxDecimal(field, intervalo).floatValue()).toString());
  }

  @Override
  public <T> void insertValue(final Field field, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    if (field.getType().equals(Float.class)) {
      field.set(entity, new Float(value));
    } else {
      field.set(entity, new Float(value).floatValue());
    }
  }

  @Override
  public boolean isMyType(final Field field) {
    return isFloat(field);
  }

  /**
   * A random number between min and max parameters.
   *
   * @param min minimum acceptable.
   * @param max maximum acceptable.
   * @return A random number between min and max parameters.
   */
  public static Float getRange(float min, float max) {
    float numero =
        MakeDouble.getRange(new Float(min).doubleValue(), new Float(max).floatValue()).floatValue();
    return numero;
  }

  /**
   * A random Float limited by max value.
   * 
   * @param max minimum 1.
   * @return A random float limited by max value.
   */
  public static Float getMax(float max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }

    return getRange(0, max);
  }

  /**
   * True if the field type is Float false if not.
   * 
   * @param field to be evaluated.
   * @return True if the field type is Float false if not.
   */
  public static boolean isFloat(final Field field) {
    if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
      return true;
    }
    return false;
  }
}
