/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.entityes.EntitySyntheticTest;
import br.com.gbvbahia.maker.log.LogInfo;
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
public class MakeEntitySynteticTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeEntitySynteticTest");
    private Validator validator;

    @Before
    @Override
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public MakeEntitySynteticTest() {
        super("Make Syntetic Entity");
    }

    @Test
    public void testMakeSyntetic() throws Exception {
        logger.info("Entity - MakeSyntetic");
        EntitySyntheticTest test = MakeEntity.makeEntity(EntitySyntheticTest.class, true);
        assertNotNull("Test é nulo.", test);
        assertNotNull("Ref Ciclica nula.", test.getCicleTest());
        assertNotNull("Entidade NotNullTest nula", test.getNotNullTest());
        assertNull("NotEntity não é nula.", test.getNoEntity());
        logger.info(test);
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
