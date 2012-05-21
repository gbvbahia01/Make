/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types.common;

import java.lang.reflect.Field;

/**
 * Para cada anotação de validação uma Classe deverá ser criada e
 * implementar ValueFactory, tendo sua regra de negócio de acordo
 * com a anotação JSR303.
 * @since v.1 20/5/2012
 * @author Guilherme
 */
public interface ValueFactory {

    /**
     * @param <T> Generic que representa proprietário do campo. Por
     * exemplo: Classe carro tem um campo int rodas, o Field f seria
     * rodas e T seria a classe Carro que terá o campo definido.
     * @param f O campo que terá o valor definido.
     * @param toReturn Objeto que contém o Field.
     * @throws IllegalAccessException se no momento de execução não
     * houver acesso ao campo.
     * @throws IllegalArgumentException Se algum argumento anotado não
     * for válido.
     */
    <T> void makeValue(Field f, T entity)
            throws IllegalAccessException, IllegalArgumentException;
}
