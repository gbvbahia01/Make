/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.string;

import br.com.gbvbahia.maker.wrappers.MakeInteger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Guilherme
 */
public class MakeCharacter {

    /**
     * Logger para depuração.
     */
    private static Log logger = LogFactory.getLog("MakeCharacter");
    /**
     * Somente numeros.
     */
    private static final char[] NUMBERS = new char[10];
    /**
     * Somente letras.
     */
    private static final char[] LETTERS = new char[26];
    /**
     * Simbolos. Esse array pode variar de uma versão para outra, por
     * isso é public, facilitando codificação para o mesmo.
     */
    public static final Character[] SYMBOLS = {'!', '@', '#', '$', '&',
        '%', '?', '-', '+'};

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
        char toReturn = NUMBERS[MakeInteger.getMax(NUMBERS.length) - 1];
        logger.debug("MakeCharacter.geNumber retornou: " + toReturn);
        return toReturn;
    }

    /**
     * Uma letra a-z.
     *
     * @return a-z.
     */
    public static Character getLetter() {
        char toReturn = LETTERS[MakeInteger.getMax(LETTERS.length) - 1];
        logger.debug("MakeCharacter.geLetter retornou: " + toReturn);
        return toReturn;
    }

    /**
     * Um simbolo !, @, #, $, &, %, ?, -, +.
     *
     * @return !, @, #, $, &, %, ?, -, +
     */
    public static Character getSymbols() {
        Character toReturn = SYMBOLS[MakeInteger.getMax(SYMBOLS.length) - 1];
        logger.debug("MakeCharacter.geSymbols retornou: " + toReturn);
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
}
