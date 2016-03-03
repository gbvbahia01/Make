package br.com.gbvbahia.maker.factories.types.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.types.managers.NamesManager;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.factories.types.works.commons.CollectionsHelper;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>MakeList</b><br>
 * tag:isList{class name}[I,E]<br>
 * Example: isList{br.com.pro.Employee}[5,25]<br>
 * Some relations are one to many and for those case a list can be necessary.<br>
 * Inside of {} inform the full class name that will be created to add a List.<br>
 * Inside of [] inform the minimum and maximum amount of objects that need to be created to add the
 * List.<br>
 * If the class referenced between "{" "}" is a object created it needs to have a default
 * constructor. A constructor without arguments.<br>
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MakeList implements ValueSpecializedFactory {

  /**
   * A setup for a lot of objects that will be created.
   */
  private Map<String, CollectionsHelper> ruleHelper = new HashMap<String, CollectionsHelper>();

  /**
   * Regex: "[isList{][a-zA-Z0-9\\.]+[}][\\[][\\d]+,[\\d]+[\\]]".
   */
  public static final String KEY_PROPERTY = "isList\\{[a-zA-Z0-9\\.]+\\}[\\[][\\d]+,[\\d]+[\\]]";
  /**
   * Compile the regex pattern.
   */
  private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTY);

  /**
   * Cannot be instantiated outside.
   */
  private MakeList() {
    super();
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("MakeList", I18N.getMsg("workValueMake", value));
    Matcher matcher = PATTERN.matcher(value);
    if (matcher.find()) {
      LogInfo.logDebugInformation("MakeList", I18N.getMsg("isWork", "List", value));
      LogInfo.logDebugInformation("MakeList", matcher.group());
      this.popularInfo(fieldName, value);
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

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    CollectionsHelper valueHelper = this.ruleHelper.get(NamesManager.getFiledName(field));
    List toSet =
        new ArrayList(MakeEntity.makes(valueHelper.getClazz(),
            MakeInteger.getIntervalo(valueHelper.getMin(), valueHelper.getMax()), testName));
    field.set(entity, toSet);
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {
    if (notification.isCreationFinished()) {
      this.ruleHelper.clear();
      instance = null;
    }
  }

  /**
   * Create ruleHelper object (CollectionsHelper) with information needed to create a List.
   *
   * @param fieldName field name that will have a list.
   * @param value the string isList wrote in XML setup file.
   * @throws ValueSpecializedException if any problem happens.
   */
  private void popularInfo(String fieldName, String value) {
    String clazz = StringUtils.substringBetween(value, "isList{", "}");
    String minMax = StringUtils.substringBetween(value, "[", "]");
    String minAmountElementsInList = minMax.split(",")[0];
    String maxAmountElementsInList = minMax.split(",")[1];
    LogInfo.logDebugInformation("MakeList", "Class: " + clazz + " min:" + minAmountElementsInList
        + " max: " + maxAmountElementsInList);
    try {
      this.ruleHelper.put(fieldName, new CollectionsHelper(Class.forName(clazz), new Integer(
          minAmountElementsInList), new Integer(maxAmountElementsInList)));
    } catch (ClassNotFoundException ce) {
      throw new ValueSpecializedException(this.getClass(), "ClassNotFoundException",
          new String[] {clazz}, ce);
    } catch (NumberFormatException nf) {
      throw new ValueSpecializedException(this.getClass(), "NumberFormatException",
          new String[] {minMax}, nf);
    }
  }

  // ==============
  // Static control
  // ==============
  private static ValueSpecializedFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return a instance for MakeList class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueSpecializedFactory getInstance() {
    if (instance == null) {
      instance = new MakeList();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
