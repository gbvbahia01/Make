package br.com.gbvbahia.maker.factories.types;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.complex.MakeCalendar;

/**
 * Fabrica valeroes para Calendar e Date
 * 
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class FuturePastFactory implements ValueFactory {

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
}
