package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Random Long maker.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeLong extends MakeNumber {

  @Override
  public <T> void insertValue(Field field, T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, Long.MIN_VALUE, Long.MAX_VALUE);
    long min = minMax[0].longValue();
    long max = minMax[1].longValue();
    this.insertValue(field, entity, MakeLong.getRange(min, max).toString());
  }

  @Override
  public <T> void insertValue(final Field field, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    if (field.getType().equals(Long.class)) {
      field.set(entity, new Long(value));
    } else {
      field.set(entity, new Long(value).longValue());
    }
  }

  @Override
  public boolean isMyType(Field field) {
    return isLong(field);
  }

  /**
   * Random engine.
   */
  public static final Random RANDOM = new Random();

  /**
   * A random number between min and max parameters.
   * 
   * @param min minimum acceptable.
   * @param max maximum acceptable.
   * @return A random number between min and max parameters.
   */
  public static Long getRange(long min, long max) {
    if (min > max) {
      LogInfo.logErrorInformation("MakeLong", I18N.getMsg("nimMaiormax", min, max), null);
      throw new IllegalArgumentException(I18N.getMsg("nimMaiormax", new Object[] {min, max}));
    }
    if (min == max) {
      return min;
    }
    double ale = RANDOM.nextDouble();
    long numero;
    if ((min < 0) && (max > 0)) {
      long longValue;
      if ((max + min) == 0) {
        if ((RANDOM.nextInt() % 2) == 0) {
          longValue =
              new BigDecimal((ale * ((max / 2) + min))).setScale(0, RoundingMode.HALF_EVEN)
                  .longValue();
        } else {
          longValue =
              new BigDecimal((ale * (max + (min / 2)))).setScale(0, RoundingMode.HALF_EVEN)
                  .longValue();
        }
      } else {
        longValue =
            new BigDecimal((ale * (max + min))).setScale(0, RoundingMode.HALF_EVEN).longValue();
      }
      if (longValue > 0) {
        numero = min + longValue;
      } else {
        numero = max + longValue;
      }
    } else {
      try {
        numero =
            min
                + new BigDecimal((ale * (max - min))).setScale(0, RoundingMode.HALF_EVEN)
                    .longValue();
      } catch (StackOverflowError s) {
        LogInfo.logWarnInformation("MakeLong", I18N.getMsg("bigErroStack", max, min, ale));
        throw s;
      }
    }
    return numero;
  }

  /**
   * A random long limited by max value.
   *
   * @param max minimum 1.
   * @return A random long limited by max value.
   */
  public static Long getMax(long max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }
    return getRange(0, max);
  }

  /**
   * True if the field type is Long false if is not.
   *
   * @param field to be evaluated.
   * @return True if the field type is Long false if is not.
   */
  public static boolean isLong(final Field field) {
    if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
      return true;
    }
    return false;
  }
}
