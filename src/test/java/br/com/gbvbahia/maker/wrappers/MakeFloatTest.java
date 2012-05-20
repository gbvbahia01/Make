/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.wrappers;

import br.com.gbvbahia.maker.wrappers.MakeDouble;
import br.com.gbvbahia.maker.wrappers.MakeFloat;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeFloatTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeFloatTest");

    public MakeFloatTest() {
        super("Maker :: Float");
    }
    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
        logger.info("Maker :: Float - GetIntervalo");
        for (float min = -10.1f; min < 0.1f; min++) {
            for (float max = min + 0.1f; max < 0.1f; max += 0.1f) {
                Double result = MakeDouble.getIntervalo(min, max);
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
        logger.info("Maker :: Float - GetMax");
        for (float i = 1.11111f; i <= 20.22f; i += 0.1f) {
            Float result = MakeFloat.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue("Intervalo incorreta", result <= i);
        }
        Float result2 = MakeFloat.getMax(1l);
        logger.debug("Max: 1 Result: " + result2);
        assertTrue("Teste minimo incorreto", result2 <= 1);
    }
}
