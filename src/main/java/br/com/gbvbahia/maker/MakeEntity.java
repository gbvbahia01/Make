package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.factories.types.properties.exception.MakeCreationException;
import br.com.gbvbahia.maker.log.LogInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Create a object populated with random values.<br>
 * The random values can be controlled with some ways.<br>
 * A range of values can be determined.<br>
 * A creator value class can be used to create specific values.<br>
 * Type of value can be determined, like a String field that must populated with a random person
 * names or a number that represent a person age between 18 - 100 and more.<br>
 * If you use a JSR303 specification, like hibernate validator, in all fields of your entity objects
 * you only need to set the property JSR303 to 'read' in XML setup file.<br>
 * The values that you want to change or get more control you can apply more rule in the XML setup
 * file.<br>
 * Make is programmed to use the make.xml file as setup file but you can change this if you want
 * another name or create more than one configuration.<br>
 * To create your object you use the static method MakeEntity.make(TheObjectYouWant.class);<br>
 * The class parameter must follow one rule: it is necessary to have a default constructor.<br>
 * 
 * <p>
 * Style code is follow the google style:<br>
 * http://google.github.io/styleguide/javaguide.html#s3.3.3-import-ordering-and-spacing<br>
 * 
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEntity {

  private static Integer counter = 0;

  /**
   * Make the object and its fields following the rules by XML setup file.<br>
   * The object parameter class must have a default constructor. The fields that don't have default
   * constructor will be set null as value.<br>
   * To have more control in some range values to set in one or more fields you must use the XML
   * setup file.<br>
   * Make uses by default make.xml setup file like the src/test/resources/make.xml<br>
   * 
   * @param objectParam the object that will be created.
   * @param testName the names of tests in XML setup file that have the tests rules.
   * @return the new stance of objectParam with fields populated.
   */
  public static <T> T make(final Class<T> objectParam, String... testName) {
    LogInfo.logMakeStartDebug(MakeEntity.class.getSimpleName(), objectParam);
    try {
      Factory.configureFactories(testName);
      notifyStarted(testName);
      T entityReturn = objectParam.newInstance();
      prepareValue(objectParam, entityReturn, testName);
      return entityReturn;
    } catch (InstantiationException ex) {
      throw new MakeCreationException(I18N.getMsg("instantiationException", objectParam.getName()),
          ex);
    } catch (IllegalAccessException ex) {
      throw new MakeCreationException(I18N.getMsg("illegalAccessException", objectParam.getName()),
          ex);
    } finally {
      LogInfo.logMakeEndDebug(MakeEntity.class.getSimpleName(), objectParam);
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

  /**
   * Need to be called after Factory.configureFactories(testName) because all factories are listener
   * of stage test.
   * 
   * @param testName the tests names that will be used.
   */
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
   * Make the object and its fields following the rules by XML setup file.<br>
   * The object parameter class must have a default constructor. The fields that don't have default
   * constructor will be set null as value.<br>
   * To have more control in some range values to set in one or more fields you must use the XML
   * setup file.<br>
   * Make uses by default make.xml setup file like the src/test/resources/make.xml<br>
   * 
   * @param objectParam the object that will be created.
   * @param amount of objectParam will be created.
   * @param testName the names of tests in XML setup file that have the tests rules.
   * @return a list with the new stances of objectParam with fields populated.
   */
  public static <T> List<T> makes(Class<T> objectParam, int amount, String... testName) {
    if (amount < 1) {
      throw new IllegalArgumentException(I18N.getMsg("qutdadeEntityInvalida", amount));
    }
    List<T> toReturn = new ArrayList<T>();
    for (int i = 0; i < amount; i++) {
      toReturn.add(make(objectParam, testName));
    }
    return toReturn;
  }
}
