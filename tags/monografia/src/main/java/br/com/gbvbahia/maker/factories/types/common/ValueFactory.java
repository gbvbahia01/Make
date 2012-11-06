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
     * Executa a ção de criar valor(es) para ser(em) inseridos no
     * field.
     *
     * @param <T> Generic que representa proprietário do campo. Por
     * exemplo: Classe carro tem um campo int rodas, o Field f seria
     * rodas e T seria a classe Carro que terá o campo definido.
     * @param testName Nome do teste declarado no properties. Pode ser
     * utilizado caso haja recursão, chamar MakeEntity novamente.
     * @param f O campo que terá o valor definido.
     * @param entity Objeto que contém o Field.
     * @param makeRelationships True irá criar objetos para ManyToOne
     * ou OneToOne <b>que tenha anotação</b> javax.persistence.Entity.
     * @throws IllegalAccessException se no momento de execução não
     * houver acesso ao campo.
     * @throws IllegalArgumentException Se algum argumento anotado não
     * for válido.
     */
    <T> void makeValue(String testName, Field f, T entity,
            boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException;

    /**
     * Perguta do factory especifico se ele trabalha com o field
     * passado.
     *
     * @param f Field a ter o valor definido.
     * @param entity Entidade que contém o field que receberá o valor.
     * @return true para se trabalha e false para não.
     */
    <T> boolean isWorkWith(Field f, T entity);
}