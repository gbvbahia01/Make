package br.com.gbvbahia.maker.factories.types.managers;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

/**
 * Names of field and class must be centralized to use like key Maps.
 * 
 * @author Guilherme Braga
 * @since v.2 02/24/2016
 *
 */
public class NamesManager {


  public static String getFiledName(Field field) {
    return StringUtils.substringAfter(field.getDeclaringClass().toString(), "class ") + "."
        + field.getName();
  }

  public static String getClassName(Class<? extends Object> clazz) {
    return clazz.getName();
  }
}
