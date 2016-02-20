package br.com.gbvbahia.maker.properties;

import org.apache.commons.logging.Log;
import org.junit.Test;

import br.com.gbvbahia.entityes.EntityPropertiesTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.MakeCNPJTest;
import br.com.gbvbahia.maker.works.MakeCPFTest;
import junit.framework.TestCase;

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
    logger.info("FactoryProperties");
    for (int i = 0; i < 50; i++) {
      EntityPropertiesTest test = MakeEntity.makeEntity(EntityPropertiesTest.class, "test1");
      assertTrue("Sem erros", true);
      assertNotNull("EntityPropertiesTest não pode ser nula", test);
      assertNotNull("EntityPropertiesTest.cpf não pode ser nulo", test.getCpf());
      assertTrue("CPF não válido!", MakeCPFTest.validarCPF(test.getCpf()));
      assertNotNull("EntityPropertiesTest.cnpj não pode ser nulo", test.getCnpj());
      assertTrue("CNPJ não válido!", MakeCNPJTest.validarCNPJ(test.getCnpj()));
      assertNotNull("EntityPropertiesTest.nome não pode ser nulo", test.getNome());
      assertNotNull("EntityPropertiesTest.cep não pode ser nulo", test.getCep());

      logger.debug(test);
    }
  }
}
