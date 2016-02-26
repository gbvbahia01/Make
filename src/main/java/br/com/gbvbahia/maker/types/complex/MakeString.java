/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */

package br.com.gbvbahia.maker.types.complex;

import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.MakeCharacter;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

/**
 * Build a String determined by enum String type.
 *
 * @since 21/04/2012
 * @author Guilherme Braga
 */
public final class MakeString {

  /**
   * Use to determine the type of characters that will be returned.<br>
   * ALL: all types of characters can be returned.<br>
   * LETTER: only characters between a-zA-Z space is included.<br>
   * NUMBER: only numbers.<br>
   * SYMBOL: any character inside of MakeCharacter.SYMBOLS<br>
   */
  public enum StringType {
    LETTER, NUMBER, SYMBOL, ALL
  };

  /**
   * Means the amount of lines in loren.properties file.
   */
  private static final int MAX_PROPERTIES_LOREN = 206;
  /**
   * Means then amount of characters by line in loren.properties file.
   * 
   */
  private static final int CARACTERES_LINE_LOREN = 100;
  /**
   * Default maximum amount of lines can be fetched in loren.properties.
   */
  private static final int MAX_CARACTERES_LOREN = 20;
  /**
   * Default minimum amount of lines can be fetched in loren.properties.
   */
  private static final int MIN_CARACTERES_LOREN = 1;
  /**
   * Default maximum of characters when developer did not set.
   */
  public static final int MAX_LENGTH_DEFAULT = 50;
  /**
   * Default minimum of characters when developer did not set.
   */
  public static final int MIN_LENGTH_DEFAULT = 1;
  /**
   * Maximum amount of characters made by make. If a value is bigger than it a
   * IllergalArgumentException will be launched.
   */
  public static final int MAX_LENGTH_SUPPORTS = 4000;

  /**
   * It makes a String with amount of characters between min and max parameters. Use type parameter
   * to control the types of character inside of the String.
   * 
   * @param max Maximum of characters.
   * @param min Minimum of characters.
   * @param type can be any StringType value.
   * @return A String to be used.
   * @throws IllegalArgumentException if max is bigger than MakeString.MAX_LENGTH_SUPPORTS constant.
   */
  public static String getString(final int min, final int max, StringType type) {
    if (min < 0) {
      throw new IllegalArgumentException(I18N.getMsg("caractereToStringErro", new Integer(min)));
    }
    int numero = MakeInteger.getIntervalo(min, max);
    return getString(numero, type);
  }

  /**
   * It makes a String with a size informed in parameter characters. Use type parameter to control
   * the types of character inside of the String.
   * 
   * @param characters amount of it.
   * @param type can be any StringType value.
   * @return A String to be used.
   * @throws IllegalArgumentException if characters is bigger than MakeString.MAX_LENGTH_SUPPORTS
   *         constant.
   */
  public static String getString(final int characters, final StringType type) {
    if (isEmptyCaracters(characters)) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    if (StringType.LETTER.equals(type)) {
      return getLoren(characters);
    }
    for (int i = 0; i < characters; i++) {
      switch (type) {
        case NUMBER:
          sb.append(MakeCharacter.getNumber());
          break;
        case SYMBOL:
          sb.append(MakeCharacter.getSymbols());
          break;
        default:
          all(sb);
      }
    }
    return sb.toString();
  }

  /**
   * Add any type of character in a stringBuilder parameter.
   *
   * @param stringBuilder to be put a new character.
   */
  private static void all(final StringBuilder stringBuilder) {
    switch (MakeInteger.getMax(5)) {
      case 3:
        stringBuilder.append(MakeCharacter.getNumber());
        break;
      case 1:
        stringBuilder.append(MakeCharacter.getSymbols());
        break;
      default:
        letter(stringBuilder);
    }
  }

  /**
   * Add letter type of character in a stringBuilder parameter.<br>
   * Space character can be put.
   *
   * @param stringBuilder to be put a new character.
   */
  private static void letter(final StringBuilder stringBuilder) {
    switch (MakeInteger.getMax(5)) {
      case 3:
        stringBuilder.append(MakeCharacter.getLetter().toString().toUpperCase());
        break;
      case 1:
        stringBuilder.append(" ");
        break;
      default:
        stringBuilder.append(MakeCharacter.getLetter().toString());
    }
  }

  /**
   * Check the amount of characters informed result in a empty String.
   * 
   * @param characters amount of.
   * @return true is a empty String and false is a String with characters.
   * @throws IllegalArgumentException if the amount is less than zero or bigger than bigger than
   *         MakeString.MAX_LENGTH_SUPPORTS constant.
   */
  private static boolean isEmptyCaracters(final int characters) throws IllegalArgumentException {
    if (characters > MAX_LENGTH_SUPPORTS) {
      throw new IllegalArgumentException(I18N.getMsg("sizeLenghFatal", MAX_LENGTH_SUPPORTS));
    } else if (characters < 0) {
      throw new IllegalArgumentException(I18N.getMsg("caractereToStringErro", new Integer(
          characters)));
    } else if (characters == 0) {
      return true;
    }
    return false;
  }

  /**
   * It fetches a line from loren_make.properties file.
   * 
   * @param position that the line must be fetched.
   * @return A line in position informed.
   * @throws IllegalAccessException If a resource problem occurs.
   */
  private static String getMsg(final int position) {
    try {
      return ResourceBundle.getBundle("loren_make").getString("loren" + position);
    } catch (Exception exeption) {
      throw new IllegalArgumentException(I18N.getMsg("loren_make_ResourceError", new Integer(
          position), exeption));
    }
  }

  /**
   * It makes a String using the loren_make.properties file. Each 100 characters a new line is
   * fetched and a line inserted before can be inserted again.
   *
   * @param characters Amount of characters. If equal zero will be used the constant
   *        MakeString.MIN_CARACTERES_LOREN.
   * @return A String made from loren_make.properties file.
   * @throws IllegalAccessException If a resource problem occurs.
   */
  public static String getLoren(final int characters) {
    int linhas = setLines(characters);
    StringBuilder sb = new StringBuilder();
    for (; linhas > 0; linhas--) {
      sb.append(getMsg(MakeInteger.getMax(MAX_PROPERTIES_LOREN)));
    }
    return StringUtils.substring(sb.toString(), 0, characters);
  }

  /**
   * Defines the amount of lines is needed from loren_make.properties for maxCaracteres parameter.
   * 
   * @param maxCaracteres is the maximum characters needed.
   * @return Amount of lines is necessary to create a String with the amount maxCaracteres.
   */
  private static int setLines(final int maxCaracteres) {
    int linhas;
    if (maxCaracteres <= 0) {
      linhas = MIN_CARACTERES_LOREN;
      LogInfo.logDebugInformation("MakeString",
          I18N.getMsg("minLorenError", maxCaracteres, MIN_CARACTERES_LOREN));
    } else if (maxCaracteres > MAX_LENGTH_SUPPORTS) {
      linhas = MAX_CARACTERES_LOREN;
      LogInfo
          .logWarnInformation(
              "MakeString",
              I18N.getMsg("minLorenError", maxCaracteres, MAX_CARACTERES_LOREN
                  * CARACTERES_LINE_LOREN));
    } else {
      linhas = maxCaracteres / CARACTERES_LINE_LOREN;
      if (linhas == 0) {
        linhas = 1;
      }
    }
    return linhas;
  }

  /**
   * Cannot be instantiated.
   */
  private MakeString() {}
}
