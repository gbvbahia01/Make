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
public class MakeIntegerTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeIntegerTest");
    
    public MakeIntegerTest() {
        super("Maker :: Integer");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
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
        for (int i = 1; i < 100; i++) {
            Integer result = MakeInteger.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue(result <= i);
            assertTrue("Intervalo incorreta", result < i);
        }

    }
}
