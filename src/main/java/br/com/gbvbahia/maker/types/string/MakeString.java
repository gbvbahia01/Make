/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.string;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.lang.reflect.Field;
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
         * LETTER: Somente caracter tipo letrra a-zA-Z
         * <b>incluindo</b> espaço.
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
     * Se a solicitação de criação de String for maior que este valor
     * uma IllergalArgumentException será lançada.
     */
    public static final int MAX_LENGTH_SUPPORTS = 4000;

    /**
     * Gera uma String (Upper ou Lower) no tamanho limitado
     * solicidato. Todo tipo de caracter poderá ser incluído na String
     * gerada, utilize:<br> getString(int caracteres, StringType type)
     * para controlar os tipos de caracter se desejado.
     *
     * @param max Máximo de caracteres
     * @param min Minimo de caracteres
     * @return A sring no tamanho solicitado.
     */
    public static String getString(final int min, final int max,
            StringType type) {
        if (min < 0) {
            throw new IllegalArgumentException(
                    I18N.getMsg("caractereToStringErro",
                    new Integer(min)));
        }
        int numero = MakeInteger.getIntervalo(min, max);
        return getString(numero, type);
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
        if (checkCaracters(caracteres)) {
            return "";
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
     * Valida o tamanho da String podendo lançar uma
     * IllegalArgumentException se a String for muito grande ou se a
     * solicitação for muito grande.
     *
     * @param caracteres Quantidade de caracteres que a String deve
     * ter.
     * @return True para String vazia, false para uma String válida
     * não vazia.
     * @throws IllegalArgumentException Se a quantidade solicitada for
     * inválida.
     */
    private static boolean checkCaracters(final int caracteres) throws IllegalArgumentException {
        if (caracteres > MAX_LENGTH_SUPPORTS) {
            throw new IllegalArgumentException(I18N.getMsg("sizeLenghFatal",
                    MAX_LENGTH_SUPPORTS));
        } else if (caracteres < 0) {
            throw new IllegalArgumentException(
                    I18N.getMsg("caractereToStringErro",
                    new Integer(caracteres)));
        } else if (caracteres == 0) {
            return true;
        }
        return false;
    }

    /**
     * Não pode ser instanciada.
     */
    private MakeString() {
    }
}