package br.com.gbvbahia.maker;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import br.com.gbvbahia.entities.EntitySyntheticTest;
import br.com.gbvbahia.maker.log.LogInfo;
import junit.framework.TestCase;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEntitySynteticTest extends TestCase {

  private static Log logger = LogInfo.getLog("Test :: MakeEntitySynteticTest");
  private Validator validator;

  @Before
  @Override
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  public MakeEntitySynteticTest() {
    super("Make Syntetic Entity");
  }

  @Test
  public void testMakeSyntetic() throws Exception {
    logger.info("Entity - MakeSyntetic");
    EntitySyntheticTest test = MakeEntity.makeEntity(EntitySyntheticTest.class);
    assertNotNull("Test é nulo.", test);
    assertNotNull("Ref Ciclica nula.", test.getCicleTest());
    assertNotNull("Entidade NotNullTest nula", test.getNotNullTest());
    assertNull("NotEntity não é nula.", test.getNoEntity());
    logger.debug(test);
  }
}
