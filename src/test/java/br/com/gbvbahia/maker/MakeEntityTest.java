package br.com.gbvbahia.maker;

import br.com.gbvbahia.entities.ConstructorDefault;
import br.com.gbvbahia.entities.ConstructorNotDefault;
import br.com.gbvbahia.entities.EntityBooleanTest;
import br.com.gbvbahia.entities.EntityDateTest;
import br.com.gbvbahia.entities.EntityDecimalTest;
import br.com.gbvbahia.entities.EntityDigitsTest;
import br.com.gbvbahia.entities.EntityEnumTest;
import br.com.gbvbahia.entities.EntityEnumTest2;
import br.com.gbvbahia.entities.EntityMaxTest;
import br.com.gbvbahia.entities.EntityMinMaxTest;
import br.com.gbvbahia.entities.EntityMinTest;
import br.com.gbvbahia.entities.EntityNotNullTest;
import br.com.gbvbahia.entities.EntitySizeTest;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.properties.exception.MakeCreationException;
import br.com.gbvbahia.maker.log.LogInfo;

import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEntityTest {

  private static Log logger = LogInfo.getLog("Test :: MakeEntityTest");
  private Validator validator;

  /**
   * Load XML setup and prepare the JSR303 and buildDefaultValidatorFactory object.
   */
  @Before
  public void setUp() {
    // Load the xml I want, not necessary if is make.xml
    Factory.loadSetup("make.xml");
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @After
  public void rollback() {
    // roll back to default make.xml, not necessary if I did not change.
    Factory.loadSetup("make.xml");
  }

  public MakeEntityTest() {}

  @Test(expected = MakeCreationException.class)
  public void testMakeEnum() throws Exception {
    logger.info("Entity - MakeEnum");
    EntityEnumTest test = MakeEntity.make(EntityEnumTest.class);
    Assert.assertNotNull("Test is null.", test);
    Assert.assertNotNull("EnumExternalTest is null", test.getEnumExternalTest());
    this.validarJsr303(test);
    MakeEntity.make(EntityEnumTest2.class);
    Assert.fail("Enum Clean deveria ter uma UnsupportedOperationException");
  }

  @Test
  public void testMakeSizeString() throws Exception {
    logger.info("Entity - MakeSizeString");
    for (int i = 0; i < 50; i++) {
      EntitySizeTest test = MakeEntity.make(EntitySizeTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertNotNull("LimiteDefault nula", test.getLimiteDefault());
      this.validarJsr303(test);
    }
  }

  @Test
  public void testMakeDate() throws Exception {
    logger.info("Entity - MakeDate");
    for (int i = 0; i < 50; i++) {
      EntityDateTest test = MakeEntity.make(EntityDateTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertNotNull("NoNullDate nula", test.getNoNullDate());
      this.validarJsr303(test);
    }
  }

  @Test
  public void testMakeDigits() throws Exception {
    logger.info("Entity - MakeDigits");
    for (int i = 0; i < 50; i++) {
      EntityDigitsTest test = MakeEntity.make(EntityDigitsTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertNotNull("BigDecimal nulo", test.getBigDecimal());
      this.validarJsr303(test);
    }
  }

  @Test
  public void testMakeDecimalEntity() throws Exception {
    logger.info("Entity - MakeDecimalEntity");
    for (int i = 0; i < 50; i++) {
      EntityDecimalTest test = MakeEntity.make(EntityDecimalTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertNotNull("IntegerObjeto nulo", test.getIntegerObjeto());
      Assert.assertTrue("IntergerObjeto maior que 3: " + test.getIntegerObjeto(),
          test.getIntegerObjeto() <= 3);
      Assert.assertTrue("BigDecima maior que 3.5", test.getBigDecimal().doubleValue() <= -3.5);
      Assert.assertTrue("MaxBigDecimal maior que -1.79769313486231570E+307", test
          .getMaxBigDecimal().doubleValue() <= -1.79769313486231570E+307);
      Assert.assertTrue("MinBigDecimal menor que 1.79769313486231570E+307", test.getMinBigDecimal()
          .doubleValue() >= 1.79769313486231570E+307);
      Assert.assertTrue("ShortObjeto menor que 32760 ou maior que 32765: " + test.getShortObjeto(),
          (test.getShortObjeto() >= 32760) && (test.getShortObjeto() <= 32765));
      this.validarJsr303(test);
    }
  }

  @Test
  public void testMakeBoolaenEntity() throws Exception {
    logger.info("Entity - MakeBoolaenEntity");
    EntityBooleanTest test = MakeEntity.make(EntityBooleanTest.class);
    logger.debug(test);
    Assert.assertNotNull("Test é nulo.", test);
    Assert.assertTrue("BooleanObjectTrue nao true", test.getBooleanObjectTrue());
    Assert.assertTrue("BooleanPrimitiveTrue nao true", test.getBooleanPrimitiveTrue());
    Assert.assertFalse("BooleanObjectFalse nao false", test.getBooleanObjectFalse());
    Assert.assertFalse("BooleanPrimitiveFalse nao false", test.getBooleanPrimitiveFalse());
    Assert.assertNotNull("booleanTrueOrFalse nulo", test.getBooleanTrueOrFalse());
    this.validarJsr303(test);
  }

  @Test
  public void testMakeMinMaxEntity() throws Exception {
    logger.info("Entity - MakeMinMaxEntity");
    for (int i = 0; i < 100; i++) {
      EntityMinMaxTest test = MakeEntity.make(EntityMinMaxTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertNull("Nulo não nulo", test.getInteiro());
      Assert.assertTrue("Valor Integer inesperado", test.getInteiroObjeto() >= 3);
      Assert.assertTrue("Valor Integer inesperado", test.getInteiroObjeto() <= 4);
      Assert.assertTrue("Valor int inesperado", test.getPrimitivoInt() >= 0);
      Assert.assertTrue("Valor int inesperado", test.getPrimitivoInt() <= 100);
      Assert.assertNotNull("Valor Long nulo", test.getLongObjeto());
      Assert.assertTrue("Valor Long inesperado: " + test.getLongObjeto(),
          (test.getLongObjeto() >= -2000000000) && (test.getLongObjeto() <= -1000000000));
      Assert.assertFalse("Valor long inesperado", test.getPrimitivoLong() < 1000000000);
      Assert.assertFalse("Valor long inesperado", test.getPrimitivoLong() > 2000000000);
      Assert.assertTrue("Valor Byte inesperado",
          (test.getByteObjeto() >= 50) && (test.getByteObjeto() <= 126));
      Assert.assertTrue("Valor byte inesperado",
          (test.getPrimitivoByte() >= 11) && (test.getPrimitivoByte() <= 22));
      Assert.assertTrue("Valor Short inesperado",
          (test.getShortObjeto() >= 30000) && (test.getShortObjeto() <= 32000));
      Assert.assertTrue("Valor short inesperado",
          (test.getPrimitivoShort() >= 32000) && (test.getPrimitivoShort() <= 32050));
      Assert.assertTrue("Valor BigInteger inesperado",
          (test.getBigInteger().longValue() >= 300000000)
              && (test.getBigInteger().longValue() <= 320500000));
      Assert.assertTrue("Valor BigDecimal inesperado", (test.getBigDecimal().doubleValue() >= 0)
          && (test.getBigDecimal().doubleValue() <= 1));
      Assert.assertTrue("Valor Integer Negativo inesperado",
          (test.getInteiroNegativoObjeto() >= -3) && (test.getInteiroNegativoObjeto() <= -2));
      Assert.assertTrue("Valor String inesperado", (new Double(test.getString()) <= 10)
          && (new Double(test.getString()) >= 5));
      Assert.assertTrue("Valor doubleObjeto inesperado",
          (test.getDoubleObjeto() <= 10) && (test.getDoubleObjeto() >= -50));
      Assert.assertTrue("Valor primitivoDouble inesperado", (test.getPrimitivoDouble() >= 512)
          && (test.getPrimitivoDouble() <= 515));
      Assert.assertTrue("Valor floatObjeto inesperado",
          (test.getFloatObjeto() >= 5000) || (test.getFloatObjeto() <= 5150));
      Assert.assertTrue("Valor primitivoFloat inesperado",
          (test.getPrimitivoFloat() >= 1) || (test.getPrimitivoFloat() <= 3));
      this.validarJsr303(test);
    }
  }

  @Test
  public void testMakeMinEntity() throws Exception {
    logger.info("Entity - MakeMinEntity");
    for (int i = 0; i < 100; i++) {
      EntityMinTest test = MakeEntity.make(EntityMinTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertTrue("Valor Integer inesperado", test.getInteiroObjeto() >= 4);
      Assert.assertTrue("Valor int inesperado", test.getPrimitivoInt() >= 100);
      Assert.assertTrue("Valor Long inesperado: " + test.getLongObjeto(),
          test.getLongObjeto() >= -1000000000);
      Assert.assertTrue("Valor long inesperado: " + test.getPrimitivoLong(),
          test.getPrimitivoLong() >= 2000000000);
      Assert.assertTrue("Valor Byte inesperado", test.getByteObjeto() >= 126);
      Assert.assertTrue("Valor byte inesperado", test.getPrimitivoByte() >= 22);
      Assert.assertTrue("Valor Short inesperado", test.getShortObjeto() >= 32000);
      Assert.assertTrue("Valor short inesperado", test.getPrimitivoShort() >= 32050);
      Assert.assertTrue("Valor BigInteger inesperado",
          test.getBigInteger().longValue() >= 320500000);
      Assert.assertTrue("Valor BigDecimal inesperado", test.getBigDecimal().doubleValue() >= 1);
      Assert.assertTrue("Valor Integer Negativo inesperado", test.getInteiroNegativoObjeto() >= -2);
      Assert.assertTrue("Valor String inesperado", new Double(test.getString()) >= 10);
      Assert.assertTrue("Valor doubleObjeto inesperado", test.getDoubleObjeto() >= -500);
      Assert.assertTrue("Valor primitivoDouble inesperado", test.getPrimitivoDouble() >= 512);
      Assert.assertTrue("Valor floatObjeto inesperado", test.getFloatObjeto() >= 5000);
      Assert.assertTrue("Valor primitivoFloat inesperado", test.getPrimitivoFloat() >= 1);
      Assert.assertTrue("longObjetoMinMaxValue valor inválido: " + test.getLongObjetoMinMaxValue(),
          test.getLongObjetoMinMaxValue() >= (Long.MAX_VALUE - 1));
      this.validarJsr303(test);
    }
  }

  @Test
  public void testMakeMaxEntity() throws Exception {
    logger.info("Entity - MakeMaxEntity");
    for (int i = 0; i < 100; i++) {
      EntityMaxTest test = MakeEntity.make(EntityMaxTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertTrue("Valor: " + test.getInteiroObjeto() + " maior que 4",
          test.getInteiroObjeto() <= 4);
      Assert.assertTrue("Valor int inesperado", test.getPrimitivoInt() <= 100);
      Assert.assertTrue("Valor Long inesperado: " + test.getLongObjeto(),
          test.getLongObjeto() <= -1000000000);
      Assert.assertTrue("Valor long inesperado: " + test.getPrimitivoLong(),
          test.getPrimitivoLong() <= 2000000000);
      Assert.assertTrue("Valor Byte inesperado", test.getByteObjeto() <= 126);
      Assert.assertTrue("Valor byte inesperado", test.getPrimitivoByte() <= 22);
      Assert.assertTrue("Valor Short inesperado", test.getShortObjeto() <= 32000);
      Assert.assertTrue("Valor short inesperado", test.getPrimitivoShort() <= 32050);
      Assert.assertTrue("Valor BigInteger inesperado",
          test.getBigInteger().longValue() <= 320500000);
      Assert.assertTrue("Valor BigDecimal inesperado", test.getBigDecimal().doubleValue() <= 1);
      Assert.assertTrue("Valor Integer Negativo inesperado", test.getInteiroNegativoObjeto() <= -2);
      Assert.assertTrue("Valor String inesperado", new Double(test.getString()) <= 10);
      Assert.assertTrue("Valor doubleObjeto inesperado", test.getDoubleObjeto() <= -10);
      Assert.assertTrue("Valor primitivoDouble inesperado", test.getPrimitivoDouble() <= -15);
      Assert.assertTrue("Valor floatObjeto inesperado", test.getFloatObjeto() <= 5000);
      Assert.assertTrue("Valor primitivoFloat inesperado", test.getPrimitivoFloat() <= 1);
      Assert.assertTrue("longObjetoMinMaxValue valor inválido: " + test.getLongObjetoMinMaxValue(),
          test.getLongObjetoMinMaxValue() <= (Long.MIN_VALUE + 1));
      this.validarJsr303(test);
    }
  }

  @Test
  public void testMakeNotNullEntity() throws Exception {
    logger.info("Entity - MakeNotNullEntity");
    for (int i = 0; i < 100; i++) {
      EntityNotNullTest test = MakeEntity.make(EntityNotNullTest.class);
      logger.debug(test);
      Assert.assertNotNull("Test é nulo.", test);
      Assert.assertNull("Nulo não nulo", test.getInteiro());
      Assert.assertNotNull("inteiroObjeto é nulo.", test.getInteiroObjeto());
      Assert.assertNotNull("longObjeto é nulo.", test.getLongObjeto());
      Assert.assertNotNull("byteObjeto é nulo.", test.getByteObjeto());
      Assert.assertNotNull("shortObjeto é nulo.", test.getShortObjeto());
      Assert.assertNotNull("bigInteger é nulo.", test.getBigInteger());
      Assert.assertNotNull("bigDecimal é nulo.", test.getBigDecimal());
      Assert.assertNotNull("String nula", test.getString());
      Assert.assertNotNull("doubleObjeto é nulo", test.getDoubleObjeto());
      Assert.assertNotNull("floatObjeto é nulo", test.getFloatObjeto());
      Assert.assertNotNull("characterObjeto é nulo", test.getCharacterObjeto());
      this.validarJsr303(test);
    }
  }

  @Test
  public void testObjectWithClassWithoutDefaultConstructor() {
    ConstructorDefault constructorDefault = MakeEntity.make(ConstructorDefault.class);
    Assert.assertNotNull("Constructor default cannot be null", constructorDefault);
    Assert.assertNull("ConstructorDefault object must has null value at field notDefault",
        constructorDefault.getNotDefault());
  }

  @Test(expected = MakeCreationException.class)
  public void testObjectWithNoDefaultConstructor() {
    ConstructorNotDefault noConstructor = MakeEntity.make(ConstructorNotDefault.class);
    Assert.fail("A exception must be throuwn for " + noConstructor.getClass().getName());
  }

  private void validarJsr303(Object test) {
    Set<ConstraintViolation<Object>> erros = this.validator.validate(test);
    for (ConstraintViolation<Object> erro : erros) {
      logger.error(erro.getMessage());
    }
    Assert.assertTrue("Erros de validação encontrados", erros.isEmpty());
  }
}
