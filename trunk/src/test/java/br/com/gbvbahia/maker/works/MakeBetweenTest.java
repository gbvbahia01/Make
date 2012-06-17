/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.entityes.EntityPatternTest;
import br.com.gbvbahia.entityes.EntitySetComplexTest;
import br.com.gbvbahia.entityes.EntitySetTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.log.LogInfo;
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

/**
 *
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
        validator = factory.getValidator();
        pattern = Pattern.compile(MakeBetween.KEY_PROPERTIE);
    }

    @Test
    public void testRegexBetween() throws Exception {
        Matcher matcher = pattern.matcher("between[1,1]");
        boolean test = matcher.find();
        assertTrue("Primeiro: deveria ser true.", test);

        matcher = pattern.matcher("between[0,15]");
        boolean test2 = matcher.find();
        assertTrue("Segundo: deveria ser true.", test2);

        matcher = pattern.matcher("between[2.0001,15.5]");
        boolean test3 = matcher.find();
        assertTrue("Terceiro: deveria ser true.", test3);

        matcher = pattern.matcher("between[-100,-15.004]");
        boolean test4 = matcher.find();
        assertTrue("Quarto: deveria ser true.", test4);

        matcher = pattern.matcher("isSet{}");
        boolean testfalse1 = matcher.find();
        assertFalse("TesteFalse1: deveria ser false.", testfalse1);

        matcher = pattern.matcher("between[ -100,-15]");
        boolean testfalse2 = matcher.find();
        assertFalse("TesteFalse2: deveria ser false.", testfalse2);

        matcher = pattern.matcher("between10,15");
        boolean testfalse3 = matcher.find();
        assertFalse("TesteFalse3: deveria ser false.", testfalse3);

        matcher = pattern.matcher("between[0, 15]");
        boolean testfalse4 = matcher.find();
        assertFalse("TesteFalse4: deveria ser false.", testfalse4);

        matcher = pattern.matcher("between [0,15]");
        boolean testfalse5 = matcher.find();
        assertFalse("TesteFalse5: deveria ser false.", testfalse5);

        matcher = pattern.matcher("between[0.0.0,15]");
        boolean testfalse6 = matcher.find();
        assertFalse("TesteFalse6: deveria ser false.", testfalse6);

    }

//    @Test
//    public void testPopularLista() throws Exception {
//        EntitySetTest test = MakeEntity.makeEntity("testSet1",
//                EntitySetTest.class, false);
//        logger.debug(test);
//        assertNotNull("Test 1: test não pode ser nulo.", test);
//        assertNotNull("Test 1: Set de test não pode ser nula.", test.getSetPattern());
//        assertTrue("Test 1: Set de test não pode ser menor que 3.", test.getSetPattern().size() >= 3);
//        assertTrue("Test 1: Set de test não pode ser maior que 5.", test.getSetPattern().size() <= 5);
//        for (EntityPatternTest entity : test.getSetPattern()) {
//            validarJSR303(entity);
//        }
//        assertNotNull("Test 2: SetComplex de test não pode ser nula.", test.getSetComplex());
//        assertTrue("Test 2: SetComplex de test não pode ser menor que 15.", test.getSetComplex().size() >= 15);
//        assertTrue("Test 2: SetComplex de test não pode ser maior que 25.", test.getSetComplex().size() <= 25);
//        for (EntitySetComplexTest entity : test.getSetComplex()) {
//            validarJSR303(entity);
//        }
//    }
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
