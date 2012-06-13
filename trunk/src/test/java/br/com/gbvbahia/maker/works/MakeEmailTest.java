/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.entityes.EntityPatternTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.log.LogInfo;
import java.util.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme
 */
public class MakeEmailTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeEmailTest");
    private Validator validator;

    public MakeEmailTest() {
    }

    @Before
    @Override
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testMakePattern() throws Exception {
        logger.info("Entity - MakePattern");
        List<String> listEmail = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            listEmail.add(MakeEmail.getEmail());
        }
        for (int i = 0; i < 50; i++) {
            EntityPatternTest test =
                    MakeEntity.makeEntity("test2", EntityPatternTest.class, false);
            logger.info(test);
            assertNotNull("Test é nulo.", test);
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
