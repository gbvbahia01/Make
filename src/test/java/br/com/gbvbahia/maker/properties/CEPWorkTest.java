package br.com.gbvbahia.maker.properties;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.complex.MakeString;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class CEPWorkTest implements ValueSpecializedFactory {

  /**
   * Como o propertie deve estár definido no valor: "isCEP".
   */
  public static final String KEY_PROPERTIE = "isCEP";

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("CEPWorkTest", I18N.getMsg("workValueMake", value));
    if (KEY_PROPERTIE.equals(StringUtils.trim(value))) {
      return true;
    }
    LogInfo.logDebugInformation("CEPWorkTest", I18N.getMsg("notIsWork", "CEP", value));
    return false;
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    return field.getType().equals(String.class);
  }

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    field.set(entity, MakeString.getString(6, MakeString.StringType.NUMBER));
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {}
}
