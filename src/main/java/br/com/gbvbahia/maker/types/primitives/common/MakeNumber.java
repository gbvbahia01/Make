package br.com.gbvbahia.maker.types.primitives.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * Define one contract for all class that create numbers.
 * 
 * @since v.1 10/06/2012
 * @author Guilherme Braga
 */
public abstract class MakeNumber {

  /**
   * It is used to inform to the caller if can create values for the field passed.
   *
   * @param field that will have a value made.
   * @return true can make or false cannot make.
   */
  public abstract boolean isMyType(Field field);

  /**
   * Create a value to put in the field parameter.<br>
   * Before to use this method you must call isMyType method to check if is possible to make a value
   * for the field parameter.<br>
   * The value will be made in the method and will respect the xml setup rules.
   *
   * @param <T> Type of the entity object.
   * @param field that will have a value made.
   * @param entity object that has he field to be populated.
   */
  public abstract <T> void insertValue(Field field, T entity) throws IllegalArgumentException,
      IllegalAccessException;

  /**
   * Put the value to the field. The parameter value will be converted to the type of field.
   *
   * @param <T> Type of the entity object.
   * @param field that will have a value made.
   * @param entity object that has he field to be populated.
   * @param value a value that will be converted to the type of field and will put into it.
   */
  public abstract <T> void insertValue(Field field, T entity, String value)
      throws IllegalArgumentException, IllegalAccessException;

  /**
   * This method can have two behaviors:<br>
   * 1. If the xml setup is set to read for JSR303 this method will search for @Min and @Max
   * annotations and use mix and max values respectively. If cannot find for those annotations its
   * will use the method parameters minValue and maxValue.<br>
   * 2. If the xml setup is set to ignore JSR303 this method will ignore the @Min and @Max
   * annotations and will use the parameters minValue and maxValue.
   *
   * @param field that will have a value made.
   * @param minValue acceptable to be created, used only if cannot use @Min annotation.
   * @param maxValue acceptable to be created, used only if cannot use @Max annotation.
   * @return Array with two positions, [0] minimum value and [1] the maximum value.
   */
  protected Number[] getMinMaxValues(Field field, Number minValue, Number maxValue) {
    Number[] toReturn = new Number[2];
    boolean readJsr303 = Factory.SETUP.readJsr303();
    if (field.isAnnotationPresent(Min.class) && readJsr303) {
      toReturn[0] = new Long(field.getAnnotation(Min.class).value());
    } else if (field.isAnnotationPresent(DecimalMin.class) && readJsr303) {
      toReturn[0] = new Double(field.getAnnotation(DecimalMin.class).value());
    } else if (field.isAnnotationPresent(Digits.class)) {
      toReturn[0] = 0.0;
    } else {
      LogInfo.logDefaultValue(field.getDeclaringClass().getSimpleName(), field, "NumberFactory");
      toReturn[0] = minValue.doubleValue();
    }
    if (field.isAnnotationPresent(Max.class) && readJsr303) {
      toReturn[1] = new Long(field.getAnnotation(Max.class).value());
    } else if (field.isAnnotationPresent(DecimalMax.class) && readJsr303) {
      toReturn[1] = new Double(field.getAnnotation(DecimalMax.class).value());
    } else if (field.isAnnotationPresent(Digits.class) && readJsr303) {
      toReturn[1] = this.maxDigits(field.getAnnotation(Digits.class).integer());
    } else {
      LogInfo.logDefaultValue(field.getDeclaringClass().getSimpleName(), field, "NumberFactory");
      toReturn[1] = maxValue.doubleValue();
    }
    return toReturn;
  }

  /**
   * Creates the greatest number possible.<br>
   *
   * @param amountDigits represents the amount of numbers, 3 = 999, 5 = 99999.
   * @return a larger number with the digits informed.
   */
  protected Long maxDigits(int amountDigits) {
    String toReturn = "";
    if (amountDigits < 19) {
      return new Long(StringUtils.leftPad(toReturn, amountDigits, "9"));
    } else {
      return Long.MAX_VALUE;
    }
  }

  /**
   * Checks if the annotation @Digits is present on the field and the JSR303 is defined read in the
   * xml. If true a value valid for that annotation will be made.
   *
   * @param field that will have a value made.
   * @param value that will be put in the field.
   * @return If @Digits is present and read in xml the value will be changed to respect it else the
   *         value parameter will be returned as the same.
   */
  protected Number maxDecimal(Field field, Number value) {
    boolean readJsr303 = Factory.SETUP.readJsr303();
    if (field.isAnnotationPresent(Digits.class) && readJsr303) {
      int maxDecimal = field.getAnnotation(Digits.class).fraction();
      return new BigDecimal(value.toString()).setScale(maxDecimal, RoundingMode.HALF_EVEN)
          .doubleValue();
    } else {
      return value;
    }
  }
}
