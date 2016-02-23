package br.com.gbvbahia.maker.factories.types.works;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.types.works.commons.CollectionsHelper;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

/**
 * This specialized class will with fields that have the mapped regex value:<br>
 * "[isList{][a-zA-Z0-9\\.]+[}][\\[][\\d]+,[\\d]+[\\]]"<br>
 * A list will be created and populled with the class informed between "{" "}", like:<br>
 * isList{com.mypackage.MyClass}[2,4]<br>
 * The number between "[" "]" will determine the amount fo class that will be created to put in
 * list.<br>
 * If the class referenced between "{" "}" is a object created it needs to have a default
 * constructor. A constructor without arguments.
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
public class MakeList implements ValueSpecializedFactory {

  /**
   * A setup for a object that will be created.
   */
  private CollectionsHelper valueHelper;
  /**
   * Regex: "[isList{][a-zA-Z0-9\\.]+[}][\\[][\\d]+,[\\d]+[\\]]".
   */
  public static final String KEY_PROPERTY = "isList\\{[a-zA-Z0-9\\.]+\\}[\\[][\\d]+,[\\d]+[\\]]";
  /**
   * Compile the regex pattern.
   */
  private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTY);

  @Override
  public boolean workValue(final String value) {
    LogInfo.logDebugInformation("MakeList", I18N.getMsg("workValueMake", value));
    Matcher matcher = PATTERN.matcher(value);
    if (matcher.find()) {
      LogInfo.logDebugInformation("MakeList", I18N.getMsg("isWork", "List", value));
      LogInfo.logDebugInformation("MakeList", matcher.group());
      this.popularInfo(value);
      return true;
    } else {
      LogInfo.logDebugInformation("MakeList", I18N.getMsg("notIsWork", "List", value));
      return false;
    }
  }

  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    return field.getType().equals(java.util.List.class);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    List toSet =
        new ArrayList(MakeEntity.makeEntities(this.valueHelper.getClazz(),
            MakeInteger.getIntervalo(this.valueHelper.getMin(), this.valueHelper.getMax()),
            testName));
    field.set(entity, toSet);
  }

  /**
   * Popula info (CollectionsHelper) com informações necessárias para criar e popular o List.
   *
   * @param value Valor declarado no make.properties.
   * @throws MakeWorkException Se não encontrar a classe informada no properties ou conversão
   *         numérica não for possível.
   */
  private void popularInfo(final String value) {
    String clazz = StringUtils.substringBetween(value, "isList{", "}");
    String minMax = StringUtils.substringBetween(value, "[", "]");
    String minAmountElementsInList = minMax.split(",")[0];
    String maxAmountElementsInList = minMax.split(",")[1];
    LogInfo.logDebugInformation("MakeList", "Class: " + clazz + " min:" + minAmountElementsInList
        + " max: " + maxAmountElementsInList);
    try {
      this.valueHelper =
          new CollectionsHelper(Class.forName(clazz), new Integer(minAmountElementsInList),
              new Integer(maxAmountElementsInList));
    } catch (ClassNotFoundException ce) {
      throw new ValueSpecializedException(this.getClass(), "ClassNotFoundException",
          new String[] {clazz}, ce);
    } catch (NumberFormatException nf) {
      throw new ValueSpecializedException(this.getClass(), "NumberFormatException",
          new String[] {minMax}, nf);
    }
  }
}
