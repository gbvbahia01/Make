package br.com.gbvbahia.maker.types.wrappers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.MakeBoolean;

import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 * @since v.1
 * @author Guilherme
 */
public class MakeBooleanTest {

  private static Log logger = LogInfo.getLog("Test :: MakeBooleanTest");

  public MakeBooleanTest() {}

  /**
   * Test of getBoolean method, of class MakeBoolean.
   */
  @Test
  public void testGetBoolean() {
    logger.info("Boolean - GetBoolean");
    boolean trueCheck = false;
    boolean falseCheck = false;
    for (int i = 0; i < 100; i++) {
      Boolean bool = MakeBoolean.getBoolean();
      logger.debug("Result Boolean: " + bool);
      assertNotNull("Boolean nulo!", bool);
      if (bool) {
        trueCheck = true;
      }
      if (!bool) {
        falseCheck = true;
      }
      if (trueCheck && falseCheck) {
        break;
      }
    }
    assertTrue("True ou False não retornado", trueCheck && falseCheck);
  }
}
