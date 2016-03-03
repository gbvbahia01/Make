package br.com.gbvbahia.maker.factories.types;

import java.lang.reflect.Field;

import javax.validation.constraints.Size;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.complex.MakeString;

/**
 * Cria String com base na anotação @Size (javax.validation.constraints.Size) da JSR303.<br>
 * <b> Para coleções de elementos esta classe não irá gerar os elementos para serem inseridos na
 * coleção. </b>
 *
 * @since v.1 26/05/2012
 * @author Guilherme
 */
public class SizeFactory implements ValueFactory {


  private SizeFactory() {
    super();
  }

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    int min = field.getAnnotation(Size.class).min();
    int max = field.getAnnotation(Size.class).max();
    if ((max > MakeString.MAX_LENGTH_SUPPORTS) && (min < MakeString.MAX_LENGTH_SUPPORTS)) {
      LogInfo.logInfoInformation("SizeFactory",
          I18N.getMsg("sizeLengh", MakeString.MAX_LENGTH_SUPPORTS, max));
      max = MakeString.MAX_LENGTH_SUPPORTS;
    }
    if (field.getType().equals(String.class)) {
      field.set(entity, MakeString.getString(min, max, MakeString.StringType.LETTER));
    }
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    if (field.isAnnotationPresent(Size.class)) {
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

  // ==============
  // Static control
  // ==============
  private static ValueFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return a instance for SizeFactory class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueFactory getInstance() {
    if (instance == null) {
      instance = new SizeFactory();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
