/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeIntegerTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeIntegerTest");

    public MakeIntegerTest() {
        super("Maker :: Integer");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
        logger.info("Integer - GetIntervalo");
        for (int min = 1; min < 100; min++) {
            for (int max = min + 1; max < 200; max++) {
                Integer result = MakeInteger.getIntervalo(min, max);
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
        logger.info("Integer - GetMax");
        for (int i = 1; i < 100; i++) {
            Integer result = MakeInteger.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue("Intervalo incorreta", result <= i);
        }
        Integer result2 = MakeInteger.getMax(1);
        logger.debug("Max: 1 Result: " + result2);
        assertTrue("Teste minimo incorreto", result2 <= 1);
    }
}
