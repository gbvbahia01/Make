/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.properties.ValuePropertiesFactory;
import br.com.gbvbahia.maker.types.string.MakeString;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;

/**
 * Cria uma String que passa no teste de validação de CPF, nove
 * caracteres mais dois digitos verificadores.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakeCPF implements ValuePropertiesFactory {

    public static final String keyPropertie = "isCPF";

    /**
     * Construtor padrão.
     */
    public MakeCPF() {
    }

    /**
     * Retorna uma string no formato de um CPF válido, em relação a
     * validação do digito verificador. Retorna nove caracteres mais
     * dois digitos verificadores, totalizando onze caracteres. Não
     * retorna formatado, somente números, pontos e ífen são
     * excluídos. <br> ATENÇÃO: Criado para facilitar testes de
     * desenvolvimento de software.
     *
     * @return String no formato de um CPF válido.
     */
    public static String getCPF() {
        String noveDigitos = MakeString.getString(9,
                MakeString.StringType.NUMBER);
        return noveDigitos + calcularDigitoVerificador(noveDigitos);
    }

    @Override
    public <T> void makeValue(final Field f, final T entity,
            final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        f.set(entity, getCPF());
    }

    @Override
    public boolean workValue(final String value) {
        LogInfo.logInfoInformation("MakeCPF",
                I18N.getMsg("workValueMakeCPF", value));
        if (keyPropertie.equals(StringUtils.trim(value))) {
            return true;
        }
        LogInfo.logInfoInformation("MakeCPF",
                I18N.getMsg("notIsCPF", value));
        return false;
    }

    /**
     * Irá avaliar se o tipo do Field é trabalhado pelo mesmo, aqui
     * deve ser String.
     *
     * @param f Field a ter o valor definido.
     * @return True para String False para o resto.
     */
    @Override
    public <T> boolean isWorkWith(Field f, T entity) {
        return f.getType().equals(String.class);
    }

    /**
     * Com base no cpf passado é calculado o dígito verificador.
     *
     * @param cpf Somente números.
     * @return duas Strings numéricas referente ao dígito verificador.
     */
    private static String calcularDigitoVerificador(String cpf) {
        Integer[] valores = new Integer[cpf.length()];
        Integer[] cpfPrimeiroDigito = new Integer[cpf.length() + 1];
        String[] values = cpf.split("");
        for (int i = 1, j = 0; i < values.length && j < values.length; i++, j++) {
            valores[j] = new Integer(values[i]);
        }
        int calculoBase = 10;
        int calculoBaseDv2 = 11;
        Integer dv1 = new Integer(0);
        Integer dv2 = new Integer(0);
        String digitoVerificador = "";
        for (int i = 0; i < valores.length; i++) {
            dv1 += valores[i] * calculoBase;
            calculoBase--;
        }
        if ((dv1 % 11) < 2) {
            dv1 = new Integer(0);
        } else {
            dv1 = new Integer(11 - (dv1 % 11));
        }
        System.arraycopy(valores, 0, cpfPrimeiroDigito, 0, valores.length);
        cpfPrimeiroDigito[cpfPrimeiroDigito.length - 1] = dv1;
        for (int i = 0; i < cpfPrimeiroDigito.length; i++) {
            dv2 += cpfPrimeiroDigito[i] * calculoBaseDv2;
            calculoBaseDv2--;
        }
        if ((dv2 % 11) < 2) {
            dv2 = new Integer(0);
        } else {
            dv2 = new Integer(11 - (dv2 % 11));
        }
        digitoVerificador = digitoVerificador + dv1 + dv2;
        return digitoVerificador;
    }
}
