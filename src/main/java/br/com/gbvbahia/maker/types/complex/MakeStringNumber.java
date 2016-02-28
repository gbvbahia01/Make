package br.com.gbvbahia.maker.types.complex;

import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;

import java.lang.reflect.Field;

/**
 * @since v.1
 * @author Guilherme
 */
public class MakeStringNumber extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity)
      throws IllegalArgumentException, IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, -Double.MAX_VALUE, Double.MAX_VALUE);
    double min = minMax[0].doubleValue();
    double max = minMax[1].doubleValue();
    Double intervalo = MakeDouble.getIntervalo(min, max);
    this.insertValue(field, entity, this.maxDecimal(field, intervalo).toString());
  }

  @Override
  public <T> void insertValue(final Field field, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    field.set(entity, value);
  }

  @Override
  public boolean isMyType(final Field field) {
    if (field.getType().equals(String.class)) {
      return true;
    }
    return false;
  }
}
