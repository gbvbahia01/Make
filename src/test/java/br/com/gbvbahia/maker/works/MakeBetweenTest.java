package br.com.gbvbahia.maker.works;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import br.com.gbvbahia.entities.EntityBetweenTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.works.MakeBetween;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeBetweenTest extends TestCase {

  private static Log logger = LogInfo.getLog("Test :: MakeBetweenTest");
  private Pattern pattern;
  private Validator validator;

  public MakeBetweenTest() {
    super("MakeBetweenTest");
  }

  @Before
  @Override
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
    this.pattern = Pattern.compile(MakeBetween.KEY_PROPERTY);
  }

  @Test
  public void testRegexBetween() throws Exception {
    logger.info("String - GetRegexBetween");
    Matcher matcher = this.pattern.matcher("between{1,1}");
    boolean test = matcher.find();
    assertTrue("Primeiro: deveria ser true.", test);

    matcher = this.pattern.matcher("between{0,15}");
    boolean test2 = matcher.find();
    assertTrue("Segundo: deveria ser true.", test2);

    matcher = this.pattern.matcher("between{2.0001,15.5}");
    boolean test3 = matcher.find();
    assertTrue("Terceiro: deveria ser true.", test3);

    matcher = this.pattern.matcher("between{-100,-15.004}");
    boolean test4 = matcher.find();
    assertTrue("Quarto: deveria ser true.", test4);

    matcher = this.pattern.matcher("isSet{}");
    boolean testfalse1 = matcher.find();
    assertFalse("TesteFalse1: deveria ser false.", testfalse1);

    matcher = this.pattern.matcher("between{ -100,-15}");
    boolean testfalse2 = matcher.find();
    assertFalse("TesteFalse2: deveria ser false.", testfalse2);

    matcher = this.pattern.matcher("between10,15");
    boolean testfalse3 = matcher.find();
    assertFalse("TesteFalse3: deveria ser false.", testfalse3);

    matcher = this.pattern.matcher("between{0, 15}");
    boolean testfalse4 = matcher.find();
    assertFalse("TesteFalse4: deveria ser false.", testfalse4);

    matcher = this.pattern.matcher("between {0,15}");
    boolean testfalse5 = matcher.find();
    assertFalse("TesteFalse5: deveria ser false.", testfalse5);

    matcher = this.pattern.matcher("between{0.0.0,15}");
    boolean testfalse6 = matcher.find();
    assertFalse("TesteFalse6: deveria ser false.", testfalse6);

  }

  @Test
  public void testPopularBetween() throws Exception {
    logger.info("String - GetPopularBetween");
    Factory.loadSetup("make.xml");
    for (int i = 0; i < 50; i++) {
      EntityBetweenTest test = MakeEntity.make(EntityBetweenTest.class, "testBetween1");
      logger.debug(test);
      assertNotNull("Test 0: test não pode ser nulo.", test);

      boolean condition = (test.getBetween5_10() >= 5) && (test.getBetween5_10() <= 10);
      assertTrue("Test 1: Entre 5 e 10: " + test.getBetween5_10(), condition);

      condition = (test.getBetween10_20() >= 10) && (test.getBetween10_20() <= 20);
      assertTrue("Test 2: Entre 10 e 20: " + test.getBetween10_20(), condition);

      condition =
          (test.getBetween_M5V40_5V56().doubleValue() >= -5.40)
              && (test.getBetween_M5V40_5V56().doubleValue() <= 5.56);
      assertTrue("Test 3: Entre -5.40 e 5.56: " + test.getBetween_M5V40_5V56(), condition);

      condition =
          (test.getBetween_M50V13_M20V15() >= -50.13)
              && (test.getBetween_M50V13_M20V15() <= -20.15);
      assertTrue("Test 4: Entre -50.13 e -20.15: " + test.getBetween_M50V13_M20V15(), condition);

      condition = (test.getBetween_M50_50() >= -50) && (test.getBetween_M50_50() <= 50);
      assertTrue("Test 5: Entre -50 e 50: " + test.getBetween_M50_50(), condition);

      condition = (test.getBetween5_23() >= 5) && (test.getBetween5_23() <= 23);
      assertTrue("Test 6: Entre 5 e 23: " + test.getBetween5_23(), condition);

      condition = (test.getBetween_M5_5() >= -5) && (test.getBetween_M5_5() <= 5);
      assertTrue("Test 7: Entre -5 e 5: " + test.getBetween_M5_5(), condition);

      this.validarJsr303(test);
    }
  }

  private void validarJsr303(Object test) {
    Set<ConstraintViolation<Object>> erros = this.validator.validate(test);
    for (ConstraintViolation<Object> erro : erros) {
      logger.error(erro.getMessage());
    }
    assertTrue("Erros de validação encontrados", erros.isEmpty());
  }
}
