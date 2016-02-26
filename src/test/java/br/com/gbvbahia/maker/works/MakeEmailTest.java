package br.com.gbvbahia.maker.works;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import br.com.gbvbahia.entities.EntityPatternTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.works.MakeEmail;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEmailTest extends TestCase {

  private static Log logger = LogInfo.getLog("Test :: MakeEmailTest");
  private Validator validator;

  public MakeEmailTest() {}

  @Before
  @Override
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @Test
  public void testMakePattern() throws Exception {
    logger.info("Entity - MakePattern");
    Factory.loadSetup("make.xml");
    List<String> listEmail = new ArrayList<String>();
    for (int i = 0; i < 10; i++) {
      listEmail.add(MakeEmail.getEmail());
    }
    for (int i = 0; i < 50; i++) {
      EntityPatternTest test = MakeEntity.makeEntity(EntityPatternTest.class, "test2");
      logger.debug(test);
      assertNotNull("Test é nulo.", test);
      this.validarJSR303(test);
    }
  }

  private void validarJSR303(Object test) {
    Set<ConstraintViolation<Object>> erros = this.validator.validate(test);
    for (ConstraintViolation<Object> erro : erros) {
      logger.error(erro.getMessage());
    }
    assertTrue("Erros de validação encontrados", erros.isEmpty());
  }
}
