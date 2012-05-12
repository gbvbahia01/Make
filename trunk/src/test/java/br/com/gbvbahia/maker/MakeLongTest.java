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
public class MakeLongTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeLongTest");

    public MakeLongTest() {
        super("Maker :: Long");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
        for (long min = 1000000000; min < 1000000100; min++) {
            for (long max = min + 1; max < 1000000200; max++) {
                Long result = MakeLong.getIntervalo(min, max);
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
        for (int i = 1000000000; i <= 1000000100; i++) {
            Long result = MakeLong.getMax(i);
            logger.info("Max: " + i + " Result: " + result);
            assertTrue("Intervalo incorreta", result <= i);
        }
        Long result2 = MakeLong.getMax(1l);
        logger.info("Max: 1 Result: " + result2);
        assertTrue("Teste minimo incorreto", result2 <= 1);
    }
}
