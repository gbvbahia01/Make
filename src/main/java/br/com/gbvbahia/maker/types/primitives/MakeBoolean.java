package br.com.gbvbahia.maker.types.primitives;

import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import java.lang.reflect.Field;

/**
 * Cria um valor booleano aleatório.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public final class MakeBoolean {

  /**
   * Can return true or false.
   *
   * @return True or False.
   */
  public static Boolean getBoolean() {
    return MakeInteger.getMax(2) == 2;
  }

  /**
   * True if the field type is a boolean false if is not.
   *
   * @param field to be evaluated.
   * @return True if the field type is a boolean false if is not.
   */
  public static boolean isBoolean(Field field) {
    if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
      return true;
    }
    return false;
  }

  /**
   * Não pode ser instânciado.
   */
  private MakeBoolean() {}
}
