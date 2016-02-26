/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.primitives.numbers;

import java.lang.reflect.Field;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeFloat extends MakeNumber {

  @Override
  public <T> void insertValue(final Field f, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(f, -Float.MAX_VALUE, Float.MAX_VALUE);
    float min = minMax[0].floatValue();
    float max = minMax[1].floatValue();
    Float intervalo = MakeFloat.getIntervalo(min, max);
    this.insertValue(f, entity, new Float(this.maxDecimal(f, intervalo).floatValue()).toString());
  }

  @Override
  public <T> void insertValue(final Field f, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    if (f.getType().equals(Float.class)) {
      f.set(entity, new Float(value));
    } else {
      f.set(entity, new Float(value).floatValue());
    }
  }

  @Override
  public boolean isMyType(final Field f) {
    return isFloat(f);
  }

  /**
   * Gera um número entre os valores solicitados.
   *
   * @param min Número minimo aceitavel.
   * @param max Número máximo aceitavel.
   * @return Número aleatório.
   */
  public static Float getIntervalo(final float min, final float max) {
    float numero =
        MakeDouble.getIntervalo(new Float(min).doubleValue(), new Float(max).floatValue())
            .floatValue();
    return numero;
  }

  /**
   * Retorna um número aleatório limitado ao max passado.
   *
   * @param max Minimo 1.
   * @return Double limitado ao max.
   */
  public static Float getMax(final float max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }

    return getIntervalo(0, max);
  }

  /**
   * Retorna True para tipos Float ou float.
   *
   * @param f Field a ser avaliado.
   * @return True para tipos Float ou float, False para outros tipos.
   */
  public static boolean isFloat(final Field f) {
    if (f.getType().equals(Float.class) || f.getType().equals(float.class)) {
      return true;
    }
    return false;
  }
}
