/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.entityes.EntityListComplexTest;
import br.com.gbvbahia.entityes.EntityListTest;
import br.com.gbvbahia.entityes.EntityPatternTest;
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
        validator = factory.getValidator();
        pattern = Pattern.compile(MakeList.KEY_PROPERTIE);
    }

    @Test
    public void testRegexList() throws Exception {
        Matcher matcher = pattern.matcher("isList{br.com.compre}[1,1]");
        boolean test = matcher.find();
        assertTrue("Primeiro: deveria ser true.", test);

        matcher = pattern.matcher("isList{br.com.compre}[0,10]");
        boolean test2 = matcher.find();
        assertTrue("Segundo: deveria ser true.", test2);

        matcher = pattern.matcher("isList{br.com.gbvbahia.entityes.EntityPatternTest}[999,10000]");
        boolean test3 = matcher.find();
        assertTrue("Terceiro: deveria ser true.", test3);

        matcher = pattern.matcher("isList{}[9,10]");
        boolean testfalse1 = matcher.find();
        assertFalse("TesteFalse1: deveria ser false.", testfalse1);

        matcher = pattern.matcher("isList{}");
        boolean testfalse2 = matcher.find();
        assertFalse("TesteFalse2: deveria ser false.", testfalse2);

        matcher = pattern.matcher("isList{br. com.compre}[1,3]");
        boolean testfalse2_5 = matcher.find();
        assertFalse("TesteFalse2_5: deveria ser false.", testfalse2_5);

        matcher = pattern.matcher("isList{br.com.compre}9,9");
        boolean testfalse3 = matcher.find();
        assertFalse("TesteFalse3: deveria ser false.", testfalse3);

        matcher = pattern.matcher("isList{br.com.compre}[a9,23]");
        boolean testfalse4 = matcher.find();
        assertFalse("TesteFalse4: deveria ser false.", testfalse4);

        matcher = pattern.matcher("isList[br.com.compre][1,a2]");
        boolean testfalse5 = matcher.find();
        assertFalse("TesteFalse5: deveria ser false.", testfalse5);

        matcher = pattern.matcher("isList{br.com.compre}[999]");
        boolean testfalse6 = matcher.find();
        assertFalse("TesteFalse6: deveria ser false.", testfalse6);

        matcher = pattern.matcher("isList{br.com.compre}[9, 9]");
        boolean testfalse7 = matcher.find();
        assertFalse("TesteFalse7: deveria ser false.", testfalse7);

        matcher = pattern.matcher("isList{br.com.compre} [9,9]");
        boolean testfalse8 = matcher.find();
        assertFalse("TesteFalse8: deveria ser false.", testfalse8);
    }

    @Test
    public void testPopularLista() throws Exception {
        EntityListTest test = MakeEntity.makeEntity("testList1",
                EntityListTest.class, false);
        logger.debug(test);
        assertNotNull("Test 1: test não pode ser nulo.", test);
        assertNotNull("Test 1: List de test não pode ser nula.", test.getListPattern());
        assertTrue("Test 1: List de test não pode ser menor que 3.", test.getListPattern().size() >= 3);
        assertTrue("Test 1: List de test não pode ser maior que 5.", test.getListPattern().size() <= 5);
        for (EntityPatternTest entity : test.getListPattern()) {
            validarJSR303(test);
        }
        assertNotNull("Test 2: ListComplex de test não pode ser nula.", test.getListComplex());
        assertTrue("Test 2: ListComplex de test não pode ser menor que 15.", test.getListComplex().size() >= 15);
        assertTrue("Test 2: ListComplex de test não pode ser maior que 25.", test.getListComplex().size() <= 25);
        for (EntityListComplexTest entity : test.getListComplex()) {
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
