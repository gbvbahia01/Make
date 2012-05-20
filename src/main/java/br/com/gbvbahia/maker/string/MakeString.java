/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.string;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.wrappers.MakeInteger;
import org.apache.commons.lang3.StringUtils;

/**
 * Cria Strings no perfil solicitado.
 *
 * @since 21/04/2012
 * @author Guilherme
 */
public final class MakeString {

    /**
     * Utilize para determinar o tipo de Caracteres que podem ser
     * retornados na String.<br> ALL: Todos os tipos de caracter.<br>
     * LETTER: Somente caracter tipo letrra a-zA-Z incluindo
     * espaço.<br> NUMBER: Somente numeros.<br> SYMBOL: Qualquer
     * caracter dentro de MakeCharacter.SYMBOLS<br>
     */
    public enum StringType {

        /**
         * LETTER: Somente caracter tipo letrra a-zA-Z <b>incluindo</b>
         * espaço.
         */
        LETTER,
        /**
         * NUMBER: Somente numeros.
         */
        NUMBER,
        /**
         * Qualquer caracter dentro de MakeCharacter.SYMBOLS.
         */
        SYMBOL,
        /**
         * Todos os tipos de caracter.
         */
        ALL
    };
    /**
     * Quantidade máxima de caracteres quando não informado pelo
     * desenvolvedor.
     */
    public static final int MAX_LENGTH_DEFAULT = 255;
    /**
     * Quantidade minina de caracteres quando não informado pelo
     * desenvolvedor.
     */
    public static final int MIN_LENGTH_DEFAULT = 1;

    /**
     * Gera uma String no tamanho limitado solicidato. Alterando de
     * Upper para Lower case entre as letras. Todo tipo de caracteres
     * poderá ser incluído na String gerada, utilize:<br>
     * getString(int caracteres, StringType type) para controlar os
     * tipos de caracteres.
     *
     * @param max Máximo de caracteres
     * @param min Minimo de caracteres
     * @return A sring no tamanho solicitado.
     */
    public static String getString(final int min, final int max) {
        if (min < 1) {
            throw new IllegalArgumentException(
                    I18N.getMsg("caractereToStringErro",
                    new Integer(min)));
        }
        int numero = MakeInteger.getIntervalo(min, max);
        return getString(numero, StringType.ALL);
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
    public static String getPassword(final int min, final int max) {
        if (min < 1) {
            throw new IllegalArgumentException(
                    I18N.getMsg("caractereToStringErro",
                    new Integer(min)));
        }
        int numero = MakeInteger.getIntervalo(min, max);
        return getPassword(numero);
    }

    /**
     * Gera uma String no tamanho limitado solicidato. Alterando de
     * Upper para Lower case entre as letras.
     *
     * @param caracteres Quantidade de caracteres.
     * @return A sring no tamanho solicitado.
     */
    public static String getString(final int caracteres,
            final StringType type) {
        if (caracteres < 1) {
            throw new IllegalArgumentException(
                    I18N.getMsg("caractereToStringErro",
                    new Integer(caracteres)));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caracteres; i++) {
            switch (type) {
                case LETTER:
                    letter(sb);
                    break;
                case NUMBER:
                    sb.append(MakeCharacter.getNumber());
                    break;
                case SYMBOL:
                    sb.append(MakeCharacter.getSymbols());
                    break;
                default:
                    all(sb);
            }
        }
        return sb.toString();
    }

    /**
     * Adiciona qualquer tipo de caractere na String, Simbolo,
     * numérico e letras.
     *
     * @param sb StringBuilder a ser inserido o caracter.
     */
    private static void all(final StringBuilder sb) {
        switch (MakeInteger.getMax(5)) {
            case 3:
                sb.append(MakeCharacter.getNumber());
                break;
            case 1:
                sb.append(MakeCharacter.getSymbols());
                break;
            default:
                letter(sb);
        }
    }

    /**
     * Adiciona letras na StringBuilder. Podendo ser Upper ou Lower e
     * espaços.
     *
     * @param sb StringBuilder a ser inserido o caracter.
     */
    private static void letter(final StringBuilder sb) {
        switch (MakeInteger.getMax(5)) {
            case 3:
                sb.append(MakeCharacter.getLetter().toString().toUpperCase());
                break;
            case 1:
                sb.append(" ");
                break;
            default:
                sb.append(MakeCharacter.getLetter().toString());
        }
    }

    /**
     * Gera uma senha com a quantidade de caracteres solicitado.
     *
     * @param caracteres Quantidade de caracteres solicitado.
     * @return Senha com numeros, letras e caracteres especiais
     * <code>(!,#,@,&,$)</code>
     */
    public static String getPassword(final int caracteres) {
        if (caracteres <= 0) {
            throw new IllegalArgumentException(
                    I18N.getMsg("caractereToStringErro",
                    new Integer(caracteres)));
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
            int letra1 = MakeInteger.getMax(a.length - 1);
            toReturn += a[letra1];
        }
        return toReturn;
    }

    /**
     * Gera um e-mail válido aleatoriamente. O texto após @ é fixo em
     * algumas possibilidades.
     * <code>{"@hotmail.com", "@gmail.com",
     * "@yahoo.com", "@bol.com.br", "@globo.com", "@nikko.jp",
     * "@uol.com.br"};</code>
     *
     * @return
     */
    public static String getEmail() {
        String[] emails = {"@hotmail.com",
            "@gmail.com", "@yahoo.com", "@bol.com.br", "@globo.com",
            "@nikko.jp", "@uol.com.br", "@saber.com.br"};
        int tamanho = MakeInteger.getIntervalo(3, 8);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            if (MakeInteger.getMax(2) % 2 == 0) {
                builder.append(MakeCharacter.getLetter());
            } else {
                builder.append(MakeCharacter.getNumber());
            }
        }
        int emailFim = MakeInteger.getMax(emails.length - 1);
        return StringUtils.replace(StringUtils.lowerCase(builder.toString()),
                " ", "_") + emails[emailFim];
    }

    /**
     * Não pode ser instanciada.
     */
    private MakeString() {
    }
}