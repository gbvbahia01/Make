/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.primitives;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

/**
 *
 * @author Guilherme
 */
public final class MakeCharacter {

  /**
   * Logger para depuração.
   */
  private static Log logger = LogInfo.getLog("MakeCharacter");
  /**
   * Somente numeros.
   */
  private static final char[] NUMBERS = new char[10];
  /**
   * Somente letras.
   */
  private static final char[] LETTERS = new char[26];
  /**
   * Simbolos. Esse array pode variar de uma versão para outra, por isso é public, facilitando
   * codificação para o mesmo.
   */
  public static final Character[] SYMBOLS = {'!', '@', '#', '$', '&', '%', '?', '-', '+'};

  /**
   * Populas letras e numeros.
   */
  static {
    for (int idx = 0; idx < 10; ++idx) {
      NUMBERS[idx] = (char) ('0' + idx);
    }
    for (int idx = 0; idx < 26; ++idx) {
      LETTERS[idx] = (char) ('a' + idx);
    }
  }

  /**
   * Um character entre 0-9.
   *
   * @return 0-9.
   */
  public static Character getNumber() {
    char toReturn = NUMBERS[MakeInteger.getIntervalo(0, NUMBERS.length - 1)];
    logger.debug(I18N.getMsg("returnValue", "MakeCharacter.geNumber", toReturn));
    return toReturn;
  }

  /**
   * Uma letra a-z.
   *
   * @return a-z.
   */
  public static Character getLetter() {
    char toReturn = LETTERS[MakeInteger.getIntervalo(0, LETTERS.length - 1)];
    logger.debug(I18N.getMsg("returnValue", "MakeCharacter.geLetter", toReturn));
    return toReturn;
  }

  /**
   * Um simbolo !, @, #, $, &, %, ?, -, +.
   *
   * @return !, @, #, $, &, %, ?, -, +
   */
  public static Character getSymbols() {
    Character toReturn = SYMBOLS[MakeInteger.getIntervalo(0, SYMBOLS.length - 1)];
    logger.debug(I18N.getMsg("returnValue", "MakeCharacter.geSymbols", toReturn));
    return toReturn;
  }

  /**
   * Uma letra ou um numero ou um simbolo.
   *
   * @return Qualquer Character de Number, Symbols ou Letter
   */
  public static Character getCharacter() {
    switch (MakeInteger.getMax(5)) {
      case 1:
        return getNumber();
      case 2:
        return getSymbols();
      default:
        return getLetter();
    }
  }

  /**
   * Retorna True para tipos Character ou char.
   *
   * @param f Field a ser avaliado.
   * @return True para tipos Character ou char, False para outros tipos.
   */
  public static boolean isCharacter(Field f) {
    if (f.getType().equals(Character.class) || f.getType().equals(char.class)) {
      return true;
    }
    return false;
  }

  /**
   * Não pode ser instânciado.
   */
  private MakeCharacter() {}
}
