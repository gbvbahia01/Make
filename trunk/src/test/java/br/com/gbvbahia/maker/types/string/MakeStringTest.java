/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.string;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeStringTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeStringTest");

    public MakeStringTest() {
        super("Maker :: String");
    }

    /**
     * Test of getString method, of class MakeString.
     */
    @Test
    public void testGetString_int_int() {
        logger.info("Maker :: String - GetString_int_int");
        for (int min = 1; min < 50; min++) {
            for (int max = min + 1; max < 100; max++) {
                String result = MakeString.getString(min, max);
                assertTrue("String erro: Max: " + max
                        + " Min:" + min + " Result: " + result.length(),
                        result.length() >= min
                        && result.length() <= max);
                logger.debug("String - Max: " + max
                        + " Min:" + min
                        + " length: " + result.length()
                        + " Result:" + result);
            }
        }
    }

    /**
     * Test of getPassword method, of class MakeString.
     */
    @Test
    public void testGerarSenha_int_int() {
        logger.info("Maker :: String - GerarSenha_int_int");
        for (int min = 1; min < 50; min++) {
            for (int max = min + 1; max < 100; max++) {
                String result = MakeString.getPassword(min, max);
                assertTrue("Senha erro: Max: " + max
                        + " Min:" + min + " Result: " + result.length(),
                        result.length() >= min
                        && result.length() <= max);
                logger.debug("Senha - Max: " + max
                        + " Min:" + min
                        + " length: " + result.length()
                        + " Result:" + result);
            }
        }
    }

    /**
     * Test of getString method, of class MakeString.
     */
    @Test
    public void testGetString_int_all() {
        logger.info("Maker :: String - GetString_int_all");
        for (int i = 1; i < 100; i++) {
            String result = MakeString.getString(i,
                    MakeString.StringType.ALL);
            assertTrue(result.length() <= i);
            assertTrue("String erro: Resultado" + result.length()
                    + " Esperado: " + i,
                    result.length() <= i);
            logger.debug("String - Max: " + i
                    + " Lenght: " + result.length()
                    + " Result: " + result);
        }
    }

    @Test
    public void testGetString_int_letter() {
        logger.info("Maker :: String - GetString_int_letter");
        for (int i = 1; i < 100; i++) {
            String result = MakeString.getString(i,
                    MakeString.StringType.LETTER);
            assertTrue(result.length() <= i);
            assertTrue("String erro: Resultado" + result.length()
                    + " Esperado: " + i,
                    result.length() <= i);
            logger.debug("String - Max: " + i
                    + " Lenght: " + result.length()
                    + " Result: " + result);
            for (char c : result.toCharArray()) {
                assertTrue("String contém um caracter inválido",
                        Character.isLetter(c) || Character.isSpaceChar(c));
            }
        }
    }

    @Test
    public void testGetString_int_number() {
        logger.info("Maker :: String - GetString_int_number");
        for (int i = 1; i < 100; i++) {
            String result = MakeString.getString(i,
                    MakeString.StringType.NUMBER);
            assertTrue(result.length() <= i);
            assertTrue("String erro: Resultado" + result.length()
                    + " Esperado: " + i,
                    result.length() <= i);
            logger.debug("String - Max: " + i
                    + " Lenght: " + result.length()
                    + " Result: " + result);
            for (char c : result.toCharArray()) {
                assertTrue("String contém um caracter numérico inválido",
                        Character.isDigit(c));
            }
        }
    }

    @Test
    public void testGetString_int_symbols() throws Exception {
        logger.info("Maker :: String - GetString_int_symbols");
        List<Character> character = Arrays.asList(MakeCharacter.SYMBOLS);
        for (int i = 1; i < 100; i++) {
            String result = MakeString.getString(i, MakeString.StringType.SYMBOL);
            logger.debug("String - Max: " + i
                    + " Lenght: " + result.length()
                    + " Result: " + result);
            for (char c : result.toCharArray()) {
                character.contains(c);
            }
        }
    }

    /**
     * Test of getPassword method, of class MakeString.
     */
    @Test
    public void testGerarSenha_int() {
        logger.info("Maker :: String - GerarSenha_int");
        for (int i = 1; i < 100; i++) {
            String result = MakeString.getPassword(i);
            assertTrue(result.length() <= i);
            assertTrue("Senha erro: Resultado" + result.length()
                    + " Esperado: " + i, result.length() <= i);
            logger.debug("Senha - Max: " + i
                    + " length: " + result.length()
                    + " Result:" + result);
        }
    }

    @Test
    public void testGerarEmail() {
        logger.info("Maker :: String - GerarEmail");
        for (int i = 0; i < 100; i++) {
            String email = MakeString.getEmail();
            String inicio = StringUtils.substringBefore(email, "@");
            String fim = StringUtils.substringAfter(email, "@");
            logger.debug("Email - Inicio: " + inicio
                    + " Fim: " + fim
                    + " length: " + email.length()
                    + " Result:" + email);
            assertNotNull("Email Inicio nulo", inicio);
            assertNotNull("Email Fim nulo", fim);
            assertTrue("Email inválido: " + email,
                    EmailValidator.getInstance().isValid(email));
        }
    }
}