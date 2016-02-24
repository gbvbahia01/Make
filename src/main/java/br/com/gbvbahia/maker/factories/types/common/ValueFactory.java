/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */

package br.com.gbvbahia.maker.factories.types.common;

import java.lang.reflect.Field;
import java.util.Observer;

/**
 * Cada tipo de valor será criado por classes que imeplementam ValueFactory.<br>
 * Se não utilizar JSR303 será observado as regras definidas para o teste no arquivo make.properties <br>
 * Se usar a JSR303, para cada anotação de validação uma Classe deverá ser criada e implementar
 * ValueFactory, tendo sua regra de negócio de acordo com a anotação JSR303.
 *
 * @since v.1 20/5/2012
 * @author Guilherme
 */
public interface ValueFactory extends Observer {

  /**
   * Executa ação de criar valor(es) para ser(em) inseridos no field.
   *
   * @param <T> Generic que representa proprietário do campo. Por exemplo: Classe carro tem um campo
   *        int rodas, o Field f seria rodas e T seria a classe Carro que terá o campo definido.
   * @param testName Nome do teste declarado no properties. Pode ser utilizado caso haja recursão,
   *        chamar MakeEntity novamente.
   * @param f O campo que terá o valor definido.
   * @param entity Objeto que contém o Field.
   * 
   * @throws IllegalAccessException se no momento de execução não houver acesso ao campo.
   * @throws IllegalArgumentException Se algum argumento anotado não for válido.
   */
  <T> void makeValue(Field field, T entity, String... testName) throws IllegalAccessException,
      IllegalArgumentException;

  /**
   * Perguta do factory especifico se ele trabalha com o field passado.
   *
   * @param f Field a ter o valor definido.
   * @param entity Entidade que contém o field que receberá o valor.
   * @return true para se trabalha e false para não.
   */
  <T> boolean isWorkWith(Field field, T entity);
}
