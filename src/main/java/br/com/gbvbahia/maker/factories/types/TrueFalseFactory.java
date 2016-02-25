package br.com.gbvbahia.maker.factories.types;

import java.lang.reflect.Field;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

/**
 * Factory para classes anotadas com @AssertTrue e/ou @AssertFalse da JSR303.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class TrueFalseFactory implements ValueFactory {

  private TrueFalseFactory() {
    super();
  }

  /**
   * Nome da entidade que está tendo um atributo fabricado.
   */
  private String entityName;

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    this.entityName = entity.getClass().getSimpleName();
    if (field.getType().equals(Boolean.class)) {
      field.set(entity, this.valueToBoolean(field));
    } else if (field.getType().equals(boolean.class)) {
      field.set(entity, this.valueToBoolean(field).booleanValue());
    } else {
      throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoTrueFalse"));
    }
  }

  private Boolean valueToBoolean(Field field) {
    if (field.isAnnotationPresent(AssertTrue.class)) {
      return true;
    }
    if (field.isAnnotationPresent(AssertFalse.class)) {
      return false;
    }
    LogInfo.logDefaultValue(this.entityName, field, "TrueFalseFactory");
    return MakeInteger.getMax(2) == 2;
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    if (this.isBoolean(field)) {
      return true;
    }
    return false;
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {
    if (notification.isTestFinished()) {
      instance = null;
    }
  }

  /**
   * Verifica se o field é tratado com anotações booleanas da JSR303.
   *
   * @param field Field a ser avaliado.
   * @return True para possui anotação booleana False para não possui.
   */
  private boolean isBoolean(Field field) {
    if (field.isAnnotationPresent(AssertTrue.class) || field.isAnnotationPresent(AssertFalse.class)) {
      return true;
    }
    return false;
  }

  // ==============
  // Static control
  // ==============
  private static ValueFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return
   */
  public static synchronized ValueFactory getInstance() {
    if (instance == null) {
      instance = new TrueFalseFactory();
      NotifierTests.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
