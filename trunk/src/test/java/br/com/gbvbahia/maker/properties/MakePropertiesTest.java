/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.entityes.EntityPropertiesTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.log.LogInfo;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakePropertiesTest extends TestCase {
    
    private static Log logger = LogInfo.getLog("Test :: MakePropertiesTest");
    
    public MakePropertiesTest() {
        super("Maker :: Properties");
    }


    /**
     * Test of factoryProperties method, of class MakeProperties.
     */
    @Test 
    public void testFactoryProperties() {
        logger.info("FactoryProperties");
        EntityPropertiesTest test = MakeEntity.makeEntity("test1",
                EntityPropertiesTest.class, true);
        assertTrue("Sem erros", true);
        assertNotNull("EntityPropertiesTest não pode ser nula", test);
        assertNotNull("EntityPropertiesTest.cpf não pode ser nulo",
                test.getCpf());
    }
}
