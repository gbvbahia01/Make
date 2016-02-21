package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.complex.MakeString;
import br.com.gbvbahia.maker.types.primitives.MakeBoolean;
import br.com.gbvbahia.maker.types.primitives.MakeCharacter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.OneToOne;

/**
 * Deve ser utilizado como <b>Factory Padrão</b>, para atributos somente anotados com @NotNull.<br>
 * Extende NumberFactory, por já ter implementação de valores default para algumas classes.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class DefaultFactory extends MaxMinFactory {

  /**
   * For logging changes edit the log4j.properties inside src/test/resources
   */
  private static Log logger = LogFactory.getLog(DefaultFactory.class.getSimpleName());

  /**
   * Necessário para recursividade.
   */
  private String[] testName;

  /**
   * O nome do teste não é obrigatório, se não informado será utilizado as propriedades da JSR303 e
   * as default do Make.
   *
   * @param testName Nome do testes no properties.
   */
  public DefaultFactory(String[] testName) {
    this.testName = testName;
  }

  /**
   * Representa um map com chave class para a classe de objetos criados e os objetos criados como
   * valor, podendo reaproveitar em relacionamentos.
   */
  private static Map<Class<? extends Object>, Object> criados =
      new HashMap<Class<? extends Object>, Object>();
  /**
   * Garante a quantidade de recursividade, se passar de MAX_RECURSIVE irá reaproveitar objetos.
   */
  private static int recursiveCount = 0;
  private static final int MAX_RECURSIVE = 3;
  private String className = this.getClass().getSimpleName();

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    criados.put(entity.getClass(), entity);
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
        new FuturePastFactory().makeValue(field, entity, testName);
      } else if (!field.getType().isInterface() && this.hasFielDefaultConstructor(field)) {
        this.avoidCyclicReference(field, entity);
        recursiveCount--;
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
   * @param field
   * @return
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

  private <T> void valueToBoolean(Field field, T entity)
      throws IllegalArgumentException, IllegalAccessException {
    LogInfo.logDefaultValue(entity, field, this.className);
    if (field.getType().equals(Boolean.class)) {
      field.set(entity, MakeBoolean.getBoolean());
    } else {
      field.set(entity, MakeBoolean.getBoolean().booleanValue());
    }
  }

  private <T> void valueToCharacter(Field field, T entity)
      throws IllegalArgumentException, IllegalAccessException {
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
  private <T> void avoidCyclicReference(Field field, T entity)
      throws IllegalAccessException, IllegalArgumentException {
    if (((recursiveCount > MAX_RECURSIVE) || this.isMappedBy(field))
        && criados.containsKey(field.getType())) {
      field.set(entity, criados.get(field.getType()));
      LogInfo.logInfoInformation(this.className, I18N.getMsg("possivelReferenciaCiclica",
          field.getType().getSimpleName(), entity.getClass().getSimpleName()));
    } else {
      recursiveCount++;
      criados.put(field.getType(), MakeEntity.makeEntity(field.getType(), this.testName));
      field.set(entity, criados.get(field.getType()));
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
   * Verifica se existe o mapeamento mappedBy da JPA, que significa que o objeto do outro lado que é
   * proprietário do relacionamento.
   *
   * @param field Field a ser populado.
   * @return True para se tiver atributo mappedBy false para não.
   */
  private boolean isMappedBy(Field field) {
    if (field.isAnnotationPresent(OneToOne.class)) {
      return field.getAnnotation(OneToOne.class).mappedBy() != null;
    } else {
      return false;
    }
  }
}
