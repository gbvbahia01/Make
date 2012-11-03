/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.complex;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.MakeCharacter;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * O arquivo loren.properties possui uma quantidade limitada de
     * linhas loren, essa variável armazena o limite.
     */
    private static final int MAX_PROPERTIES_LOREN = 206;
    /**
     * Representa a quantidade de caracteres por linha no arquivo
     * loren.properties.
     */
    private static final int CARACTERES_LINE_LOREN = 100;
    /**
     * Quantidade maxima default de linhas buscadas no Loren, 2000
     * caracteres.
     */
    private static final int MAX_CARACTERES_LOREN = 20;
    /**
     * Quantidade minna default de linhas buscadas no Loren, 2000
     * caracteres.
     */
    private static final int MIN_CARACTERES_LOREN = 1;
    /**
     * Quantidade máxima de caracteres quando não informado pelo
     * desenvolvedor.
     */
    public static final int MAX_LENGTH_DEFAULT = 50;
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
     * Gera uma String no tamanho limitado solicidato. Se for somente
     * texto, LETTER, um texto Lorem ipsum será retornado.
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
        if (StringType.LETTER.equals(type)) {
            return getLoren(caracteres);
        }
        for (int i = 0; i < caracteres; i++) {
            switch (type) {
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
     * Retira dois valores do arquivo: nomes.properties, onde todos os
     * nomes ficam armazenados.
     *
     * @param posicao indica qual posição deverá ser trazido o nome,
     * de 0 até 1281.
     * @return java.lang.String referente a um nome.
     */
    private static String getMsg(final int posicao) {
        try {
            return ResourceBundle.getBundle("loren_make").getString("loren"
                    + posicao);
        } catch (MissingResourceException e) {
            e.printStackTrace();
            Logger.getLogger(I18N.class.getName()).log(Level.SEVERE,
                    "Maker: Loren não encontrada para {0}",
                    new Object[]{"loren" + posicao});
            return "loren" + posicao;
        }
    }

    /**
     * Retorna as strings solicitadas utilizando trechos do Lorem
     * ipsum, simulação de texto da indústria tipográfica e de
     * impressos. O trecho será aleatório, por ser utilizado partes de
     * 100 caracteres, a cada 100 caractres poderá haver repetição.
     *
     * @param caracteres Quantidade de caracteres. Se menor ou igual a
     * 0 será utilizado default, 50.
     * @return String tipo Lorem ipsum de no máximo
     */
    public static String getLoren(final int caracteres) {
        int linhas = setLines(caracteres);
        StringBuilder sb = new StringBuilder();
        for (; linhas > 0; linhas--) {
            sb.append(getMsg(MakeInteger.getMax(MAX_PROPERTIES_LOREN)));
        }
        return StringUtils.substring(sb.toString(), 0, caracteres);
    }

    /**
     * Define a quantidade de linhas que devem ser buscadas do arquivo
     * loren.properties.
     *
     * @param maxCaracteres Qantidade máxima de caracteres.
     * @return Quantidade de linhas necessárias.
     */
    private static int setLines(final int maxCaracteres) {
        int linhas;
        if (maxCaracteres <= 0) {
            linhas = MIN_CARACTERES_LOREN;
            LogInfo.logWarnInformation("MakeString", I18N.getMsg("minLorenError",
                    maxCaracteres, MIN_CARACTERES_LOREN));
        } else if (maxCaracteres > MAX_LENGTH_SUPPORTS) {
            linhas = MAX_CARACTERES_LOREN;
            LogInfo.logWarnInformation("MakeString", I18N.getMsg("minLorenError",
                    maxCaracteres, MAX_CARACTERES_LOREN * CARACTERES_LINE_LOREN));
        } else {
            linhas = maxCaracteres / CARACTERES_LINE_LOREN;
            if (linhas == 0) {
                linhas = 1;
            }
        }
        return linhas;
    }

    /**
     * Não pode ser instanciada.
     */
    private MakeString() {
    }
}