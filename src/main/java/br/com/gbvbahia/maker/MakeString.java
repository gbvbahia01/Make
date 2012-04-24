/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

/**
 * Cria Strings no perfil solicitado.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public final class MakeString {

    /**
     * Gera uma String no tamanho limitado solicidato. Alterando de
     * Upper para Lower case entre as letras.
     *
     * @param max Máximo de caracteres
     * @param min Minimo de caracteres
     * @return A sring no tamanho solicitado.
     */
    public static String getString(final int min, final int max) {
        int numero = MakeInteger.getIntervalo(min, max);
        return getString(numero);
    }

    /**
     * Gera uma senha com a quantidade de caracteres limitado ao
     * solicitado.
     *
     * @param min Quantidade minima de caracteres solicitado.
     * @param max Quantidade maxima de caracteres solicitado.
     * @return Senha com numeros, letras e caracteres especiais
     * <code>(!,#,@,&,$)</code>
     */
    public static String gerarSenha(final int min, final int max) {
        int numero = MakeInteger.getIntervalo(min, max);
        return gerarSenha(numero);
    }

    /**
     * Gera uma String no tamanho limitado solicidato. Alterando de
     * Upper para Lower case entre as letras.
     *
     * @param caracteres Quantidade de caracteres.
     * @return A sring no tamanho solicitado.
     */
    public static String getString(final int caracteres) {
        StringBuilder sb = new StringBuilder();
        String[] mai = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "X", "Z", " "};
        for (int i = 0; i < caracteres; i++) {
            if (i % 2 == 0) {
                sb.append(mai[MakeInteger.getMax(mai.length)]);
            } else {
                sb.append(mai[MakeInteger.getMax(mai.length)].toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * Gera uma senha com a quantidade de caracteres solicitado.
     *
     * @param caracteres Quantidade de caracteres solicitado.
     * @return Senha com numeros, letras e caracteres especiais
     * <code>(!,#,@,&,$)</code>
     */
    public static String gerarSenha(final int caracteres) {
        if (caracteres <= 0) {
            throw new IllegalArgumentException(
                    I18N.getMsg("caracteresMaxErro"));
        }
        String toReturn = "";
        for (int i = 0; i < caracteres; i++) {
            String[] a = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                "!", "#", "@", "&", "$",
                "A", "a", "B", "b", "C", "c", "D", "d", "E",
                "e", "F", "f", "G", "g", "H", "h", "I", "i", "J", "j",
                "K", "k", "L", "l", "Z", "z",
                "K", "k", "L", "l", "M", "m", "N", "n", "O",
                "o", "P", "p", "Q", "q", "R", "r", "S", "s", "T", "t",
                "U", "u", "V", "v", "X", "x", "Z", "z"};
            int letra1 = MakeInteger.getMax(a.length);
            toReturn += a[letra1];
        }
        return toReturn;
    }

    /**
     * Gera um e-mail válido aleatoriamente. O texto após @ é fixo em
     * algumas possibilidades. <code>{"@hotmail.com", "@gmail.com",
     * "@yahoo.com", "@bol.com.br", "@globo.com", "@nikko.jp",
     * "@uol.com.br"};</code>
     *
     * @return
     */
    public static String gerarEmail() {
        String[] emails = {"@hotmail.com",
            "@gmail.com", "@yahoo.com", "@bol.com.br", "@globo.com",
            "@nikko.jp", "@uol.com.br", "@saber.com.br"};
        int tamanho = MakeInteger.getIntervalo(3, 8);
        String ini = MakeString.getString(tamanho);
        int emailFim = MakeInteger.getMax(emails.length);
        return StringUtils.replace(StringUtils.lowerCase(ini), " ", "") + emails[emailFim];
    }

    /**
     * Não pode ser instanciada.
     */
    private MakeString() {
    }
}
