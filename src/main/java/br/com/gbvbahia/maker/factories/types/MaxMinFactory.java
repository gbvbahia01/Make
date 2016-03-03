package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.MakeNumberManager;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import java.lang.reflect.Field;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Factory para classes anotadas com @Min e/ou @Max da JSR303.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class MaxMinFactory implements ValueFactory {

  MaxMinFactory() {
    super();
  }

  /**
   * Manager the makeNumber classes.
   */
  public final MakeNumberManager numberManager = new MakeNumberManager();

  @Override
  public <T> void makeValue(final Field field, final T entity, final String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    for (MakeNumber makeNumber : this.numberManager.getFactoriesNumber()) {
      if (makeNumber.isMyType(field)) {
        makeNumber.insertValue(field, entity);
        return;
      }
    }
    throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoMinMax"));
  }

  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    if (this.isNumber(field)) {
      return true;
    }
    return false;
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {
    Notification infoTest = notification;
    if (infoTest.isTestFinished()) {
      this.numberManager.clear();
      instance = null;
    }
    if (infoTest.isTestStarted()) {
      this.numberManager.loadNumberFactories();
    }
  }



  /**
   * Verifica se o field é tratado com anotações numéricas da JSR303.
   *
   * @param field Field a ser avaliado.
   * @return True para possui anotação numérica False para não possui.
   */
  private boolean isNumber(final Field field) {
    if (field.isAnnotationPresent(Min.class) || field.isAnnotationPresent(Max.class)
        || field.isAnnotationPresent(DecimalMin.class)
        || field.isAnnotationPresent(DecimalMax.class)) {
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
   * @return a instance for MaxMinFactory class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueFactory getInstance() {
    if (instance == null) {
      instance = new MaxMinFactory();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
