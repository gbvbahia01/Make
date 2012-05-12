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
public class MakeByteTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeByteTest");
    
    public MakeByteTest() {
        super("Maker :: Byte");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
        for (byte min = 1; min < Byte.MAX_VALUE - 100; min++) {
            for (byte max = (byte) (min + 1); max < Byte.MAX_VALUE; max++) {
                Byte result = MakeByte.getIntervalo(min, max);
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
        for (byte i = 1; i < Byte.MAX_VALUE; i++) {
            Byte result = MakeByte.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue(result <= i);
            assertTrue("Intervalo incorreta", result < i);
        }

    }
}
