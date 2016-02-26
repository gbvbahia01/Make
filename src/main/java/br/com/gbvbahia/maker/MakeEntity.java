package br.com.gbvbahia.maker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * Style code is follow the google style:<br>
 * http://google.github.io/styleguide/javaguide.html#s3.3.3-import-ordering-and-spacing
 * 
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEntity {

  /**
   * For logging changes edit the log4j.properties inside src/test/resources
   */
  private static Log logger = LogFactory.getLog(MakeEntity.class.getSimpleName());

  private static Integer counter = 0;

  /**
   * Cria a entidade com atributos populados.<br>
   * Se tiver anotaçoes da especificação JSR303 (Hibernate Validator) a mesma será respeidata se não
   * utilize o properties para definir os ranges de valores.<br>
   * Coleções, Set, List e Map não serão criados. Relacionamentos com outras classes serão
   * ignorados, null será settado nesses objetos.<br>
   *
   * @param entityParam Classe a ter os atributos gerados.
   * @param testName O nome do teste para ser utilizado ao ler o arquivo make.properties
   */
  public static <T> T makeEntity(final Class<T> entityParam, String... testName) {
    LogInfo.logMakeStartDebug(MakeEntity.class.getSimpleName(), entityParam);
    try {
      Factory.configureFactories(testName);
      notifyStarted(testName);
      T entityReturn = entityParam.newInstance();
      prepareValue(entityParam, entityReturn, testName);
      LogInfo.logMakeEndDebug(MakeEntity.class.getSimpleName(), entityParam);
      return entityReturn;
    } catch (InstantiationException ex) {
      logger.error(I18N.getMsg("instantiationException", entityParam.getName()), ex);
      throw new RuntimeException(ex);
    } catch (IllegalAccessException ex) {
      logger.error(I18N.getMsg("illegalAccessException", entityParam.getName()), ex);
      throw new RuntimeException(ex);
    } finally {
      notifyEnded(testName);
    }
  }

  private static <T> void prepareValue(final Class<T> entityParam, T entityReturn,
      String... testName) throws IllegalAccessException {
    for (Field field : entityReturn.getClass().getDeclaredFields()) {
      boolean accessField = field.isAccessible();
      try {
        field.setAccessible(true);
        try {
          ValueFactory valueFactory = Factory.getFactory(field, entityParam);
          valueFactory.makeValue(field, entityReturn, testName);
        } catch (IllegalArgumentException e) {
          LogInfo.logFieldNull(MakeEntity.class.getSimpleName(), field);
        }
        LogInfo.logFieldDebug(MakeEntity.class.getSimpleName(), field, entityReturn);
      } finally {
        field.setAccessible(accessField);
      }
    }
  }

  private static void notifyStarted(String... testName) {
    if (counter++ == 0) {
      NotifierTests.getNotifyer().notifyTestBegin(testName);
    } else {
      NotifierTests.getNotifyer().notifyTestRecursionBegin(counter - 1, testName);
    }
  }

  private static void notifyEnded(String... testName) {
    if (--counter == 0) {
      NotifierTests.getNotifyer().notifyTestEnd(testName);
    } else {
      NotifierTests.getNotifyer().notifyTestRecursionEnd(counter, testName);
    }
  }

  /**
   * Utilize para criar uma lista de entidades.
   *
   * @param <T> Tipo da entidade solicitada.
   * @param entity Classe da entidade solicitada.
   * @param amount Quantidade de entidades criadas dentro da lista.
   * @return Lista com a quantidade de entidades solicitadas em amount.
   */
  public static <T> List<T> makeEntities(Class<T> entity, int amount, String... testName) {
    if (amount < 1) {
      throw new IllegalArgumentException(I18N.getMsg("qutdadeEntityInvalida", amount));
    }
    List<T> toReturn = new ArrayList<T>();
    for (int i = 0; i < amount; i++) {
      toReturn.add(makeEntity(entity, testName));
    }
    return toReturn;
  }
}
