package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeLong;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 * @since v.1
 * @author Guilherme
 */
public class MakeLongTest extends TestCase {
  private static Log logger = LogInfo.getLog("Test :: MakeLongTest");

  public MakeLongTest() {
    super("Maker :: Long");
  }

  /**
   * Test of getIntervalo method, of class MakeInteger.
   */
  @Test
  public void testGetIntervalo() {
    logger.info("Long - GetIntervalo");
    for (long min = 1000000000; min < 1000000100; min++) {
      for (long max = min + 1; max < 1000000200; max++) {
        Long result = MakeLong.getIntervalo(min, max);
        logger.debug("Max: " + max + " Min:" + min + " Result:" + result);
        assertTrue("Intervalo incorreto: Max: " + max + " Min:" + min + " Result: " + result,
            (result >= min) && (result <= max));
      }
    }

  }

  @Test
  public void testMinMaxIsReturned() throws Exception {
    logger.info("Long - MinMaxIsReturned");
    int min = 1;
    int max = 3;
    boolean minOk = false;
    boolean maxOk = false;
    for (int i = 0; i < 100; i++) {
      if (!minOk && (MakeLong.getIntervalo(min, max) == min)) {
        logger.debug("minOk setter true, interation: " + i);
        minOk = true;
      }
      if (!maxOk && (MakeLong.getIntervalo(min, max) == max)) {
        logger.debug("maxOk setter true, interation: " + i);
        maxOk = true;
      }
      if ((MakeLong.getIntervalo(min, max) > max) || (MakeLong.getIntervalo(min, max) < min)) {
        fail("Resultado não pode ser menor minimo ou maior que maximo!");
      }
    }
    assertTrue("Mínimo não foi atingido", minOk);
    assertTrue("Máximo não foi atingido", maxOk);
  }

  /**
   * Test of getMax method, of class MakeInteger.
   */
  @Test
  public void testGetMax() {
    logger.info("Long - GetMax");
    for (int i = 1000000000; i <= 1000000100; i++) {
      Long result = MakeLong.getMax(i);
      logger.debug("Max: " + i + " Result: " + result);
      assertTrue("Intervalo incorreta", result <= i);
    }
    Long result2 = MakeLong.getMax(1L);
    logger.debug("Max: 1 Result: " + result2);
    assertTrue("Teste minimo incorreto", result2 <= 1);
  }
}
