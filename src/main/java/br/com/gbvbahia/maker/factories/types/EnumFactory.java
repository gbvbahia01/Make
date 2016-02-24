package br.com.gbvbahia.maker.factories.types;

import java.lang.reflect.Field;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

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
      throw new UnsupportedOperationException(I18N.getMsg("enumInvalida", field.getType()
          .getSimpleName()));
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
  public void testStageChanged(Notification notification) {
    if (notification.isTestFinished()) {
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
   * @return
   */
  public static synchronized ValueFactory getInstance() {
    if (instance == null) {
      instance = new EnumFactory();
      NotifierTests.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
