/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import br.com.gbvbahia.maker.works.common.CollectionsHelper;
import br.com.gbvbahia.maker.works.common.ValueSpecializedFactory;
import br.com.gbvbahia.maker.works.execeptions.MakeWorkException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * No arquivo make.properties deve estár definido no valor para o
 * field: "[isList{][a-zA-Z0-9\\.]+[}][\\[][\\d]+,[\\d]+[\\]]".<br>
 * Regex: deve iniciar com <i>isList{</i> contendo letras maiúsculas
 * ou minúsculas, números e o caractere "." (ponto). Fecha com
 * <i>}</i> e deve terminar com <i>[</i> qualquer número "," (virgula)
 * qualquer número <i>]</i>.<br> Ex:
 * test1.br.com.meuprojeto.ClasseCriada.fieldNome =
 * isList{br.com.meupacote.MinhaClasse}[1,4]<br> Isso diz: O fieldNome
 * é um java.util.List contendo objetos MinhasClasse com list.size()
 * entre 1 até 4.<br> Atenção: o objeto MinhaCLasse, que irá para
 * dentro do List, deverá possuir um construtor padrão, sem
 * argumentos. Seus objetos primitivos ou Wrappers (incluíndo Strings)
 * serão criados pelo Make, os valores complexos (objetos não
 * primitivos nem wrappers) ou valores limitados que não tem seus
 * limites anotados pela JSR303 deverão ser mapeados pelo arquivo
 * make.properties.
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
public class MakeList implements ValueSpecializedFactory {

    /**
     * Guarda informações que serão necessárias para popular a lista;
     */
    private CollectionsHelper info;
    /**
     * No arquivo make.properties deve estár definido no valor para o
     * field:
     * "[isList{][a-zA-Z0-9\\.]+[}][\\[][\\d]+,[\\d]+[\\]]".<br>
     * Regex: deve iniciar com <i>isList{</i> contendo letras
     * maiusculas ou minusculas, numéros e o caractere "." (ponto).
     * Fecha com <i>}</i> e deve terminar com <i>[</i> qualquer número
     * "," (virgula) qualquer número <i>]</i>.
     */
    public static final String KEY_PROPERTIE = "isList\\{[a-zA-Z0-9\\.]+\\}[\\[][\\d]+,[\\d]+[\\]]";
    /**
     * Compilador regex que realiza a comparação.
     */
    private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTIE);

    @Override
    public boolean workValue(final String value) {
        LogInfo.logDebugInformation("MakeList",
                I18N.getMsg("workValueMake", value));
        Matcher matcher = PATTERN.matcher(value);
        if (matcher.find()) {
            LogInfo.logDebugInformation("MakeList",
                    I18N.getMsg("isWork", "List", value));
            LogInfo.logDebugInformation("MakeList", matcher.group());
            popularInfo(value);
            return true;
        } else {
            LogInfo.logDebugInformation("MakeList",
                    I18N.getMsg("notIsWork", "List", value));
            return false;
        }
    }

    @Override
    public <T> boolean isWorkWith(final Field f, final T entity) {
        return f.getType().equals(java.util.List.class);
    }

    @Override
    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        List toSet = new ArrayList(MakeEntity.makeEntitys(testName,
                info.getClazz(),
                MakeInteger.getIntervalo(info.getMin(), info.getMax()),
                makeRelationships));
        f.set(entity, toSet);
    }

    /**
     * Popula info (CollectionsHelper) com informações necessárias
     * para criar e popular o List.
     *
     * @param value Valor declarado no make.properties.
     * @throws MakeWorkException Se não encontrar a classe
     * informada no properties ou conversão numérica não for possível.
     */
    private void popularInfo(final String value) {
        String clazz = StringUtils.substringBetween(value, "isList{", "}");
        String minMax = StringUtils.substringBetween(value, "[", "]");
        String min = minMax.split(",")[0];
        String max = minMax.split(",")[1];
        LogInfo.logDebugInformation("MakeList", "Class: " + clazz
                + " min:" + min + " max: " + max);
        try {
            info = new CollectionsHelper(Class.forName(clazz),
                    new Integer(min), new Integer(max));
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
            throw new MakeWorkException("MakeList", "ClassNotFoundException",
                    new String[]{clazz}, ce);
        } catch (NumberFormatException nf) {
            nf.printStackTrace();
            throw new MakeWorkException("MakeList", "NumberFormatException",
                    new String[]{minMax}, nf);
        }
    }
}
