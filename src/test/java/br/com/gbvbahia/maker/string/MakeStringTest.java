/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.string;

import br.com.gbvbahia.maker.string.MakeString;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.*;
import static org.junit.Assert.*;

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
        for (int min = 1; min < 100; min++) {
            for (int max = min + 1; max < 200; max++) {
                String result = MakeString.getString(min, max);
                assertTrue("String erro: Max: " + max
                        + " Min:" + min + " Result: " + result.length(),
                        result.length() >= min
                        && result.length() <= max);
                logger.debug("String - Max: " + max
                        + " Min:" + min
                        + " Result:" + result.length());

            }
        }
    }

    /**
     * Test of gerarSenha method, of class MakeString.
     */
    @Test
    public void testGerarSenha_int_int() {
        logger.info("Maker :: String - GerarSenha_int_int");
        for (int min = 1; min < 100; min++) {
            for (int max = min + 1; max < 200; max++) {
                String result = MakeString.gerarSenha(min, max);
                assertTrue("Senha erro: Max: " + max
                        + " Min:" + min + " Result: " + result.length(),
                        result.length() >= min
                        && result.length() <= max);
                logger.debug("Senha - Max: " + max
                        + " Min:" + min
                        + " Result:" + result.length());
            }
        }
    }

    /**
     * Test of getString method, of class MakeString.
     */
    @Test
    public void testGetString_int() {
        logger.info("Maker :: String - GetString_int");
        for (int i = 1; i < 100; i++) {
            String result = MakeString.getString(i);
            assertTrue(result.length() <= i);
            assertTrue("String erro: Resultado" + result.length()
                    + " Esperado: " + i,
                    result.length() <= i);
            logger.debug("String - Max: " + i
                    + " Result: " + result.length());
        }
    }

    /**
     * Test of gerarSenha method, of class MakeString.
     */
    @Test
    public void testGerarSenha_int() {
        logger.info("Maker :: String - GerarSenha_int");
        for (int i = 1; i < 100; i++) {
            String result = MakeString.gerarSenha(i);
            assertTrue(result.length() <= i);
            assertTrue("Senha erro: Resultado" + result.length()
                    + " Esperado: " + i, result.length() <= i);
            logger.debug("Senha - Max: " + i
                    + " Result: " + result.length());
        }
    }

    @Test
    public void testGerarEmail() {
        logger.info("Maker :: String - GerarEmail");
        for (int i = 0; i < 100; i++) {
            String email = MakeString.gerarEmail();
            String inicio = StringUtils.substringBefore(email, "@");
            String fim = StringUtils.substringAfter(email, "@");
            assertNotNull("Email Inicio nulo", inicio);
            assertNotNull("Email Fim nulo", fim);
            assertTrue("Email inválido: " + email,
                    EmailValidator.getInstance().isValid(email));
        }
    }
}
