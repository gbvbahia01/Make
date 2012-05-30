/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.log.LogInfo;
import org.apache.commons.logging.Log;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeBooleanTest {

    private static Log logger = LogInfo.getLog("Test :: MakeBooleanTest");

    public MakeBooleanTest() {
    }

    /**
     * Test of getBoolean method, of class MakeBoolean.
     */
    @Test
    public void testGetBoolean() {
        logger.info("Boolean - GetBoolean");
        boolean trueCheck = false, falseCheck = false;
        for (int i = 0; i < 100; i++) {
            Boolean b = MakeBoolean.getBoolean();
            logger.debug("Result Boolean: " + b);
            assertNotNull("Boolean nulo!", b);
            if (b) {
                trueCheck = true;
            }
            if (!b) {
                falseCheck = true;
            }
            if (trueCheck && falseCheck) {
                break;
            }
        }
        assertTrue("True ou False não retornado",
                trueCheck && falseCheck);
    }
}
