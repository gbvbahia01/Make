package br.com.gbvbahia.maker.factories.types.works;

import java.lang.reflect.Field;
import java.util.Observable;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.MakeBoolean;
import br.com.gbvbahia.maker.types.primitives.MakeCharacter;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeFloat;

/**
 * Essa clase cria valores default para os fields informados.<br>
 * Primitivos recebem 0 e false.<br>
 * Objetos recebem null como valor.<br>
 * Data Type Default Value (for fields)<br>
 * byte 0<br>
 * short 0<br>
 * int 0<br>
 * long 0L<br>
 * float 0.0f<br>
 * double 0.0d<br>
 * char '\u0000'<br>
 * String (or any object) null<br>
 * boolean false
 * 
 * @author P9924903
 *
 */
public class DefaultValuesFactory implements ValueSpecializedFactory {

  /**
   * Use isDefault at xml field that you want this behavior happens.
   */
  public static final String KEY_PROPERTY = "isDefault";

  /**
   * Cannot be instantiated outside.
   */
  private DefaultValuesFactory() {}

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    if (!field.getType().isPrimitive()) {
      field.set(entity, null);
      return;
    }
    if (MakeDouble.isDouble(field) || MakeFloat.isFloat(field)) {
      field.set(entity, 0.0f);
      return;
    }
    if (MakeBoolean.isBoolean(field)) {
      field.set(entity, false);
      return;
    }
    if (MakeCharacter.isCharacter(field)) {
      field.set(entity, '\u0000');
      return;
    }
    field.set(entity, Byte.valueOf((byte) 0));
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    return true;
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("DefaultValuesFactory", I18N.getMsg("workValueMake", value));
    if (KEY_PROPERTY.equals(StringUtils.trim(value))) {
      return true;
    }
    LogInfo.logDebugInformation("DefaultValuesFactory",
        I18N.getMsg("notIsWork", KEY_PROPERTY, value));
    return false;
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void update(Observable notifierTests, Object notification) {}

  // ==============
  // Static control
  // ==============
  private static ValueSpecializedFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return
   */
  public static synchronized ValueSpecializedFactory getInstance() {
    if (instance == null) {
      instance = new DefaultValuesFactory();
      NotifierTests.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
