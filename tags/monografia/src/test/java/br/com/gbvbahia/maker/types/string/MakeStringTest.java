/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.string;

import br.com.gbvbahia.maker.types.primitives.MakeCharacter;
import br.com.gbvbahia.maker.types.complex.MakeString;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.MakeEmail;
import br.com.gbvbahia.maker.works.MakePassword;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeStringTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeStringTest");

    public MakeStringTest() {
        super("Maker :: String");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringLenghtSuport() throws Exception {
        logger.info("String - StringLenghtSuport");
        try {
            MakeString.getString(MakeString.MAX_LENGTH_SUPPORTS + 1,
                    MakeString.StringType.ALL);
            fail("Uma IllegalArgumentException era esperada!");
        } catch (IllegalArgumentException ie) {
            assertTrue("Exceção lançada!", true);
        }
        try {
            MakePassword.getPassword(MakePassword.MAX_LENGTH_SUPPORTS + 1);
            fail("Uma IllegalArgumentException era esperada!");
        } catch (IllegalArgumentException ie) {
            assertTrue("Exceção lançada!", true);
        }
    }

    /**
     * Test of getString method, of class MakeString.
     */
    @Test
    public void testGetString_int_int() {
        logger.info("String - GetString_int_int");
        for (int min = 1; min < 50; min++) {
            for (int max = min + 1; max < 100; max++) {
                String result = MakeString.getString(min, max,
                        MakeString.StringType.ALL);
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
        logger.info("String - GerarSenha_int_int");
        for (int min = 1; min < 50; min++) {
            for (int max = min + 1; max <= MakePassword.MAX_LENGTH_SUPPORTS; max++) {
                String result = MakePassword.getPassword(min, max);
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
        logger.info("String - GetString_int_all");
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
        logger.info("String - GetString_int_letter");
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
        }
        String result = MakeString.getLoren(-50);
        logger.debug("String - Max: " + -50
                    + " Lenght: " + result.length()
                    + " Result: " + result);
        result = MakeString.getLoren(5000);
        logger.debug("String - Max: " + 5000
                    + " Lenght: " + result.length()
                    + " Result: " + result);
        
    }

    @Test
    public void testGetString_int_number() {
        logger.info("String - GetString_int_number");
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
        logger.info("String - GetString_int_symbols");
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
        logger.info("String - GerarSenha_int");
        for (int i = 1; i < MakePassword.MAX_LENGTH_SUPPORTS; i++) {
            String result = MakePassword.getPassword(i);
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
        logger.info("String - GerarEmail");
        for (int i = 0; i < 100; i++) {
            String email = MakeEmail.getEmail();
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
