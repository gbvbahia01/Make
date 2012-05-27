/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.string;

import br.com.gbvbahia.maker.factories.types.LogInfo;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeCharacterTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeCharacterTest");

    public MakeCharacterTest() {
        super("Maker :: Character");
    }

    @Test
    public void testGetNumber() throws Exception {
        logger.info("Character - GetNumber");
        for (int i = 0; i < 1000; i++) {
            Character test = MakeCharacter.getNumber();
            logger.debug("MakeCharacter.getNumber retornou: " + test);
            assertTrue("Erro ao converter " + test
                    + " MakeCharacter.getNumber em um número Inteiro.",
                    checkNumber(test));
        }

    }

    private boolean checkNumber(Character test) {
        try {
            new Integer(test.toString());
        } catch (NumberFormatException n) {
            return false;
        }
        return true;

    }

    @Test
    public void testGetLetter() throws Exception {
        logger.info("Character - GetLetter");
        for (int i = 0; i < 1000; i++) {
            Character test = MakeCharacter.getLetter();
            logger.debug("MakeCharacter.getLetter retornou: " + test);
            assertTrue("O caracter não é uma letra: " + test,
                    checkLetter(test));
        }
    }

    private boolean checkLetter(Character test) {
        boolean toReturn = Character.isLetter(test);
        return toReturn;
    }

    @Test
    public void testGetSymbols() throws Exception {
        logger.info("Character - GetSymbols");
        List<Character> character = Arrays.asList(MakeCharacter.SYMBOLS);
        for (int i = 0; i < 100; i++) {
            Character test = MakeCharacter.getSymbols();
            logger.debug("MakeCharacter.getSymbols retornou: " + test);
            assertTrue("O caracter não é um simbolo: " + test,
                    checkSymbols(test, character));
        }
    }

    private boolean checkSymbols(Character test, List<Character> character) {
        boolean toReturn = character.contains(test);
        return toReturn;
    }

    @Test
    public void testGetCharacter() throws Exception {
        logger.info("Character - GetCharacter");
        List<Character> character = Arrays.asList(MakeCharacter.SYMBOLS);
        for (int i = 0; i < 1000; i++) {
            Character test = MakeCharacter.getCharacter();
            logger.debug("MakeCharacter.getCharacter retornou: " + test);
            if (!checkLetter(test)
                    && !checkNumber(test)
                    && !checkSymbols(test, character)) {
                fail("Character " + test + " é inválido!");
            }
        }
        assertTrue("Todas as verificações passaram!", true);
    }
}
