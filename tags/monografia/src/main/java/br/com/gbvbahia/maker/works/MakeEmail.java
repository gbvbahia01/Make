/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.MakeCharacter;
import br.com.gbvbahia.maker.types.complex.MakeString;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import br.com.gbvbahia.maker.works.common.ValueSpecializedFactory;
import java.lang.reflect.Field;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Cria e-mails com formatos válidos, após @ existe uma lista de
 * possibilidades, antes do @ uma String entre 3 a 8 caracteres é
 * fabricada.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakeEmail implements ValueSpecializedFactory {

    /**
     * O arquivo nomes.properties possui uma quantidade limitada de
     * nomes, essa variável armazena o limite.
     */
    private static final int MAX_PROPERTIES_EMAILS = 39;
    /**
     * Como o propertie deve estár definido no valor: "isEmail".
     */
    public static final String KEY_PROPERTY = "isEmail";

    @Override
    public boolean workValue(final String value) {
        LogInfo.logDebugInformation("MakeEmail",
                I18N.getMsg("workValueMake", value));
        if (KEY_PROPERTY.equals(StringUtils.trim(value))) {
            return true;
        }
        LogInfo.logDebugInformation("MakeEmail",
                I18N.getMsg("notIsWork", "Email", value));
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
        f.set(entity, getEmail());
    }

    /**
     * Gera um e-mail válido aleatoriamente. O texto após @ é fixo em
     * algumas possibilidades.
     * <code>{"@hotmail.com", "@gmail.com","@ig.com.br",
     * "@amazon.com","@mycompany.com","@aol.com",
     * "@yahoo.com", "@bol.com.br", "@globo.com", "@nikko.jp",
     * "@uol.com.br"...};</code>
     *
     * @return Uma string no formato de email.
     */
    public static String getEmail() {
        int emaiPos = MakeInteger.getMax(MAX_PROPERTIES_EMAILS);
        String nameIni = MakeCharacter.getLetter().toString()
                + MakeCharacter.getLetter().toString()
                + MakeCharacter.getLetter().toString()
                + MakeString.getString(4, MakeString.StringType.NUMBER);
        String email = getMsg(emaiPos);
        return nameIni.toLowerCase().trim() + email;
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
            String email = ResourceBundle.getBundle("emails_make").getString("email"
                    + posicao);
            return email;
        } catch (MissingResourceException e) {
            e.printStackTrace();
            Logger.getLogger(I18N.class.getName()).log(Level.SEVERE,
                    "Maker: E-mail não encontrada para {0}",
                    new Object[]{"email" + posicao});
            return "email" + posicao;
        }
    }
}
