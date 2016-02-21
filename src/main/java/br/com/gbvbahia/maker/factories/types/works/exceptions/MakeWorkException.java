/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types.works.exceptions;

/**
 * Classe para minipulações de exceção nos works.
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
public class MakeWorkException extends RuntimeException {

    /**
     * Oriem da exceção.
     */
    private String classOrigem;
    /**
     * chave no msg.properties.
     */
    private String pMsgProperties;
    /**
     * Preenche as lacunas no msg.properties {0}...
     */
    private String[] pVariations;

    /**
     * Construtor com informações da exceção.
     *
     * @param origem Nome da classe onde a exceção foi gerada.
     * @param msgProperties chave no msg.properties.
     * @param variations Variações na ordem de declaração {0} {1} no
     * properties, a ordem é importante.
     */
    public MakeWorkException(final String origem,
            final String msgProperties,
            final String[] variations) {
        this.classOrigem = origem;
        this.pMsgProperties = msgProperties;
        this.pVariations = variations;
    }

    /**
     * Construtor com informações da exceção. Não utiliza variações
     * {0}.
     *
     * @param origem Nome da classe onde a exceção foi gerada.
     * @param msgProperties chave no msg.properties.
     */
    public MakeWorkException(final String origem,
            final String msgProperties) {
        this.pMsgProperties = msgProperties;
    }

    /**
     * Construtor com informações da exceção. Não utiliza variações
     * {0}.
     *
     * @param origem Nome da classe onde a exceção foi gerada.
     * @param msgProperties chave no msg.properties.
     * @param cause Utilizado pela super classe, RuntimeException.
     */
    public MakeWorkException(final String origem,
            final String msgProperties,
            final Throwable cause) {
        super(cause);
        this.pMsgProperties = msgProperties;
    }

    /**
     * Construtor com informações da exceção.
     *
     * @param origem Nome da classe onde a exceção foi gerada.
     * @param msgProperties chave no msg.properties.
     * @param variations Variações na ordem de declaração {0} {1} no
     * properties, a ordem é importante.
     * @param cause Utilizado pela super classe, RuntimeException.
     */
    public MakeWorkException(final String origem,
            final String msgProperties,
            final String[] variations, final Throwable cause) {
        super(cause);
        this.pMsgProperties = msgProperties;
        this.pVariations = variations;
    }

    /**
     * chave no msg.properties.
     *
     * @return chave a ser buscada no msg.properties.
     */
    public String getMsgProperties() {
        return pMsgProperties;
    }

    /**
     * Preenche as lacunas no msg.properties {0}...
     *
     * @return String[] ou null se não houver.
     */
    public String[] getVariations() {
        return pVariations;
    }

    /**
     * Nome da classe onde a exceção foi gerada.
     *
     * @return Nome da classe.
     */
    public String getClassOrigem() {
        return classOrigem;
    }
}
