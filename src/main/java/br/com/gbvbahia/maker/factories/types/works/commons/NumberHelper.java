package br.com.gbvbahia.maker.factories.types.works.commons;

import br.com.gbvbahia.maker.types.complex.MakeBigDecimal;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeLong;

import org.apache.commons.lang3.StringUtils;

import java.math.RoundingMode;

/**
 * Realiza o trabalho de manipular String passadas para o tipo correto.
 *
 * @since v.1 17/06/2012
 * @author Guilherme
 */
public class NumberHelper {

  /**
   * Valor mínimo aceito.
   */
  private String min;
  /**
   * Valor máximo aceito.
   */
  private String max;
  /**
   * Valor a ser devolvido.
   */
  private String value;

  /**
   * Valor calculado de min e max.
   *
   * @return Um valor entre min e max, incluídos.
   */
  public String getValue() {
    if (StringUtils.contains(this.min, ".") || StringUtils.contains(this.max, ".")) {
      int decimal = StringUtils.substringAfter(this.min, ".").length();
      this.value =
          MakeBigDecimal
              .getRange(new Double(this.min).doubleValue(), new Double(this.max).doubleValue())
              .setScale(decimal, RoundingMode.UP).toString();
    } else {
      this.value =
          MakeLong.getRange(new Long(this.min).longValue(), new Long(this.max).longValue())
              .toString();
    }
    return this.value;
  }

  /**
   * Construtor obrigatório para criação do número no intervalo.
   *
   * @param min Mínimo aceitavel.
   * @param max Máximo aceitavel.
   */
  public NumberHelper(String min, String max) {
    this.min = min;
    this.max = max;
  }

  public String getMax() {
    return this.max;
  }

  public String getMin() {
    return this.min;
  }

  @Override
  public String toString() {
    return "min=" + this.min + ", max=" + this.max;
  }

}
