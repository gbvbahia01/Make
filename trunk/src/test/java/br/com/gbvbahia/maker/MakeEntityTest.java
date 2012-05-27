/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.entityes.*;
import br.com.gbvbahia.maker.factories.types.LogInfo;
import br.com.gbvbahia.maker.types.string.MakeCharacter;
import br.com.gbvbahia.maker.types.string.MakeString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeEntityTest extends TestCase {
    
    private static Log logger = LogInfo.getLog("Test :: MakeEntityTest");
    private Validator validator;
    
    @Before
    @Override
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    public MakeEntityTest() {
        super("Make Entity");
    }
    
    @Test
    public void testMakePattern() throws Exception {
        logger.info("Entity - MakePattern");
        List<String> listEmail = new ArrayList<String>();
        List<String> listAzAZ = new ArrayList<String>();
        List<String> listNum = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            listEmail.add(MakeString.getEmail());
            listAzAZ.add(MakeCharacter.getLetter().toString());
            listNum.add(MakeCharacter.getNumber().toString());
        }
        Map<String, List<String>> patterns = new HashMap<String, List<String>>();
        patterns.put("EntityPatternTest.email", listEmail);
        patterns.put("EntityPatternTest.azAZ", listAzAZ);
        patterns.put("EntityPatternTest.num", listNum);
        for (int i = 0; i < 50; i++) {
            EntityPatternTest test =
                    MakeEntity.makeEntity(EntityPatternTest.class,
                    patterns);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeSizeString() throws Exception {
        logger.info("Entity - MakeSizeString");
        for (int i = 0; i < 50; i++) {
            EntitySizeTest test = MakeEntity.makeEntity(EntitySizeTest.class);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            assertNotNull("LimiteDefault nula", test.getLimiteDefault());
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeDate() throws Exception {
        logger.info("Entity - MakeDate");
        for (int i = 0; i < 50; i++) {
            EntityDateTest test = MakeEntity.makeEntity(EntityDateTest.class);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            assertNotNull("NoNullDate nula", test.getNoNullDate());
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeDigits() throws Exception {
        logger.info("Entity - MakeDigits");
        for (int i = 0; i < 50; i++) {
            EntityDigitsTest test = MakeEntity.makeEntity(EntityDigitsTest.class);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            assertNotNull("IntegerObjeto nulo", test.getBigDecimal());
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeDecimalEntity() throws Exception {
        logger.info("Entity - MakeDecimalEntity");
        for (int i = 0; i < 50; i++) {
            EntityDecimalTest test = MakeEntity.makeEntity(EntityDecimalTest.class);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            assertNotNull("IntegerObjeto nulo", test.getIntegerObjeto());
            assertTrue("IntergerObjeto maior que 3: "
                    + test.getIntegerObjeto(),
                    test.getIntegerObjeto() <= 3);
            assertTrue("BigDecima maior que 3.5",
                    test.getBigDecimal().doubleValue() <= -3.5);
            assertTrue("MaxBigDecimal maior que -1.79769313486231570E+307",
                    test.getMaxBigDecimal().doubleValue() <= -1.79769313486231570E+307);
            assertTrue("MinBigDecimal menor que 1.79769313486231570E+307",
                    test.getMinBigDecimal().doubleValue() >= 1.79769313486231570E+307);
            assertTrue("ShortObjeto menor que 32760 ou maior que 32765: "
                    + test.getShortObjeto(),
                    test.getShortObjeto() >= 32760
                    && test.getShortObjeto() <= 32765);
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeBoolaenEntity() throws Exception {
        logger.info("Entity - MakeBoolaenEntity");
        EntityBooleanTest test = MakeEntity.makeEntity(EntityBooleanTest.class);
        logger.debug(test);
        assertNotNull("Test é nulo.", test);
        assertTrue("BooleanObjectTrue nao true",
                test.getBooleanObjectTrue());
        assertTrue("BooleanPrimitiveTrue nao true",
                test.getBooleanPrimitiveTrue());
        assertFalse("BooleanObjectFalse nao false",
                test.getBooleanObjectFalse());
        assertFalse("BooleanPrimitiveFalse nao false",
                test.getBooleanPrimitiveFalse());
        assertNotNull("booleanTrueOrFalse nulo",
                test.getBooleanTrueOrFalse());
        validarJSR303(test);
    }
    
    @Test
    public void testMakeMinMaxEntity() throws Exception {
        logger.info("Entity - MakeMinMaxEntity");
        for (int i = 0; i < 100; i++) {
            EntityMinMaxTest test = MakeEntity.makeEntity(EntityMinMaxTest.class);
            logger.debug(test);
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
            assertNotNull("Valor Long nulo",
                    test.getLongObjeto());
            assertTrue("Valor Long inesperado: " + test.getLongObjeto(),
                    test.getLongObjeto() >= -2000000000
                    && test.getLongObjeto() <= -1000000000);
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
            assertTrue("Valor BigInteger inesperado",
                    test.getBigInteger().longValue() >= 300000000
                    && test.getBigInteger().longValue() <= 320500000);
            assertTrue("Valor BigDecimal inesperado",
                    test.getBigDecimal().doubleValue() >= 0
                    && test.getBigDecimal().doubleValue() <= 1);
            assertTrue("Valor Integer Negativo inesperado",
                    test.getInteiroNegativoObjeto() >= -3
                    && test.getInteiroNegativoObjeto() <= -2);
            assertTrue("Valor String inesperado",
                    new Double(test.getString()) <= 10
                    && new Double(test.getString()) >= 5);
            assertTrue("Valor doubleObjeto inesperado",
                    test.getDoubleObjeto() <= 10
                    && test.getDoubleObjeto() >= -50);
            assertTrue("Valor primitivoDouble inesperado",
                    test.getPrimitivoDouble() >= 512
                    && test.getPrimitivoDouble() <= 515);
            assertTrue("Valor floatObjeto inesperado",
                    test.getFloatObjeto() >= 5000
                    || test.getFloatObjeto() <= 5150);
            assertTrue("Valor primitivoFloat inesperado",
                    test.getPrimitivoFloat() >= 1
                    || test.getPrimitivoFloat() <= 3);
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeMinEntity() throws Exception {
        logger.info("Entity - MakeMinEntity");
        for (int i = 0; i < 100; i++) {
            EntityMinTest test = MakeEntity.makeEntity(EntityMinTest.class);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            assertTrue("Valor Integer inesperado",
                    test.getInteiroObjeto() >= 4);
            assertTrue("Valor int inesperado",
                    test.getPrimitivoInt() >= 100);
            assertTrue("Valor Long inesperado: " + test.getLongObjeto(),
                    test.getLongObjeto() >= -1000000000);
            assertTrue("Valor long inesperado: " + test.getPrimitivoLong(),
                    test.getPrimitivoLong() >= 2000000000);
            assertTrue("Valor Byte inesperado",
                    test.getByteObjeto() >= 126);
            assertTrue("Valor byte inesperado",
                    test.getPrimitivoByte() >= 22);
            assertTrue("Valor Short inesperado",
                    test.getShortObjeto() >= 32000);
            assertTrue("Valor short inesperado",
                    test.getPrimitivoShort() >= 32050);
            assertTrue("Valor BigInteger inesperado",
                    test.getBigInteger().longValue() >= 320500000);
            assertTrue("Valor BigDecimal inesperado",
                    test.getBigDecimal().doubleValue() >= 1);
            assertTrue("Valor Integer Negativo inesperado",
                    test.getInteiroNegativoObjeto() >= -2);
            assertTrue("Valor String inesperado",
                    new Double(test.getString()) >= 10);
            assertTrue("Valor doubleObjeto inesperado",
                    test.getDoubleObjeto() >= -500);
            assertTrue("Valor primitivoDouble inesperado",
                    test.getPrimitivoDouble() >= 512);
            assertTrue("Valor floatObjeto inesperado",
                    test.getFloatObjeto() >= 5000);
            assertTrue("Valor primitivoFloat inesperado",
                    test.getPrimitivoFloat() >= 1);
            assertTrue("longObjetoMinMaxValue valor inválido: "
                    + test.getLongObjetoMinMaxValue(),
                    test.getLongObjetoMinMaxValue() >= (Long.MAX_VALUE - 1));
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeMaxEntity() throws Exception {
        logger.info("Entity - MakeMaxEntity");
        for (int i = 0; i < 100; i++) {
            EntityMaxTest test = MakeEntity.makeEntity(EntityMaxTest.class);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            assertTrue("Valor: " + test.getInteiroObjeto() + " maior que 4",
                    test.getInteiroObjeto() <= 4);
            assertTrue("Valor int inesperado",
                    test.getPrimitivoInt() <= 100);
            assertTrue("Valor Long inesperado: " + test.getLongObjeto(),
                    test.getLongObjeto() <= -1000000000);
            assertTrue("Valor long inesperado: " + test.getPrimitivoLong(),
                    test.getPrimitivoLong() <= 2000000000);
            assertTrue("Valor Byte inesperado",
                    test.getByteObjeto() <= 126);
            assertTrue("Valor byte inesperado",
                    test.getPrimitivoByte() <= 22);
            assertTrue("Valor Short inesperado",
                    test.getShortObjeto() <= 32000);
            assertTrue("Valor short inesperado",
                    test.getPrimitivoShort() <= 32050);
            assertTrue("Valor BigInteger inesperado",
                    test.getBigInteger().longValue() <= 320500000);
            assertTrue("Valor BigDecimal inesperado",
                    test.getBigDecimal().doubleValue() <= 1);
            assertTrue("Valor Integer Negativo inesperado",
                    test.getInteiroNegativoObjeto() <= -2);
            assertTrue("Valor String inesperado",
                    new Double(test.getString()) <= 10);
            assertTrue("Valor doubleObjeto inesperado",
                    test.getDoubleObjeto() <= -10);
            assertTrue("Valor primitivoDouble inesperado",
                    test.getPrimitivoDouble() <= -15);
            assertTrue("Valor floatObjeto inesperado",
                    test.getFloatObjeto() <= 5000);
            assertTrue("Valor primitivoFloat inesperado",
                    test.getPrimitivoFloat() <= 1);
            assertTrue("longObjetoMinMaxValue valor inválido: "
                    + test.getLongObjetoMinMaxValue(),
                    test.getLongObjetoMinMaxValue() <= (Long.MIN_VALUE + 1));
            validarJSR303(test);
        }
    }
    
    @Test
    public void testMakeNotNullEntity() throws Exception {
        logger.info("Entity - MakeNotNullEntity");
        for (int i = 0; i < 100; i++) {
            EntityNotNullTest test = MakeEntity.makeEntity(EntityNotNullTest.class);
            logger.debug(test);
            assertNotNull("Test é nulo.", test);
            assertNull("Nulo não nulo", test.getInteiro());
            assertNotNull("inteiroObjeto é nulo.", test.getInteiroObjeto());
            assertNotNull("longObjeto é nulo.", test.getLongObjeto());
            assertNotNull("byteObjeto é nulo.", test.getByteObjeto());
            assertNotNull("shortObjeto é nulo.", test.getShortObjeto());
            assertNotNull("bigInteger é nulo.", test.getBigInteger());
            assertNotNull("bigDecimal é nulo.", test.getBigDecimal());
            assertNotNull("String nula", test.getString());
            assertNotNull("doubleObjeto é nulo", test.getDoubleObjeto());
            assertNotNull("floatObjeto é nulo", test.getFloatObjeto());
            assertNotNull("characterObjeto é nulo", test.getCharacterObjeto());
            validarJSR303(test);
        }
    }
    
    private void validarJSR303(Object test) {
        Set<ConstraintViolation<Object>> erros =
                validator.validate(test);
        for (ConstraintViolation<Object> erro : erros) {
            logger.error(erro.getMessage());
        }
        assertTrue("Erros de validação encontrados",
                erros.isEmpty());
    }
}
