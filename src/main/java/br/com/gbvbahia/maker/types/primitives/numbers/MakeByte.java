package br.com.gbvbahia.maker.types.primitives.numbers;

import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;

/**
 * @since v.1 11/05/2012
 * @author Guilherme
 */
public class MakeByte extends MakeNumber {

  @Override
  public <T> void insertValue(final Field field, final T entity)
      throws IllegalArgumentException, IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(field, Byte.MIN_VALUE, Byte.MAX_VALUE);
    byte min = minMax[0].byteValue();
    byte max = minMax[1].byteValue();
    this.insertValue(field, entity, MakeByte.getIntervalo(min, max).toString());
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
   * Retorna um número aleatório limitado ao max passado.
   *
   * @param max Minimo 1.
   * @return Byte limitado ao max.
   */
  public static Byte getMax(final byte max) {
    return MakeLong.getMax(max).byteValue();
  }

  /**
   * Gera um número entre os valores solicitados.
   *
   * @param min Número minimo aceitavel.
   * @param max Número máximo aceitavel.
   * @return Número aleatório.
   */
  public static Byte getIntervalo(final byte min, final byte max) {
    return MakeLong.getIntervalo(min, max).byteValue();
  }

  /**
   * Retorna True para tipos Byte ou byte.
   *
   * @param field Field a ser avaliado.
   * @return True para tipos Byte ou byte, False para outros tipos.
   */
  public static boolean isByte(final Field field) {
    if (field.getType().equals(Byte.class) || field.getType().equals(byte.class)) {
      return true;
    }
    return false;
  }
}
