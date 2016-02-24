package br.com.gbvbahia.maker.factories.types;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.persistence.OneToOne;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.complex.MakeString;
import br.com.gbvbahia.maker.types.primitives.MakeBoolean;
import br.com.gbvbahia.maker.types.primitives.MakeCharacter;

/**
 * Deve ser utilizado como <b>Factory Padrão</b>, para atributos somente anotados com @NotNull.<br>
 * Extende NumberFactory, por já ter implementação de valores default para algumas classes.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class DefaultFactory extends MaxMinFactory implements Observer {

  /**
   * For logging changes edit the log4j.properties inside src/test/resources
   */
  private static Log logger = LogFactory.getLog(DefaultFactory.class.getSimpleName());

  /**
   * Needs to recursion in MakeEntity.makeEntity(field.getType(), this.testName));
   */
  private String[] testName;

  /**
   * Cannot be instantiated outside.
   */
  private DefaultFactory() {}

  /**
   * Representa um map com chave class para a classe de objetos criados e os objetos criados como
   * valor, podendo reaproveitar em relacionamentos.
   */
  private Map<Class<? extends Object>, Object> mapOneToOne =
      new HashMap<Class<? extends Object>, Object>();

  private String className = this.getClass().getSimpleName();

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    this.mapOneToOne.put(entity.getClass(), entity);
    if (field.getType().equals(String.class)) {
      LogInfo.logDefaultValue(entity, field, this.className);
      field.set(entity, MakeString.getString(MakeString.MIN_LENGTH_DEFAULT,
          MakeString.MAX_LENGTH_DEFAULT, MakeString.StringType.LETTER));
      return;
    }
    try {
      super.makeValue(field, entity, testName);
    } catch (IllegalArgumentException ex) {
      if (MakeCharacter.isCharacter(field)) {
        this.valueToCharacter(field, entity);
      } else if (MakeBoolean.isBoolean(field)) {
        this.valueToBoolean(field, entity);
      } else if (this.isDate(field)) {
        LogInfo.logDefaultValue(entity, field, this.className);
        FuturePastFactory.getInstance().makeValue(field, entity, testName);
      } else if (!field.getType().isInterface() && this.hasFielDefaultConstructor(field)) {
        this.avoidCyclicReference(field, entity);
      } else if (field.getType().isInterface()) {
        logger.info(I18N.getMsg("internafeWarn", field.toGenericString()));
        logger.info(I18N.getMsg("factoryEspecializedWarn"));
        field.set(entity, null);
      } else if (!this.hasFielDefaultConstructor(field)) {
        logger.info(I18N.getMsg("constructorWarn", field.toGenericString()));
        logger.info(I18N.getMsg("factoryEspecializedWarn"));
        field.set(entity, null);
      } else {
        throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoDefault"));
      }
    }
  }

  /**
   * Check if the field has a default constructor.
   * 
   * @param field thats needs to be checked
   * @return true if has false if not
   */
  private boolean hasFielDefaultConstructor(Field field) {
    Constructor<?>[] constructors = field.getType().getConstructors();
    for (int i = 0; i < constructors.length; i++) {
      if (constructors[i].getTypeParameters().length == 0) {
        return true;
      }
    }
    return false;
  }

  private <T> void valueToBoolean(Field field, T entity) throws IllegalArgumentException,
      IllegalAccessException {
    LogInfo.logDefaultValue(entity, field, this.className);
    if (field.getType().equals(Boolean.class)) {
      field.set(entity, MakeBoolean.getBoolean());
    } else {
      field.set(entity, MakeBoolean.getBoolean().booleanValue());
    }
  }

  private <T> void valueToCharacter(Field field, T entity) throws IllegalArgumentException,
      IllegalAccessException {
    LogInfo.logDefaultValue(entity, field, this.className);
    if (field.getType().equals(Character.class)) {
      field.set(entity, MakeCharacter.getCharacter());
    } else {
      field.set(entity, MakeCharacter.getCharacter().charValue());
    }
  }

  /**
   * Evita referência ciclica, ou um loop infinito.
   *
   * @param <T> Tipo da entidade com fields definidos.
   * @param field Field a ser definido.
   * @param entity Entidade que está sendo definida.
   * @param makeRelationships Define se é para fazer objetos de relacionamentos.
   * @throws IllegalAccessException Se não conseguir definir valor em um field.
   * @throws IllegalArgumentException Se o field não puder ser construído devido algum
   *         relacionamento incorreto.
   */
  private <T> void avoidCyclicReference(Field field, T entity) throws IllegalAccessException,
      IllegalArgumentException {
    if (this.hasOneToOne(field, entity) && !this.mapOneToOne.containsKey(field.getType())) {
      this.mapOneToOne.put(entity.getClass(), entity);
    }
    if (this.hasOneToOne(field, entity) && this.mapOneToOne.containsKey(field.getType())) {
      field.set(entity, this.mapOneToOne.get(field.getType()));
    } else {
      if (!this.mapOneToOne.containsKey(field.getType())) {
        this.mapOneToOne
            .put(field.getType(), MakeEntity.makeEntity(field.getType(), this.testName));
      }
      field.set(entity, this.mapOneToOne.get(field.getType()));
    }
  }

  /**
   * Verifica se o field é do tipo Date ou Calendar.
   *
   * @param field Atributo da classe.
   * @return True para Calendar ou Date, false se não for.
   */
  private boolean isDate(Field field) {
    if (field.getType().equals(Date.class) || field.getType().equals(Calendar.class)) {
      return true;
    }
    return false;
  }

  /**
   * If field has a @OneToOne Annotation return true.<br>
   * If dont has @OneToOne will check if the type of the field has a field that is the same class of
   * entity.
   * 
   * @param field to check.
   * @return true if has false if not.
   */
  private <T> boolean hasOneToOne(Field field, T entity) {
    if (field.isAnnotationPresent(OneToOne.class)) {
      return true;
    }
    Field[] fields = field.getType().getDeclaredFields();
    for (Field subField : fields) {
      if (subField.getType().equals(entity.getClass())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void update(Observable notifierTests, Object notification) {
    Notification infoTest = (Notification) notification;
    if (infoTest.isTestFinished()) {
      this.mapOneToOne.clear();
    }
  }

  // ==============
  // Static control
  // ==============
  private static DefaultFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueFactory.
   * 
   * @return DefaultFactoy encapsulated in ValueFactory.
   */
  public static synchronized ValueFactory getInstance(String... testName) {
    loadInstance();
    instance.testName = testName;
    return instance;
  }

  /**
   * Load a instance of DefaultFacotry.
   */
  public static void loadInstance() {
    if (instance == null) {
      instance = new DefaultFactory();
      NotifierTests.getNotifyer().addObserver(instance);
    }
  }
}
