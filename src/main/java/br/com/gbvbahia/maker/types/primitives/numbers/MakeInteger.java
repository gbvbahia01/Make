package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public class MakeInteger extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity)
      throws IllegalArgumentException, IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, Integer.MIN_VALUE, Integer.MAX_VALUE);
    int min = minMax[0].intValue();
    int max = minMax[1].intValue();
    this.insertValue(field, entity, MakeInteger.getIntervalo(min, max).toString());
  }

  @Override
  public <T> void insertValue(Field field, T entity, String value)
      throws IllegalArgumentException, IllegalAccessException {
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
   * Gera um número entre os valores solicitados.
   *
   * @param min Número minimo aceitavel.
   * @param max Número máximo aceitavel.
   * @return Número aleatório.
   */
  public static Integer getIntervalo(final int min, final int max) {
    return MakeLong.getIntervalo(min, max).intValue();
  }

  /**
   * Retorna um número aleatório limitado ao max passado.
   *
   * @param max Minimo 1.
   * @return Integer limitado ao max.
   */
  public static Integer getMax(final int max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }
    return MakeLong.getIntervalo(0, max).intValue();
  }

  /**
   * Retorna True para tipos Integer ou int.
   *
   * @param field Field a ser avaliado.
   * @return True para tipos Integer ou int, False para outros tipos.
   */
  public static boolean isInteger(final Field field) {
    if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
      return true;
    }
    return false;
  }
}
