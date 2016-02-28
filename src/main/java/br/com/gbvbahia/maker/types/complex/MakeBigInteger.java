package br.com.gbvbahia.maker.types.complex;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeLong;

import java.lang.reflect.Field;
import java.math.BigInteger;

/**
 * A number factory to work with BigInteger fields.
 *
 * @since v.1 10/06/2012
 * @author Guilherme Braga
 */
public class MakeBigInteger extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity)
      throws IllegalArgumentException, IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, Long.MIN_VALUE, Long.MAX_VALUE);
    long min = minMax[0].longValue();
    long max = minMax[1].longValue();
    this.insertValue(field, entity, MakeLong.getIntervalo(min, max).toString());
  }

  @Override
  public <T> void insertValue(Field field, T entity, String value)
      throws IllegalArgumentException, IllegalAccessException {
    field.set(entity, new BigInteger(value));
  }

  @Override
  public boolean isMyType(final Field field) {
    return isBigInteger(field);
  }

  /**
   * Makes a number between min parameter and max parameter.
   *
   * @param min minimum acceptable number.
   * @param max maximum acceptable number.
   * @return a random number between min and max parameters.
   */
  public static BigInteger getIntervalo(final long min, final long max) {
    return new BigInteger(MakeLong.getIntervalo(min, max).toString());
  }

  /**
   * Creates a number up max parameter.<br>
   * The minimum acceptable as parameter max is 1.
   *
   * @param max the maximum value the return can be. Must be at least 1.
   * @return a value up max.
   */
  public static BigInteger getMax(final long max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }
    return new BigInteger(MakeLong.getIntervalo(0, max).toString());
  }

  /**
   * Checks if the field is a type BigInteger.
   *
   * @param field to be evaluated.
   * @return true if is BigInteger type false if not.
   */
  public static boolean isBigInteger(final Field field) {
    if (field.getType().equals(BigInteger.class)) {
      return true;
    }
    return false;
  }
}
