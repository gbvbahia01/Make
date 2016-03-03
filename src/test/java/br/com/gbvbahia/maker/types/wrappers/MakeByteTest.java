package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeByte;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 * @since v.1
 * @author Guilherme
 */
public class MakeByteTest extends TestCase {

  private static Log logger = LogInfo.getLog("Test :: MakeByteTest");

  public MakeByteTest() {
    super("Maker :: Byte");
  }

  /**
   * Test of getIntervalo method, of class MakeInteger.
   */
  @Test
  public void testGetIntervalo() {
    logger.debug("Byte - GetIntervalo");
    for (byte min = 1; min < (Byte.MAX_VALUE - 100); min++) {
      for (byte max = (byte) (min + 1); max < Byte.MAX_VALUE; max++) {
        Byte result = MakeByte.getRange(min, max);
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
    logger.debug("Byte - GetMax");
    for (byte i = 1; i < Byte.MAX_VALUE; i++) {
      Byte result = MakeByte.getMax(i);
      logger.debug("Max: " + i + " Result: " + result);
      assertTrue("Intervalo incorreto", result <= i);
    }
    Byte result2 = MakeByte.getMax(new Byte("1"));
    logger.debug("Max: 1 Result: " + result2);
    assertTrue("Teste minimo incorreto", result2 <= 1);
  }
}
