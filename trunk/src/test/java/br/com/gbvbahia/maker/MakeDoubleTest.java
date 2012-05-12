/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeDoubleTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeDoubleTest");

    public MakeDoubleTest() {
        super("Maker :: Double");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
        for (double min = 10.1; min < 20.1; min++) {
            for (double max = min + 0.1; max < 20.1; max += 0.1) {
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
        for (double i = 1000000000.11111; i <= 1000000020.22; i += 0.1) {
            Double result = MakeDouble.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue("Intervalo incorreta", result <= i);
        }
        Double result2 = MakeDouble.getMax(1l);
        logger.debug("Max: 1 Result: " + result2);
        assertTrue("Teste minimo incorreto", result2 <= 1);
    }
}
