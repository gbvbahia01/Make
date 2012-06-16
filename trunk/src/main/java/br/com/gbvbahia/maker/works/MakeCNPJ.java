/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.string.MakeString;
import br.com.gbvbahia.maker.types.wrappers.MakeBoolean;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import br.com.gbvbahia.maker.works.common.ValuePropertiesFactory;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;

/**
 * Retorna uma string no formato de um CNPJ válido, em relação a
 * validação do digito verificador. Retorna 12 caracteres mais dois
 * digitos verificadores, totalizando quatorze caracteres. Não retorna
 * formatado, somente números, pontos,ífen e barra são excluídos.<br>
 * ATENÇÃO: Criado para facilitar testes de desenvolvimento de
 * software. <br> Para que funcione deve ter o valor isCNPJ no arquivo
 * make.properties.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakeCNPJ implements ValuePropertiesFactory {

    /**
     * Como o propertie deve estár definido no valor: "isCNPJ".
     */
    public static final String KEY_PROPERTIE = "isCNPJ";

    /**
     * Construtor padrão.
     */
    public MakeCNPJ() {
    }

    @Override
    public boolean workValue(final String value) {
        LogInfo.logDebugInformation("MakeCNPJ",
                I18N.getMsg("workValueMake", value));
        if (KEY_PROPERTIE.equals(StringUtils.trim(value))) {
            return true;
        }
        LogInfo.logDebugInformation("MakeCNPJ",
                I18N.getMsg("notIsWork", "CNPJ", value));
        return false;
    }

    @Override
    public <T> boolean isWorkWith(final Field f, final T entity) {
        return f.getType().equals(String.class);
    }

    @Override
    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        f.set(entity, getCNPJ());
    }

    /**
     * Gera um CNPJ aleatório mas válido, ou seja, o dígito
     * verificador correto.
     *
     * @return CNPJ válido.
     */
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
        return cnpj + digitCalculate(cnpj);
    }

    /**
     * Cria os dígitos verificadores do CNPJ com base nos 12
     * caracteres passados.
     *
     * @param strCnpj 12 caractres numéricos do CNPJ
     * @return retorna Strings no formato de CNPJ válidas.
     */
    private static String digitCalculate(final String strCnpj) {
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int nine = 9;
        final int ten = 10;
        final int cpfCaracteres = 11;
        final int twelve = 12;
        final int fourEight = 48;
        int soma = 0, dig;
        String cnpjCalc = strCnpj.substring(0, twelve);
        char[] chrCnpj = new char[strCnpj.length() + 2];
        System.arraycopy(strCnpj.toCharArray(), 0, chrCnpj, 0,
                strCnpj.toCharArray().length);

        /*
         * Primeira parte
         */
        for (int i = 0; i < four; i++) {
            if (chrCnpj[i] - fourEight >= 0 && chrCnpj[i] - fourEight <= nine) {
                soma += (chrCnpj[i] - fourEight) * (six - (i + 1));
            }
        }
        for (int i = 0; i < eight; i++) {
            if (chrCnpj[i + four] - fourEight >= 0 && chrCnpj[i + four] - fourEight <= nine) {
                soma += (chrCnpj[i + four] - fourEight) * (ten - (i + 1));
            }
        }
        dig = cpfCaracteres - (soma % cpfCaracteres);
        cnpjCalc += (dig == ten || dig == cpfCaracteres) ? "0" : Integer.toString(dig);

        /*
         * Segunda parte
         */
        chrCnpj[twelve] = cnpjCalc.charAt(cnpjCalc.length() - 1);
        soma = 0;
        for (int i = 0; i < five; i++) {
            if (chrCnpj[i] - fourEight >= 0 && chrCnpj[i] - fourEight <= nine) {
                soma += (chrCnpj[i] - fourEight) * (seven - (i + 1));
            }
        }
        for (int i = 0; i < eight; i++) {
            if (chrCnpj[i + five] - fourEight >= 0 && chrCnpj[i + five] - fourEight <= nine) {
                soma += (chrCnpj[i + five] - fourEight) * (ten - (i + 1));
            }
        }
        dig = cpfCaracteres - (soma % cpfCaracteres);
        cnpjCalc += (dig == ten || dig == cpfCaracteres) ? "0" : Integer.toString(dig);
        return StringUtils.substring(cnpjCalc, twelve);
    }
}
