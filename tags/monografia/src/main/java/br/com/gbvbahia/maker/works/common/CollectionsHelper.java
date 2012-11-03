/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works.common;

/**
 *
 * Utilizada para guardar a classe que será criada para a colção e a
 * quantidade máxima e mínima.
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
public class CollectionsHelper {

    /**
     * Tipo do objeto a ser criado.
     */
    private Class clazz;
    /**
     * Mínimo a ser criado.
     */
    private Integer min;
    /**
     * Máximo a ser criado.
     */
    private Integer max;

    /**
     * Tipo do objeto a ser criado.
     *
     * @return Class.
     */
    public Class getClazz() {
        return clazz;
    }

    /**
     * Máximo a ser criado.
     *
     * @return Integer.
     */
    public Integer getMax() {
        return max;
    }

    /**
     * Mínimo a ser criado.
     *
     * @return Integer.
     */
    public Integer getMin() {
        return min;
    }

    /**
     * Contrutor que definie os valores de auxilio para a geração dos
     * objetos a serem inseridos na coleção.
     *
     * @param clazzInCollection Tipo do objeto a ser criado para a
     * lista.
     * @param minInCollection Quantidade mánima a ser criada para ser
     * inserida na coleção.
     * @param maxInCollection Quantidade máxima a ser criada para ser
     * inserida na coleção.
     */
    public CollectionsHelper(final Class clazzInCollection,
            final Integer minInCollection,
            final Integer maxInCollection) {
        this.clazz = clazzInCollection;
        this.min = minInCollection;
        this.max = maxInCollection;
    }

    @Override
    public String toString() {
        return "CollectionsHelper{"
                + "clazz=" + clazz
                + ", min=" + min
                + ", max=" + max
                + '}';
    }
    
    
}
