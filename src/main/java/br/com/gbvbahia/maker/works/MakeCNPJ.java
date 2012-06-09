/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.maker.types.string.MakeString;
import br.com.gbvbahia.maker.types.wrappers.MakeBoolean;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import org.apache.commons.lang3.StringUtils;

/**
 * Retorna uma string no formato de um CNPJ válido, em relação a
 * validação do digito verificador. Retorna 12 caracteres mais dois
 * digitos verificadores, totalizando quatorze caracteres. Não retorna
 * formatado, somente números, pontos,ífen e barra são excluídos.<br>
 * ATENÇÃO: Criado para facilitar testes de desenvolvimento de
 * software.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakeCNPJ {

    public static String getCNPJ() {
        String cnpj = MakeString.getString(8, MakeString.StringType.NUMBER);
        if (MakeBoolean.getBoolean()) {
            Integer filiais = MakeInteger.getMax(11);
            if (filiais > 9) {
                cnpj += "00" + MakeInteger.getIntervalo(10, 99);
            } else if (filiais > 5) {
                cnpj += "000" + MakeInteger.getMax(9);
            } else {
                cnpj += "000" + MakeInteger.getMax(5);
            }
        } else {
            cnpj += "0001";
        }
        return cnpj + calcularDigitoVerificador(cnpj);
    }

    /**
     * Cria os dígitos verificadores do CNPJ com base nos 12
     * caracteres passados.
     *
     * @param str_cnpj 12 caractres numéricos do CNPJ
     * @return retorna Strings no formato de CNPJ válidas.
     */
    private static String calcularDigitoVerificador(String str_cnpj) {
        int soma = 0, aux, dig;
        String cnpj_calc = str_cnpj.substring(0, 12);
        char[] chr_cnpj = new char[str_cnpj.length() + 2];
        //char[] chr_cnpj = str_cnpj.toCharArray();
        System.arraycopy(str_cnpj.toCharArray(), 0, chr_cnpj, 0,
                str_cnpj.toCharArray().length);
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
        chr_cnpj[12] = cnpj_calc.charAt(cnpj_calc.length() - 1);
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

        return StringUtils.substring(cnpj_calc, 12);
    }
}
