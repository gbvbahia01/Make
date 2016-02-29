package br.com.gbvbahia.maker.works;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Test;

import br.com.gbvbahia.maker.factories.types.works.MakeCpf;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * @since v.1
 * @author Guilherme
 */
public class MakeCpfTest extends TestCase {

  private static Log logger = LogInfo.getLog("Test :: MakeCPFTest");

  public MakeCpfTest() {
    super("Maker :: CPF");
  }

  /**
   * Test of getCPF method, of class MakeCPF.
   */
  @Test
  public void testGetCpf() {
    logger.debug("String - GetCPF");
    for (int i = 0; i < 50; i++) {
      String result = MakeCpf.getCpf();
      assertTrue("CPF: " + result + " INVÁLIDO!", validarCpf(result));
      logger.debug("CPF: " + result);
    }
  }

  /**
   * Checks if the Brazilian CPF is a valid number.
   *
   * @return true if is valid or false if is not.
   */
  public static boolean validarCpf(String cpf) {
    cpf = cpf.replace(".", "").replace("-", "").replace(" ", "");
    if (cpf.length() != 11) {
      return false;
    }
    int d1, d2;
    int digito1, digito2, resto;
    int digitoCpf;
    String digResult;
    d1 = d2 = 0;
    digito1 = digito2 = resto = 0;
    for (int count = 1; count < (cpf.length() - 1); count++) {
      digitoCpf = Integer.valueOf(cpf.substring(count - 1, count)).intValue();
      d1 = d1 + ((11 - count) * digitoCpf);
      d2 = d2 + ((12 - count) * digitoCpf);
    }
    resto = (d1 % 11);
    if (resto < 2) {
      digito1 = 0;
    } else {
      digito1 = 11 - resto;
    }
    d2 += 2 * digito1;
    resto = (d2 % 11);
    if (resto < 2) {
      digito2 = 0;
    } else {
      digito2 = 11 - resto;
    }
    String digVerific = cpf.substring(cpf.length() - 2, cpf.length());
    digResult = String.valueOf(digito1) + String.valueOf(digito2);
    return digVerific.equals(digResult);
  }
}
