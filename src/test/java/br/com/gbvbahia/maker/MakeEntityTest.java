/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.entityes.EntityTest;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Guilherme
 */
public class MakeEntityTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeEntityTest");

    public MakeEntityTest() {
        super("Make Entity");
    }

    public void testMakeEntity() throws Exception {
        for (int i = 0; i < 100; i++) {
            EntityTest test = MakeEntity.makeEntity(EntityTest.class);
            logger.info(test);
            assertNotNull("Test é nulo.", test);
            assertNull("Nulo não nulo", test.getInteiro());
            assertTrue("Valor Integer inesperado",
                    test.getInteiroObjeto() >= 3);
            assertTrue("Valor Integer inesperado",
                    test.getInteiroObjeto() <= 4);
            assertTrue("Valor int inesperado",
                    test.getPrimitivoInt() >= 0);
            assertTrue("Valor int inesperado",
                    test.getPrimitivoInt() <= 100);
            assertNotNull("Valor Long inesperado",
                    test.getLongObjeto());
            assertTrue("Valor Long inesperado",
                    test.getLongObjeto() > 0);
            assertFalse("Valor long inesperado",
                    test.getPrimitivoLong() < 1000000000);
            assertFalse("Valor long inesperado",
                    test.getPrimitivoLong() > 2000000000);
            assertTrue("Valor Byte inesperado",
                    test.getByteObjeto() >= 50
                    && test.getByteObjeto() <= 126);
            assertTrue("Valor byte inesperado",
                    test.getPrimitivoByte() >= 11
                    && test.getPrimitivoByte() <= 22);
            assertTrue("Valor Short inesperado",
                    test.getShortObjeto() >= 30000
                    && test.getShortObjeto() <= 32000);
            assertTrue("Valor short inesperado",
                    test.getPrimitivoShort() >= 32000
                    && test.getPrimitivoShort() <= 32050);
        }
    }
}
