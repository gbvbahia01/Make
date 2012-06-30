/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.maker.factories.types.properties.MakeWorksFactory;
import br.com.gbvbahia.entityes.EntityPropertiesTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.MakeCNPJTest;
import br.com.gbvbahia.maker.works.MakeCPFTest;
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
     * Test of factoryProperties method, of class MakeWorksFactory.
     */
    @Test
    public void testFactoryPropertiesMultiplos() {
        logger.info("FactoryProperties");
        for (int i = 0; i < 50; i++) {
            EntityPropertiesTest test = MakeEntity.makeEntity("test1",
                    EntityPropertiesTest.class, true);
            assertTrue("Sem erros", true);
            assertNotNull("EntityPropertiesTest não pode ser nula", test);
            assertNotNull("EntityPropertiesTest.cpf não pode ser nulo",
                    test.getCpf());
            assertTrue("CPF não válido!", MakeCPFTest.validarCPF(test.getCpf()));
            assertNotNull("EntityPropertiesTest.cnpj não pode ser nulo",
                    test.getCnpj());
            assertTrue("CNPJ não válido!", MakeCNPJTest.validarCNPJ(test.getCnpj()));
            assertNotNull("EntityPropertiesTest.nome não pode ser nulo",
                    test.getNome());
            assertNotNull("EntityPropertiesTest.cep não pode ser nulo",
                    test.getCep());

            logger.debug(test);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameReservedException() throws Exception {
        logger.info("NameReservedException");
        try {
            EntityPropertiesTest test = MakeEntity.makeEntity(MakeWorksFactory.WORK_USER_IMPL + "_1",
                    EntityPropertiesTest.class, true);
            fail("Uma IllegalArgumentException era esperada.");
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            assertTrue("OK exeção esperada", true);
        }
    }
}
