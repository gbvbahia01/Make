package br.com.gbvbahia.maker.works;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import br.com.gbvbahia.entities.EntityInTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.works.MakeIn;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * @since v.1 01/05/2012
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
    this.validator = factory.getValidator();
    this.pattern = Pattern.compile(MakeIn.KEY_PROPERTY);
  }

  @Test
  public void testRegexIn() throws Exception {
    logger.info("String - GetRegexIn");

    Matcher matcher = this.pattern.matcher("in{1}[a]");
    boolean test = matcher.find();
    if (test) {
      assertTrue("Primeiro: deveria ser true: ", "in{1}[a]".equals(matcher.group()));
    }
    assertTrue("Primeiro: deveria ser true: " + matcher.group(), test);

    matcher = this.pattern.matcher("in{1,1}[|]");
    boolean test2 = matcher.find();
    if (test2) {
      assertTrue("Segundo: deveria ser true: ", "in{1,1}[|]".equals(matcher.group()));
    }
    assertTrue("Segundo: deveria ser true: " + matcher.group(), test2);

    matcher = this.pattern.matcher("in{10018}");
    boolean test3 = matcher.find();
    if (test3) {
      assertTrue("Terceiro: deveria ser true: ", "in{10018}".equals(matcher.group()));
    }
    assertTrue("Terceiro: deveria ser true: " + matcher.group(), test3);

    matcher = this.pattern.matcher("in{10018}[a]");
    boolean test4 = matcher.find();
    if (test4) {
      assertTrue("Quarto: deveria ser true: ", "in{10018}[a]".equals(matcher.group()));
    }
    assertTrue("Quarto: deveria ser true: " + matcher.group(), test4);

    matcher = this.pattern.matcher("in{10|018}[|]");
    boolean test5 = matcher.find();
    if (test5) {
      assertTrue("Quinto: deveria ser true: ", "in{10|018}[|]".equals(matcher.group()));
    }
    assertTrue("Quinto: deveria ser true: " + matcher.group(), test5);

    matcher = this.pattern.matcher("in{10|018}[|]");
    boolean test6 = matcher.find();
    if (test6) {
      assertTrue("Sexto: deveria ser true: ", "in{10|018}[|]".equals(matcher.group()));
    }
    assertTrue("Sexto: deveria ser true: " + matcher.group(), test6);

    matcher = this.pattern.matcher("in{10|018}[]");
    boolean test7 = matcher.find();
    if (test7) {
      assertTrue("Setimo: deveria ser true: " + matcher.group(),
          "in{10|018}".equals(matcher.group()));
    }
    assertTrue("Setimo: deveria ser true: " + matcher.group(), test7);

    matcher = this.pattern.matcher("in{10|018}[|a]");
    boolean test8 = matcher.find();
    if (test8) {
      assertTrue("Oitavo: deveria ser true: ", "in{10|018}[|a]".equals(matcher.group()));
    }
    assertTrue("Oitavo: deveria ser true: " + matcher.group(), test8);
  }

  @Test
  public void testInValues() throws Exception {
    logger.info("String - InValues");
    Factory.loadSetup("make.xml");
    for (int i = 0; i < 50; i++) {
      EntityInTest test = MakeEntity.makeEntity(EntityInTest.class, "testIn1");
      logger.debug(test);
      assertNotNull("Test não pode ser nulo", test);
      assertTrue("in_1_2_5 error: " + test.getIn_1_2_5(), test.getIn_1_2_5().equals(1)
          || test.getIn_1_2_5().equals(2) || test.getIn_1_2_5().equals(5));

      assertTrue("in_10V30_10V50_10V80 error: " + test.getIn_10V30_10V50_10V80(), test
          .getIn_10V30_10V50_10V80().equals(new BigDecimal("10.30"))
          || test.getIn_10V30_10V50_10V80().equals(new BigDecimal("10.50"))
          || test.getIn_10V30_10V50_10V80().equals(new BigDecimal("10.80")));

      assertTrue("in_3000_5000_6000 error: " + test.getIn_3000_5000_6000(), test
          .getIn_3000_5000_6000().equals(3000L)
          || test.getIn_3000_5000_6000().equals(5000L)
          || test.getIn_3000_5000_6000().equals(6000L));

      assertTrue("in_45V80_M45V80_100V10: " + test.getIn_45V80_M45V80_100V10(),
          test.getIn_45V80_M45V80_100V10().equals(45.80F)
              || test.getIn_45V80_M45V80_100V10().equals(-45.80F)
              || test.getIn_45V80_M45V80_100V10().equals(100.10F));

      assertTrue("in_500_5000_50000_500000_5000000: " + test.getIn_500_5000_50000_500000_5000000(),
          test.getIn_500_5000_50000_500000_5000000().equals(new BigInteger("500"))
              || test.getIn_500_5000_50000_500000_5000000().equals(new BigInteger("5000"))
              || test.getIn_500_5000_50000_500000_5000000().equals(new BigInteger("50000"))
              || test.getIn_500_5000_50000_500000_5000000().equals(new BigInteger("500000"))
              || test.getIn_500_5000_50000_500000_5000000().equals(new BigInteger("5000000")));

      assertTrue("in_200_300_400 error: " + test.getIn_200_300_400(), test.getIn_200_300_400()
          .equals("200")
          || test.getIn_200_300_400().equals("300")
          || test.getIn_200_300_400().equals("400"));

      assertTrue("in_A_B_C error: " + test.getIn_A_B_C(), test.getIn_A_B_C().equals("A")
          || test.getIn_A_B_C().equals("B") || test.getIn_A_B_C().equals("C"));

      assertTrue("in_Virgula_Arroba_Percentual error: " + test.getIn_Comma_At_Percent(), test
          .getIn_Comma_At_Percent().equals(",")
          || test.getIn_Comma_At_Percent().equals("@")
          || test.getIn_Comma_At_Percent().equals("%"));

      assertTrue("only_30 error: " + test.getOnly_30(), test.getOnly_30() == 30);
    }
  }
}
