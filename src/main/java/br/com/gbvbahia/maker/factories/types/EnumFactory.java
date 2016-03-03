package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.factories.types.properties.exception.MakeCreationException;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import java.lang.reflect.Field;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class EnumFactory implements ValueFactory {

  private EnumFactory() {
    super();
  }

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    Object[] enumConstants = field.getType().getEnumConstants();
    int enumSize = enumConstants.length;
    if (enumSize <= 0) {
      throw new MakeCreationException(I18N.getMsg("enumInvalida", field.getType().getSimpleName()),
          new UnsupportedOperationException());
    }
    field.set(entity, enumConstants[MakeInteger.getIntervalo(0, enumSize - 1)]);
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    if (field.getType().isEnum()) {
      return true;
    }
    return false;
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {
    if (notification.isCreationFinished()) {
      instance = null;
    }
  }

  // ==============
  // Static control
  // ==============
  private static ValueFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return a instance of EnumFactory encapsulated in ValueFactory.
   */
  public static synchronized ValueFactory getInstance() {
    if (instance == null) {
      instance = new EnumFactory();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
