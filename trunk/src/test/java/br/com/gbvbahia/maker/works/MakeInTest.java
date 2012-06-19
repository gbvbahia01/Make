/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.maker.log.LogInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class MakeInTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeInTest");
    private Pattern pattern;
    private Validator validator;

    public MakeInTest() {
        super("MakeInTest");
    }

    @Before
    @Override
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        pattern = Pattern.compile(MakeIn.KEY_PROPERTIE);
    }

    @Test
    public void testRegexIn() throws Exception {
        logger.info("String - GetRegexIn");

        Matcher matcher = pattern.matcher("in{1}[a]");
        boolean test = matcher.find();
        if (test) {
            assertTrue("Primeiro: deveria ser true: ",
                    "in{1}[a]".equals(matcher.group()));
        }
        assertTrue("Primeiro: deveria ser true: " + matcher.group(), test);

        matcher = pattern.matcher("in{1,1}[|]");
        boolean test2 = matcher.find();
        if (test2) {
            assertTrue("Segundo: deveria ser true: ",
                    "in{1,1}[|]".equals(matcher.group()));
        }
        assertTrue("Segundo: deveria ser true: " + matcher.group(), test2);

        matcher = pattern.matcher("in{10018}");
        boolean test3 = matcher.find();
        if (test3) {
            assertTrue("Terceiro: deveria ser true: ",
                    "in{10018}".equals(matcher.group()));
        }
        assertTrue("Terceiro: deveria ser true: " + matcher.group(), test3);

        matcher = pattern.matcher("in{10018}[a]");
        boolean test4 = matcher.find();
        if (test4) {
            assertTrue("Quarto: deveria ser true: ",
                    "in{10018}[a]".equals(matcher.group()));
        }
        assertTrue("Quarto: deveria ser true: " + matcher.group(), test4);

        matcher = pattern.matcher("in{10|018}[|]");
        boolean test5 = matcher.find();
        if (test5) {
            assertTrue("Quinto: deveria ser true: ",
                    "in{10|018}[|]".equals(matcher.group()));
        }
        assertTrue("Quinto: deveria ser true: " + matcher.group(), test5);

        matcher = pattern.matcher("in{10|018}[|]");
        boolean test6 = matcher.find();
        if (test6) {
            assertTrue("Sexto: deveria ser true: ",
                    "in{10|018}[|]".equals(matcher.group()));
        }
        assertTrue("Sexto: deveria ser true: " + matcher.group(), test6);

        matcher = pattern.matcher("in{10|018}[]");
        boolean test7 = matcher.find();
        if (test7) {
            assertTrue("Setimo: deveria ser true: " + matcher.group(),
                    "in{10|018}".equals(matcher.group()));
        }
        assertTrue("Setimo: deveria ser true: " + matcher.group(), test7);

        matcher = pattern.matcher("in{10|018}[|a]");
        boolean test8 = matcher.find();
        if (test8) {
            assertTrue("Oitavo: deveria ser true: ",
                    "in{10|018}[|a]".equals(matcher.group()));
        }
        assertTrue("Oitavo: deveria ser true: " + matcher.group(), test8);
    }
}
