/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types.common;

import java.lang.reflect.Field;

/**
 * Para cada anotação de validação uma Classe deverá ser criada e
 * implementar ValueFactory, tendo sua regra de negócio de acordo com
 * a anotação JSR303.
 *
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
     * @param makeRelationships True irá criar objetos para ManyToOne
     * ou OneToOne <b>que tenha anotação</b> javax.persistence.Entity.
     * @throws IllegalAccessException se no momento de execução não
     * houver acesso ao campo.
     * @throws IllegalArgumentException Se algum argumento anotado não
     * for válido.
     */
    <T> void makeValue(Field f, T entity, boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException;

    /**
     * Perguta do factory especifico se ele trabalha com o field
     * passado.
     *
     * @param f Field a ter o valor definido.
     * @return true para se trabalha e false para não.
     */
    <T> boolean isWorkWith(Field f, T entity);
}
