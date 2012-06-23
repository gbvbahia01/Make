/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.NumberFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import br.com.gbvbahia.maker.works.common.ValuePropertiesFactory;
import br.com.gbvbahia.maker.works.execeptions.MakeWorkException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @since 18/06/2012
 * @author Guilherme
 */
public class MakeIn implements ValuePropertiesFactory {

    /**
     * Guarda informações que serão necessárias para popular o field.
     */
    private List<String> inList;
    /**
     * No arquivo make.properties deve estár definido no valor para o
     * field: "in\\{.*\\}(\\[.+\\])?".<br> Regex: deve iniciar com
     * <i>in{</i>conter qualquer tipo de caractere. Fecha com <i>}</i>
     * podendo seguir com <i>[</i> conter qualquer caractere "."
     * (ponto) fechando com <i>]</i>. O que está entre <i>[?]</i> Será
     * utilizado como separador, se não houver valor explícito, será
     * considerado "," (virgula). <br> Ex: Inteiros: in{1,1}[,],
     * in{10|100}[|], in{-10ab10}[ab]<br> Fracionádos:
     * in{1.12,1.22}[,] in{-10.33,100.40} in{1!10.20}[!]<br>Letras:
     * in{abc|abg}[|] <br>Se o segundo número for menor que o primeiro
     * um erro será gerado.
     */
    public static final String KEY_PROPERTIE = "in\\{.*\\}(\\[.+\\])?";
    /**
     * Compilador regex que realiza a comparação.
     */
    private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTIE);

    @Override
    public boolean workValue(final String value) {
        LogInfo.logDebugInformation("MakeIn",
                I18N.getMsg("workValueMake", value));
        Matcher matcher = PATTERN.matcher(value);
        if (matcher.find()) {
            LogInfo.logDebugInformation("MakeIn",
                    I18N.getMsg("isWork", "In", value));
            LogInfo.logDebugInformation("MakeIn", matcher.group());
            popularInfo(value);
            return true;
        } else {
            LogInfo.logDebugInformation("MakeIn",
                    I18N.getMsg("notIsWork", "In", value));
            return false;
        }
    }

    @Override
    public <T> boolean isWorkWith(final Field f, final T entity) {
        for (MakeNumber number : NumberFactory.NUMBERS_FACTORYS) {
            if (number.isMyType(f)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
          for (MakeNumber number : NumberFactory.NUMBERS_FACTORYS) {
            if (number.isMyType(f)) {
                try {
                    number.insertValue(f, entity, inList.get(MakeInteger.getIntervalo(0, inList.size() -1)));
                } catch (NumberFormatException nf) {
                    nf.printStackTrace();
                    throw new MakeWorkException("MakeIn", "NumberFormatException",
                            new String[]{inList.toString()}, nf);
                }
            }
        }
    }

    /**
     * Popula info (CollectionsHelper) com informações necessárias
     * para criar e popular o List.
     *
     * @param value Valor declarado no make.properties.
     * @throws MakeWorkException Se não encontrar a classe informada
     * no properties ou conversão numérica não for possível.
     */
    private void popularInfo(final String value) {
        String in = StringUtils.substringBetween(value, "{", "}");
        String split = StringUtils.substringBetween(value, "[", "]");
        if (StringUtils.isBlank(split)) {
            split = ",";
        }
        inList = Arrays.asList(StringUtils.split(in, split));
        LogInfo.logDebugInformation("MakeIn", "split:" + split
                + " in: " + in);
    }
}
