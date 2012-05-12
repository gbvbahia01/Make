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
public class MakeShortTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeShortTest");
    
    public MakeShortTest() {
        super("Maker :: Short");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
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
        for (short i = 32666; i < Short.MAX_VALUE; i++) {
            Short result = MakeShort.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue(result <= i);
            assertTrue("Intervalo incorreta", result < i);
        }

    }
}
