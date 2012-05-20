/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.wrappers;

import br.com.gbvbahia.maker.wrappers.MakeLong;
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
        logger.info("Maker :: Long - GetIntervalo");
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

    @Test
    public void testMinMaxIsReturned() throws Exception {
        logger.info("Maker :: Long - MinMaxIsReturned");
        int min = 1, max = 3;
        boolean minOk = false, maxOk = false;
        for (int i = 0; i < 100; i++) {
            if (!minOk && MakeLong.getIntervalo(min, max) == min) {
                logger.info("minOk setter true, interation: " + i);
                minOk = true;
            }
            if (!maxOk && MakeLong.getIntervalo(min, max) == max) {
                logger.info("maxOk setter true, interation: " + i);
                maxOk = true;
            }
            if (MakeLong.getIntervalo(min, max) > max
                    || MakeLong.getIntervalo(min, max) < min) {
                fail("Resultado não pode ser menor minimo ou maior que maximo!");
            }
        }
        assertTrue("Mínimo não foi atingido", minOk);
        assertTrue("Máximo não foi atingido", maxOk);
    }

    /**
     * Test of getMax method, of class MakeInteger.
     */
    @Test
    public void testGetMax() {
        logger.info("Maker :: Long - GetMax");
        for (int i = 1000000000; i <= 1000000100; i++) {
            Long result = MakeLong.getMax(i);
            logger.debug("Max: " + i + " Result: " + result);
            assertTrue("Intervalo incorreta", result <= i);
        }
        Long result2 = MakeLong.getMax(1l);
        logger.debug("Max: 1 Result: " + result2);
        assertTrue("Teste minimo incorreto", result2 <= 1);
    }
}
