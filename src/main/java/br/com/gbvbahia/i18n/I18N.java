/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.i18n;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Classe responsável para manter todas as mensagens que serão
 * enviadas ao usuário.<br> Nenhum código deverá conter mensagens que
 * vão diretamente ao usuário.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public class I18N {

    /**
     * Construtor privado, classe não pode ser instânciada.
     */
    private I18N() {
    }

    /**
     * Retira o valor do arquivo:
     * br.com.convergeti.solida.utils.I18n.properties, onde todas as
     * mensagens que são enviadas ao usuário ficam armazenadas.
     *
     * @param chave java.lang.String chave da mensagem que será
     * enviada.
     * @return java.lang.String a ser utilizada para a chave passada,
     * caso ocorra algum erro ou a chave não exista, será retornado o
     * mesmo valor passado como parâmetro chave.
     */
    public static String getMsg(final String chave) {
        try {
            return ResourceBundle.getBundle("msg", Locale.getDefault(),
                    I18N.class.getClassLoader()).getString(chave);
        } catch (Exception e) {
            e.printStackTrace();
                Logger.getLogger(I18N.class.getName()).log(Level.INFO,
                        "Maker: Menssagem não encontrada para {0}", new Object[]{chave});
                return chave;
        }
    }

    /**
     * Retira o valor do arquivo:
     * br.com.convergeti.solida.utils.I18n.properties, onde todas as
     * mensagens que são enviadas ao usuário ficam armazenadas. Este
     * substitui todos {*} pela posição correspondente no vararg.
     *
     * @param msg Messagem do Resource.
     * @param var Variações da mensagem.
     * @return Menssagem formada.
     */
    public static String getMsg(final String msg, final Object... var) {
        String toReturn = getMsg(msg);
        if (var != null) {
            for (int i = 0; i < var.length; i++) {
                if (var[i] != null) {
                    toReturn = StringUtils.replace(toReturn,
                            "{" + i + "}", var[i].toString());
                }
            }
        }
        return toReturn;
    }
}
