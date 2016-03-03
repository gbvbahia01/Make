package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Random Double maker.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeDouble extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, -Double.MAX_VALUE, Double.MAX_VALUE);
    double min = minMax[0].doubleValue();
    double max = minMax[1].doubleValue();
    Double intervalo = MakeDouble.getRange(min, max);
    this.insertValue(field, entity,
        new Double(this.maxDecimal(field, intervalo).doubleValue()).toString());
  }

  @Override
  public <T> void insertValue(final Field field, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    if (field.getType().equals(Double.class)) {
      field.set(entity, new Double(value));
    } else {
      field.set(entity, new Double(value).doubleValue());
    }
  }

  @Override
  public boolean isMyType(Field field) {
    return isDouble(field);
  }

  /**
   * A random number between min and max parameters.
   *
   * @param min minimum acceptable.
   * @param max maximum acceptable.
   * @return A random number between min and max parameters.
   */
  public static Double getRange(double min, double max) {
    if (min > max) {
      LogInfo.logErrorInformation("MakeLong", I18N.getMsg("nimMaiormax", min, max), null);
      throw new IllegalArgumentException(I18N.getMsg("nimMaiormax", new Object[] {min, max}));
    }
    double ale = MakeLong.RANDOM.nextDouble();
    double numero;
    if ((min < 0) && (max > 0)) {
      if ((MakeLong.RANDOM.nextInt() % 2) == 0) {
        numero = new BigDecimal((ale * (max + 0))).doubleValue();
      } else {
        numero = new BigDecimal((ale * (0 + min))).doubleValue();
      }
    } else {
      numero = min + new BigDecimal((ale * (max - min))).doubleValue();
    }
    return numero;
  }

  /**
   * A random float limited by max value.
   *
   * @param max minimum 1.
   * @return A random float limited by max value.
   */
  public static Double getMax(double max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }

    return getRange(0, max);
  }

  /**
   * True if field type is double false if not.
   * 
   * @param field to be evaluated.
   * @return True if field type is double false if not.
   */
  public static boolean isDouble(final Field field) {
    if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
      return true;
    }
    return false;
  }
}
