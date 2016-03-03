package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;

/**
 * @since v.1 11/05/2012
 * @author Guilherme
 */
public class MakeByte extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, Byte.MIN_VALUE, Byte.MAX_VALUE);
    byte min = minMax[0].byteValue();
    byte max = minMax[1].byteValue();
    this.insertValue(field, entity, MakeByte.getRange(min, max).toString());
  }

  @Override
  public <T> void insertValue(final Field field, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    if (field.getType().equals(Byte.class)) {
      field.set(entity, new Byte(value));
    } else {
      field.set(entity, new Byte(value).byteValue());
    }
  }

  @Override
  public boolean isMyType(final Field field) {
    return isByte(field);
  }

  /**
   * A random byte limited by max value.
   * 
   * @param max minimum 1.
   * @return Byte limited by max value.
   */
  public static Byte getMax(byte max) {
    return MakeLong.getMax(max).byteValue();
  }

  /**
   * A random number between min and max parameters.
   * 
   * @param min minimum acceptable.
   * @param max maximum acceptable.
   * @return random between min and max.
   */
  public static Byte getRange(byte min, byte max) {
    return MakeLong.getRange(min, max).byteValue();
  }

  /**
   * True if the Field is Byte type false if is not.
   *
   * @param field Field to be evaluated.
   * @return True if the Field is Byte type false if is not.
   */
  public static boolean isByte(final Field field) {
    if (field.getType().equals(Byte.class) || field.getType().equals(byte.class)) {
      return true;
    }
    return false;
  }
}
