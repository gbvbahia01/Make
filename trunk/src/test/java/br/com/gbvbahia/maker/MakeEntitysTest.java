/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.entityes.EntityNotNullTest;
import br.com.gbvbahia.entityes.EntityPatternTest;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.string.MakeCharacter;
import br.com.gbvbahia.maker.types.string.MakeString;
import br.com.gbvbahia.maker.works.MakeEmail;
import java.util.*;
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
public class MakeEntitysTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeEntitysTest");
    private Validator validator;

    @Before
    @Override
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public MakeEntitysTest() {
        super("Make Entitys");
    }

    @Test
    public void testEntitysError() throws Exception {
        boolean excecao = false;
        try {
            List<EntityNotNullTest> test = MakeEntity.makeEntitys(EntityNotNullTest.class, -10, false);
        } catch (IllegalArgumentException e) {
            excecao = true;
            logger.info(e.getMessage());
        }
        assertTrue("Esperado IllegalArgumentException", excecao);
        excecao = false;
        try {
            List<EntityNotNullTest> test = MakeEntity.makeEntitys(EntityNotNullTest.class, -1, false, null);
        } catch (IllegalArgumentException e) {
            excecao = true;
            logger.info(e.getMessage());
        }
        assertTrue("Esperado IllegalArgumentException", excecao);
    }

    @Test
    public void testEntitysList() throws Exception {
        List<EntityNotNullTest> tests = MakeEntity.makeEntitys(EntityNotNullTest.class, 10, false);
        for (EntityNotNullTest test : tests) {
            validarJSR303(test);
        }
    }

    @Test
    public void testMakePatternList() throws Exception {
        logger.info("Entity - MakePattern");
        List<String> listEmail = new ArrayList<String>();
        List<String> listAzAZ = new ArrayList<String>();
        List<String> listNum = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            listEmail.add(MakeEmail.getEmail());
            listAzAZ.add(MakeCharacter.getLetter().toString());
            listNum.add(MakeCharacter.getNumber().toString());
        }
        Map<String, List<String>> patterns = new HashMap<String, List<String>>();
        patterns.put("EntityPatternTest.email", listEmail);
        patterns.put("EntityPatternTest.azAZ", listAzAZ);
        patterns.put("EntityPatternTest.num", listNum);
        List<EntityPatternTest> tests =
                MakeEntity.makeEntitys(EntityPatternTest.class, 50,
                false, patterns);
        for (EntityPatternTest test : tests) {
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
