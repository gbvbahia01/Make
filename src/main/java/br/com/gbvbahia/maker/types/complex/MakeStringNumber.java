package br.com.gbvbahia.maker.types.complex;

import java.lang.reflect.Field;

import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;

/**
 *
 * @author Guilherme
 */
public class MakeStringNumber extends MakeNumber {

  @Override
  public <T> void insertValue(final Field f, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(f, -Double.MAX_VALUE, Double.MAX_VALUE);
    double min = minMax[0].doubleValue();
    double max = minMax[1].doubleValue();
    Double intervalo = MakeDouble.getIntervalo(min, max);
    this.insertValue(f, entity, this.maxDecimal(f, intervalo).toString());
  }

  @Override
  public <T> void insertValue(final Field f, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    f.set(entity, value);
  }

  @Override
  public boolean isMyType(final Field f) {
    if (f.getType().equals(String.class)) {
      return true;
    }
    return false;
  }
}
