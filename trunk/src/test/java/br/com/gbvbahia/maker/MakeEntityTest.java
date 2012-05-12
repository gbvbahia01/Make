/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.entityes.EntityMinMaxTest;
import br.com.gbvbahia.entityes.EntityNotNullTest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeEntityTest extends TestCase {

    private Log logger = LogFactory.getLog("MakeEntityTest");

    public MakeEntityTest() {
        super("Make Entity");
    }

    @Test
    public void testMakeMinMaxEntity() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        for (int i = 0; i < 100; i++) {
            EntityMinMaxTest test = MakeEntity.makeEntity(EntityMinMaxTest.class);
            logger.info(test);
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
            assertNotNull("Valor Long inesperado",
                    test.getLongObjeto());
            assertTrue("Valor Long inesperado",
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
            validarJSR303(validator, test);
        }
    }

    @Test
    public void testMakeNotNullEntity() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        for (int i = 0; i < 100; i++) {
            EntityNotNullTest test = MakeEntity.makeEntity(EntityNotNullTest.class);
            logger.info(test);
            assertNotNull("Test é nulo.", test);
            assertNull("Nulo não nulo", test.getInteiro());
            assertNotNull("inteiroObjeto é nulo.", test.getInteiroObjeto());
            assertNotNull("longObjeto é nulo.", test.getLongObjeto());
            assertNotNull("byteObjeto é nulo.", test.getByteObjeto());
            assertNotNull("shortObjeto é nulo.", test.getShortObjeto());
            assertNotNull("bigInteger é nulo.", test.getBigInteger());
            assertNotNull("bigDecimal é nulo.", test.getBigDecimal());
            validarJSR303(validator, test);
        }
    }

    private void validarJSR303(Validator validator, Object test) {
        Set<ConstraintViolation<Object>> erros =
                validator.validate(test);
        for (ConstraintViolation<Object> erro : erros) {
            logger.error(erro.getMessage());
        }
        assertTrue("Erros de validação encontrados",
                erros.isEmpty());
    }
}
