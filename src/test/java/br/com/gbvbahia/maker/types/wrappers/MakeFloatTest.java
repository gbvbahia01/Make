package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeFloat;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 * @since v.1
 * @author Guilherme
 */
public class MakeFloatTest extends TestCase {
  private static Log logger = LogInfo.getLog("Test :: MakeFloatTest");

  public MakeFloatTest() {
    super("Maker :: Float");
  }

  /**
   * Test of getIntervalo method, of class MakeInteger.
   */
  @Test
  public void testGetIntervalo() {
    logger.debug("Float - GetIntervalo");
    for (float min = -10.1f; min < 0.1f; min++) {
      for (float max = min + 0.1f; max < 0.1f; max += 0.1f) {
        Double result = MakeDouble.getRange(min, max);
        logger.debug("Max: " + max + " Min:" + min + " Result:" + result);
        assertTrue("Intervalo incorreto: Max: " + max + " Min:" + min + " Result: " + result,
            (result >= min) && (result <= max));
      }
    }

  }

  /**
   * Test of getMax method, of class MakeInteger.
   */
  @Test
  public void testGetMax() {
    logger.debug("Float - GetMax");
    for (float i = 1.11111f; i <= 1120.22f; i += 0.1f) {
      Float result = MakeFloat.getMax(i);
      logger.debug("Max: " + i + " Result: " + result);
      assertTrue("Intervalo incorreta", result <= i);
    }
    Float result2 = MakeFloat.getMax(1f);
    logger.debug("Max: 1 Result: " + result2);
    assertTrue("Teste minimo incorreto", result2 <= 1);
  }
}
