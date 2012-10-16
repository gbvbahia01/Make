/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.NumberFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import br.com.gbvbahia.maker.works.common.NumberHelper;
import br.com.gbvbahia.maker.works.common.ValueSpecializedFactory;
import br.com.gbvbahia.maker.works.execeptions.MakeWorkException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * Utilizada para criar valores limitados a um intervalo:<br>
 * testBetween1.br.com.gbvbahia.entityes.EntityBetweenTest.valor =
 * between{5,10}<br> A propriedade valor será definida entre 5 e 10.
 * Sendo os dois valores inclusos, podendo ser utilizados.
 *
 * @since v.1 18/06/2012
 * @author Guilherme
 */
public class MakeBetween implements ValueSpecializedFactory {

    /**
     * Guarda informações que serão necessárias para popular o field.
     */
    private NumberHelper info;
    /**
     * No arquivo make.properties deve estár definido no valor para o
     * field:
     * "between\\{[-\\d]+[\\.\\d]?[\\d]*,[-\\d]+[\\.\\d]?[\\d]*\\}".<br>
     * Regex: deve iniciar com <i>between{</i> conter números
     * positivos ou negativos (-) o caractere "." (ponto) separando as
     * casas decimais, se houver. Fecha com <i>}</i> .<br> Ex:
     * Inteiros: between{1,1}, between{10,100}, between{-10,10}<br>
     * Fracionádos: between{1.12,1.22} between{-10.33,100.40}
     * between{1,10.20}<br> Se o segundo número for menor que o
     * primeiro um erro será gerado.
     */
    public static final String KEY_PROPERTY = "between\\{[-\\d]+[\\.\\d]?[\\d]*,[-\\d]+[\\.\\d]?[\\d]*\\}";
    /**
     * Compilador regex que realiza a comparação.
     */
    private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTY);

    @Override
    public boolean workValue(final String value) {
        LogInfo.logDebugInformation("MakeBetween",
                I18N.getMsg("workValueMake", value));
        Matcher matcher = PATTERN.matcher(value);
        if (matcher.find()) {
            LogInfo.logDebugInformation("MakeBetween",
                    I18N.getMsg("isWork", "Between", value));
            LogInfo.logDebugInformation("MakeBetween", matcher.group());
            popularInfo(value);
            return true;
        } else {
            LogInfo.logDebugInformation("MakeBetween",
                    I18N.getMsg("notIsWork", "Between", value));
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

    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        for (MakeNumber number : NumberFactory.NUMBERS_FACTORYS) {
            if (number.isMyType(f)) {
                try {
                    number.insertValue(f, entity, info.getValue());
                } catch (NumberFormatException nf) {
                    nf.printStackTrace();
                    throw new MakeWorkException("MakeBetween", "NumberFormatException",
                            new String[]{info.toString()}, nf);
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
        String minMax = StringUtils.substringBetween(value, "{", "}");
        String min = minMax.split(",")[0];
        String max = minMax.split(",")[1];
        LogInfo.logDebugInformation("MakeBetween", "min:" + min
                + " max: " + max);
        info = new NumberHelper(min, max);
    }
}
