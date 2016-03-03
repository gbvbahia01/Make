package br.com.gbvbahia.maker.factories.types.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>MakeName</b><br>
 * tag: isName<br>
 * Example: isName<br>
 * Create a person name.<br>
 * Works only with String.<br>
 * Two names are used to create the name.<br>
 * All names come from names_make.properties file
 *
 * @since v.1 10/06/2012
 * @author Guilherme
 */
public class MakeName implements ValueSpecializedFactory {

  /**
   * Key for this specialized factory.
   */
  public static final String KEY_PROPERTY = "isName";
  /**
   * Amount of names in names_make.properties.
   */
  private static final int MAX_PROPERTIES_NAMES = 1281;

  /**
   * Cannot be instantiated outside.
   */
  private MakeName() {
    super();
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("MakeName", I18N.getMsg("workValueMake", value));
    if (KEY_PROPERTY.equals(StringUtils.trim(value))) {
      return true;
    }
    LogInfo.logDebugInformation("MakeName", I18N.getMsg("notIsWork", "Name", value));
    return false;
  }

  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    return field.getType().equals(String.class);
  }

  @Override
  public <T> void makeValue(Field field, final T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    field.set(entity, getName());
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
   * It fetches two names from names_make.properties file.<br>
   *
   * @param position index of name must be fetched.
   * @return java.lang.String the name.
   */
  private static String getMsg(final int position) {
    try {
      return ResourceBundle.getBundle("names_make").getString("nome" + position);
    } catch (MissingResourceException e) {
      Logger.getLogger(I18N.class.getName()).log(Level.SEVERE,
          "Maker: Nome não encontrada para {0}", new Object[] {"nome" + position});
      return "nome" + position;
    }
  }

  /**
   * It fetches two names from names_make.properties file.<br>
   *
   * @return String the name.
   */
  public static String getName() {
    int firstName = MakeInteger.getMax(MAX_PROPERTIES_NAMES);
    int secondName;
    do {
      secondName = MakeInteger.getMax(MAX_PROPERTIES_NAMES);
    } while (secondName == firstName);
    return getMsg(firstName) + " " + getMsg(secondName);
  }

  // ==============
  // Static control
  // ==============
  private static ValueSpecializedFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return a instance for MakeName class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueSpecializedFactory getInstance() {
    if (instance == null) {
      instance = new MakeName();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
