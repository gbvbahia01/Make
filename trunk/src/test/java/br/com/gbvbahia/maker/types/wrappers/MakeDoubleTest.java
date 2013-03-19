/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeDoubleTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeDoubleTest");

    public MakeDoubleTest() {
        super("Maker :: Double");
    }

    /**
     * Test of getIntervalo method, of class MakeInteger.
     */
    @Test
    public void testGetIntervalo() {
        logger.info("Double - GetIntervalo");
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
        logger.info("Double - GetMax");
        for (double i = 1000000000.11111; i <= 1000000020.22; i += 0.1) {
            Double result = MakeDouble.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue("Intervalo incorreta", result <= i);
        }
        Double result2 = MakeDouble.getMax(1l);
        logger.debug("Max: 1 Result: " + result2);
        assertTrue("Teste minimo incorreto", result2 <= 1);
    }

    @Test
    public void testIntervalo() {
        logger.info("Double - Intervalo");
        for (int i = 0; i < 200; i++) {
            final Double intervalo = MakeDouble.getIntervalo(0.0001, 0.0002);
            System.out.println(intervalo);
            assertFalse("Teste minimo incorreto", intervalo < 0.0001);
            assertFalse("Teste maximo incorreto", intervalo > 0.0002);
        }
    }
}
