/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.factories.types.LogInfo;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeShortTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeShortTest");

    public MakeShortTest() {
        super("Maker :: Short");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
        logger.info("Short - GetIntervalo");
        for (short min = 32666; min < Short.MAX_VALUE; min++) {
            for (short max = (short) (min + 1); max < Short.MAX_VALUE; max++) {
                Short result = MakeShort.getIntervalo(min, max);
                logger.debug("Max: " + max
                        + " Min:" + min
                        + " Result:" + result);
                assertTrue("Intervalo incorreto: Max: " + max
                        + " Min:" + min + " Result: " + result,
                        result >= min && result <= max);
            }
        }

    }

    /**
     * Test of getMax method, of class MakeInteger.
     */
    @Test
    public void testGetMax() {
        logger.info("Short - GetMax");
        for (short i = 32666; i < Short.MAX_VALUE; i++) {
            Short result = MakeShort.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue("Intervalo incorreta", result <= i);
        }
        Short result2 = MakeShort.getMax(new Short("1"));
        logger.debug("Max: 1 Result: " + result2);
        assertTrue("Teste minimo incorreto", result2 <= 1);
    }
}
