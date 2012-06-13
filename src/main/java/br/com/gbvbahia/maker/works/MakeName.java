/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import br.com.gbvbahia.maker.works.common.ValuePropertiesFactory;
import java.lang.reflect.Field;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Cria nomes aleatórios, para que funcione deve ter o valor isName no
 * arquivo make.properties.
 *
 * @since v.1 10/06/2012
 * @author Guilherme
 */
public class MakeName implements ValuePropertiesFactory {

    /**
     * Como o propertie deve estár definido no valor: "isName".
     */
    public static final String KEY_PROPERTIE = "isName";
    /**
     * O arquivo nomes.properties possui uma quantidade limitada de
     * nomes, essa variável armazena o limite.
     */
    private static final int MAX_PROPERTIES_NAMES = 1281;

    @Override
    public boolean workValue(final String value) {
        LogInfo.logDebugInformation("MakeName",
                I18N.getMsg("workValueMake", value));
        if (KEY_PROPERTIE.equals(StringUtils.trim(value))) {
            return true;
        }
        LogInfo.logDebugInformation("MakeName",
                I18N.getMsg("notIsWork", "Name", value));
        return false;
    }

    @Override
    public <T> boolean isWorkWith(final Field f, final T entity) {
        return f.getType().equals(String.class);
    }

    @Override
    public <T> void makeValue(final Field f, final T entity,
            final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        f.set(entity, getName());
    }

    /**
     * Retira dois valores do arquivo: nomes.properties, onde todos os
     * nomes ficam armazenados.
     *
     * @param posicao indica qual posição deverá ser trazido o nome,
     * de 0 até 1281.
     * @return java.lang.String referente a um nome.
     */
    private static String getMsg(final int posicao) {
        try {
            return ResourceBundle.getBundle("nomes_make").getString("nome"
                    + posicao);
        } catch (MissingResourceException e) {
            e.printStackTrace();
            Logger.getLogger(I18N.class.getName()).log(Level.SEVERE,
                    "Maker: Nome não encontrada para {0}",
                    new Object[]{"nome" + posicao});
            return "nome" + posicao;
        }
    }

    /**
     * Retira dois valores do arquivo: nomes.properties, onde todos os
     * nomes ficam armazenados.
     *
     * @return String nome.
     */
    public static String getName() {
        int firstName = MakeInteger.getMax(MAX_PROPERTIES_NAMES);
        int secondName;
        do {
            secondName = MakeInteger.getMax(MAX_PROPERTIES_NAMES);
        } while (secondName == firstName);
        return getMsg(firstName) + " " + getMsg(secondName);
    }
}
