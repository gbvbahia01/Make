package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.entities.EntityPropertiesTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.MakeCnpjTest;
import br.com.gbvbahia.maker.works.MakeCpfTest;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 * @since v.1 01/05/2012
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
    Factory.loadSetup("make.xml");
    logger.info("FactoryProperties");
    for (int i = 0; i < 50; i++) {
      EntityPropertiesTest test = MakeEntity.make(EntityPropertiesTest.class, "test1");
      assertTrue("Sem erros", true);
      assertNotNull("EntityPropertiesTest não pode ser nula", test);
      assertNotNull("EntityPropertiesTest.cpf não pode ser nulo", test.getCpf());
      assertTrue("CPF não válido!", MakeCpfTest.validarCpf(test.getCpf()));
      assertNotNull("EntityPropertiesTest.cnpj não pode ser nulo", test.getCnpj());
      assertTrue("CNPJ não válido!", MakeCnpjTest.validarCnpj(test.getCnpj()));
      assertNotNull("EntityPropertiesTest.nome não pode ser nulo", test.getNome());
      assertNotNull("EntityPropertiesTest.cep não pode ser nulo", test.getCep());

      logger.debug(test);
    }
  }
}
