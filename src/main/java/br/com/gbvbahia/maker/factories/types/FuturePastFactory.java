package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.types.complex.MakeCalendar;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

/**
 * Fabrica valeroes para Calendar e Date
 * 
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class FuturePastFactory implements ValueFactory {

  private FuturePastFactory() {
    super();
  }

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    if (field.getType().equals(Date.class)) {
      field.set(entity, this.valueTime(field).getTime());
    } else if (field.getType().equals(Calendar.class)) {
      field.set(entity, this.valueTime(field));
    }
  }

  private Calendar valueTime(Field field) {
    if (field.isAnnotationPresent(Future.class)) {
      return MakeCalendar.getInFuture();
    } else if (field.isAnnotationPresent(Past.class)) {
      return MakeCalendar.getInPast();
    }
    return MakeCalendar.getCalendar();
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    if (this.isDate(field)) {
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
   * Verifica se o field é tratado com anotações de tempo da JSR303.
   *
   * @param field Field a ser avaliado.
   * @return True para possui anotação de tempo False para não possui.
   */
  private boolean isDate(Field field) {
    if (field.isAnnotationPresent(Future.class) || field.isAnnotationPresent(Past.class)) {
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
   * @return a instance for FuturePastFactory class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueFactory getInstance() {
    if (instance == null) {
      instance = new FuturePastFactory();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
