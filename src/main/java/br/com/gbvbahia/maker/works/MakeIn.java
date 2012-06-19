/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.maker.works.common.ValuePropertiesFactory;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 *
 * @since 18/06/2012
 * @author Guilherme
 */
public class MakeIn implements ValuePropertiesFactory {

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> boolean isWorkWith(final Field f, final T entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
