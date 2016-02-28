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
   * Devolve um valor booleano, true ou false.
   *
   * @return True ou False.
   */
  public static Boolean getBoolean() {
    return MakeInteger.getMax(2) == 2;
  }

  /**
   * Retorna True para tipos Boolean ou boolean.
   *
   * @param field Field a ser avaliado.
   * @return True para tipos Boolean ou boolean, False para outros tipos.
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
