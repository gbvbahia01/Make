/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.maker.log.LogInfo;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeCNPJTest extends TestCase {

    private static Log logger = LogInfo.getLog("Test :: MakeCNPJTest");

    public MakeCNPJTest() {
        super("Maker :: CNPJ");
    }

    /**
     * Test of getCPF method, of class MakeCNPJ.
     */
    @Test
    public void testGetCNPJ() {
        logger.info("String - GetCNPJ");
        for (int i = 0; i < 50; i++) {
            String result = MakeCNPJ.getCNPJ();
            assertTrue("CNPJ: " + result + " INVÁLIDO, Tamanho: "
                    + result.length(), validarCNPJ(result));
            logger.debug("CNPJ: " + result);
        }
    }

    /**
     * Código Java de uma classe com os métodos de validação de CNPJ
     * de acordo com as regras da Receita Federal.
     *
     * @param str_cnpj
     * @return retorna verdadeiro (true) para CNPJ válido e falso
     * (false) para CNPJ inválido
     */
    public static boolean validarCNPJ(String str_cnpj) {
        if (str_cnpj == null) {
            return false;
        }
        if (str_cnpj.length() != 14) {
            return false;
        }
        int soma = 0, aux, dig;
        String cnpj_calc = str_cnpj.substring(0, 12);
        char[] chr_cnpj = str_cnpj.toCharArray();

        /*
         * Primeira parte
         */
        for (int i = 0; i < 4; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);

        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        /*
         * Segunda parte
         */
        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);
        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        return str_cnpj.equals(cnpj_calc);
    }
}
