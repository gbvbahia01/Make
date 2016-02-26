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

import br.com.gbvbahia.entities.EntityListComplexTest;
import br.com.gbvbahia.entities.EntityListTest;
import br.com.gbvbahia.entities.EntityPatternTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.works.MakeList;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeListTest extends TestCase {

  private static Log logger = LogInfo.getLog("Test :: MakeListTest");
  private Pattern pattern;
  private Validator validator;

  public MakeListTest() {
    super("MakeListTest");
  }

  @Before
  @Override
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
    this.pattern = Pattern.compile(MakeList.KEY_PROPERTY);
  }

  @Test
  public void testPopularLista() throws Exception {
    logger.info("String - GetPopularLista");
    Factory.loadSetup("make.xml");
    EntityListTest test = MakeEntity.make(EntityListTest.class, "testList1");
    logger.debug(test);
    assertNotNull("Test 1: test não pode ser nulo.", test);
    assertNotNull("Test 1: List de test não pode ser nula.", test.getListPattern());
    assertTrue("Test 1: List de test não pode ser menor que 3.", test.getListPattern().size() >= 3);
    assertTrue("Test 1: List de test não pode ser maior que 5.", test.getListPattern().size() <= 5);
    for (EntityPatternTest entity : test.getListPattern()) {
      this.validarJsr303(entity);
    }
    assertNotNull("Test 2: ListComplex de test não pode ser nula.", test.getListComplex());
    assertTrue("Test 2: ListComplex de test não pode ser menor que 15.", test.getListComplex()
        .size() >= 15);
    assertTrue("Test 2: ListComplex de test não pode ser maior que 25.", test.getListComplex()
        .size() <= 25);
    for (EntityListComplexTest entity : test.getListComplex()) {
      this.validarJsr303(entity);
    }
  }

  @Test
  public void testRegexList() throws Exception {
    logger.info("String - GetRegexList");
    Matcher matcher = this.pattern.matcher("isList{br.com.compre}[1,1]");
    boolean test = matcher.find();
    assertTrue("test: must be true.", test);

    matcher = this.pattern.matcher("isList{br.com.compre}[0,10]");
    boolean test2 = matcher.find();
    assertTrue("test2: must be true.", test2);

    matcher = this.pattern.matcher("isList{br.com.gbvbahia.entityes.EntityPatternTest}[999,10000]");
    boolean test3 = matcher.find();
    assertTrue("test3: must be true.", test3);

    matcher = this.pattern.matcher("isList{}[9,10]");
    boolean testfalse1 = matcher.find();
    assertFalse("testfalse1: must be false.", testfalse1);

    matcher = this.pattern.matcher("isList{}");
    boolean testfalse2 = matcher.find();
    assertFalse("testfalse2: must be false.", testfalse2);

    matcher = this.pattern.matcher("isList{br. com.compre}[1,3]");
    boolean testFalse25 = matcher.find();
    assertFalse("testefalse25: must be false.", testFalse25);

    matcher = this.pattern.matcher("isList{br.com.compre}9,9");
    boolean testfalse3 = matcher.find();
    assertFalse("testfalse3: must be false.", testfalse3);

    matcher = this.pattern.matcher("isList{br.com.compre}[a9,23]");
    boolean testfalse4 = matcher.find();
    assertFalse("testfalse4: must be false.", testfalse4);

    matcher = this.pattern.matcher("isList[br.com.compre][1,a2]");
    boolean testfalse5 = matcher.find();
    assertFalse("testeFalse5: must be false.", testfalse5);

    matcher = this.pattern.matcher("isList{br.com.compre}[999]");
    boolean testfalse6 = matcher.find();
    assertFalse("TesteFalse6: must be false.", testfalse6);

    matcher = this.pattern.matcher("isList{br.com.compre}[9, 9]");
    boolean testfalse7 = matcher.find();
    assertFalse("TesteFalse7: must be false.", testfalse7);

    matcher = this.pattern.matcher("isList{br.com.compre} [9,9]");
    boolean testfalse8 = matcher.find();
    assertFalse("TesteFalse8: must be false.", testfalse8);

    matcher = this.pattern.matcher("isSet{br.com.gbvbahia.entityes.EntityPatternTest}[3,5]");
    boolean testfalse9 = matcher.find();
    assertFalse("TesteFalse9: must be false.", testfalse9);
  }

  @Test(expected = ValueSpecializedException.class)
  public void testClassNotFoundException() {
    MakeEntity.make(EntityListTest.class, "testListClassNotFoundException");
  }

  private void validarJsr303(Object test) {
    Set<ConstraintViolation<Object>> erros = this.validator.validate(test);
    for (ConstraintViolation<Object> erro : erros) {
      logger.error(erro.getMessage());
    }
    assertTrue("Erros de validação encontrados", erros.isEmpty());
  }
}
