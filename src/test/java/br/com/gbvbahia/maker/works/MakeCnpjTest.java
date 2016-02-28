package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.maker.factories.types.works.MakeCnpj;
import br.com.gbvbahia.maker.log.LogInfo;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 * @since v.1
 * @author Guilherme
 */
public class MakeCnpjTest extends TestCase {

  private static Log logger = LogInfo.getLog("Test :: MakeCNPJTest");

  public MakeCnpjTest() {
    super("Maker :: CNPJ");
  }

  /**
   * Test of getCPF method, of class MakeCNPJ.
   */
  @Test
  public void testGetCnpj() {
    logger.info("String - GetCNPJ");
    for (int i = 0; i < 50; i++) {
      String result = MakeCnpj.getCnpj();
      assertTrue("CNPJ: " + result + " INVÁLIDO, Tamanho: " + result.length(), validarCnpj(result));
      logger.debug("CNPJ: " + result);
    }
  }

  /**
   * Checks if the Brazilian cnpj is a right/valid number.
   *
   * @param strCnpj the Brazilian cnpj that will be checked.
   * @return true for cnpj right or false if is not right.
   */
  public static boolean validarCnpj(String strCnpj) {
    if (strCnpj == null) {
      return false;
    }
    if (strCnpj.length() != 14) {
      return false;
    }
    int soma = 0, aux, dig;
    String cnpj_calc = strCnpj.substring(0, 12);
    char[] chr_cnpj = strCnpj.toCharArray();
    for (int i = 0; i < 4; i++) {
      if (((chr_cnpj[i] - 48) >= 0) && ((chr_cnpj[i] - 48) <= 9)) {
        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
      }
    }
    for (int i = 0; i < 8; i++) {
      if (((chr_cnpj[i + 4] - 48) >= 0) && ((chr_cnpj[i + 4] - 48) <= 9)) {
        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
      }
    }
    dig = 11 - (soma % 11);
    cnpj_calc += ((dig == 10) || (dig == 11)) ? "0" : Integer.toString(dig);
    soma = 0;
    for (int i = 0; i < 5; i++) {
      if (((chr_cnpj[i] - 48) >= 0) && ((chr_cnpj[i] - 48) <= 9)) {
        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
      }
    }
    for (int i = 0; i < 8; i++) {
      if (((chr_cnpj[i + 5] - 48) >= 0) && ((chr_cnpj[i + 5] - 48) <= 9)) {
        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
      }
    }
    dig = 11 - (soma % 11);
    cnpj_calc += ((dig == 10) || (dig == 11)) ? "0" : Integer.toString(dig);
    return strCnpj.equals(cnpj_calc);
  }
}
