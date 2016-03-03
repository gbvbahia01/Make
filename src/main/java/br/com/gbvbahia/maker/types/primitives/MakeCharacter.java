package br.com.gbvbahia.maker.types.primitives;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import org.apache.commons.logging.Log;

import java.lang.reflect.Field;

/**
 * @since v.1 06/2012
 * @author Guilherme
 */
public final class MakeCharacter {

  /**
   * Logger.
   */
  private static Log logger = LogInfo.getLog("MakeCharacter");
  /**
   * Only numbers.
   */
  private static final char[] NUMBERS = new char[10];
  /**
   * Only letters.
   */
  private static final char[] LETTERS = new char[26];
  /**
   * The symbols used to return.
   */
  public static final Character[] SYMBOLS = {'!', '@', '#', '$', '&', '%', '?', '-', '+'};

  /**
   * Puts letters and numbers.
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
   * A character between 0 and 9.
   *
   * @return 0-9.
   */
  public static Character getNumber() {
    char toReturn = NUMBERS[MakeInteger.getRange(0, NUMBERS.length - 1)];
    logger.debug(I18N.getMsg("returnValue", "MakeCharacter.geNumber", toReturn));
    return toReturn;
  }

  /**
   * A character between a and Z.
   *
   * @return a-Z.
   */
  public static Character getLetter() {
    char toReturn = LETTERS[MakeInteger.getRange(0, LETTERS.length - 1)];
    logger.debug(I18N.getMsg("returnValue", "MakeCharacter.geLetter", toReturn));
    return toReturn;
  }

  /**
   * A character in !, @, #, $, &, %, ?, -, +.
   *
   * @return !, @, #, $, &, %, ?, -, +
   */
  public static Character getSymbols() {
    Character toReturn = SYMBOLS[MakeInteger.getRange(0, SYMBOLS.length - 1)];
    logger.debug(I18N.getMsg("returnValue", "MakeCharacter.geSymbols", toReturn));
    return toReturn;
  }

  /**
   * A letter, number or symbol.
   * 
   * @return A letter, number or symbol.
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
   * True if the field type is a Character false if not.
   * 
   * @param field to be evaluated.
   * @return True if the field type is a Character false if not.
   */
  public static boolean isCharacter(Field field) {
    if (field.getType().equals(Character.class) || field.getType().equals(char.class)) {
      return true;
    }
    return false;
  }

  /**
   * Cannot be instantiated.
   */
  private MakeCharacter() {}
}
