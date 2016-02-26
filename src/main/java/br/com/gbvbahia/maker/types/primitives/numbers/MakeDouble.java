/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.primitives.numbers;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

/**
 * Gerador de números decimais aleatório.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeDouble extends MakeNumber {

  @Override
  public <T> void insertValue(final Field f, final T entity) throws IllegalArgumentException,
      IllegalAccessException {
    Number[] minMax = this.getMinMaxValues(f, -Double.MAX_VALUE, Double.MAX_VALUE);
    double min = minMax[0].doubleValue();
    double max = minMax[1].doubleValue();
    Double intervalo = MakeDouble.getIntervalo(min, max);
    this.insertValue(f, entity, new Double(this.maxDecimal(f, intervalo).doubleValue()).toString());
  }

  @Override
  public <T> void insertValue(final Field f, final T entity, final String value)
      throws IllegalArgumentException, IllegalAccessException {
    if (f.getType().equals(Double.class)) {
      f.set(entity, new Double(value));
    } else {
      f.set(entity, new Double(value).doubleValue());
    }
  }

  @Override
  public boolean isMyType(Field f) {
    return isDouble(f);
  }

  /**
   * Gera um número entre os valores solicitados.
   *
   * @param min Número minimo aceitavel.
   * @param max Número máximo aceitavel.
   * @return Número aleatório.
   */
  public static Double getIntervalo(final double min, final double max) {
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
   * Retorna um número aleatório limitado ao max passado.
   *
   * @param max Minimo 1.
   * @return Double limitado ao max. Minimo é zero.
   */
  public static Double getMax(final double max) {
    if (max <= 0) {
      throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
    }

    return getIntervalo(0, max);
  }

  /**
   * Retorna True para tipos Double ou double.
   *
   * @param f Field a ser avaliado.
   * @return True para tipos Double ou double, False para outros tipos.
   */
  public static boolean isDouble(final Field f) {
    if (f.getType().equals(Double.class) || f.getType().equals(double.class)) {
      return true;
    }
    return false;
  }
}
