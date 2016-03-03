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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>MakeSet</b><br>
 * tag:isSet{class name}[3,5]<br>
 * Example: isSet{br.com.pro.Employee}[5,25]<br>
 * Some relations are one to many and for those case a set can be necessary.<br>
 * Inside of { } inform the full class name that will be created to add a Set.<br>
 * The class referenced between "{" "}" needs to have a default constructor. A constructor without
 * arguments.<br>
 * Inside of [ ] inform the minimum and maximum amount of objects that need to be created to add the
 * Set.<br>
 * A collection with Set cannot have two objects as equals. Make will try to create a Set with a
 * satisfactory amount. But if the framework cannot add objects because of equals the set can have
 * less objects than configured as minimum at isSet value.
 * 
 * @since v.1 01/05/2012
 * @author Guilherme
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MakeSet implements ValueSpecializedFactory {

  /**
   * A setup for a lot of objects that will be created.
   */
  private Map<String, CollectionsHelper> ruleHelper = new HashMap<String, CollectionsHelper>();
  /**
   * Key for this specialized factory.
   */
  public static final String KEY_PROPERTY = "isSet\\{[a-zA-Z0-9\\.]+\\}[\\[][\\d]+,[\\d]+[\\]]";
  /**
   * The compile regex.
   */
  private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTY);

  /**
   * Cannot be instantiated outside.
   */
  private MakeSet() {
    super();
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("MakeSet", I18N.getMsg("workValueMake", value));
    Matcher matcher = PATTERN.matcher(value);
    if (matcher.find()) {
      LogInfo.logDebugInformation("MakeSet", I18N.getMsg("isWork", "Set", value));
      LogInfo.logDebugInformation("MakeSet", matcher.group());
      this.popularInfo(fieldName, value);
      return true;
    } else {
      LogInfo.logDebugInformation("MakeSet", I18N.getMsg("notIsWork", "Set", value));
      return false;
    }
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    return field.getType().equals(java.util.Set.class);
  }

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    CollectionsHelper valueHelper = this.ruleHelper.get(NamesManager.getFiledName(field));
    Set toSet =
        new HashSet(MakeEntity.makes(valueHelper.getClazz(),
            MakeInteger.getIntervalo(valueHelper.getMin(), valueHelper.getMax()), testName));
    if (toSet.size() < valueHelper.getMin()) {
      LogInfo.logWarnInformation("MakeSet",
          I18N.getMsg("setSizeMenorMin", valueHelper.getMin(), toSet.size()));
    }
    field.set(entity, toSet);
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {
    if (notification.isTestFinished()) {
      this.ruleHelper.clear();
      instance = null;
    }
  }

  /**
   * Create ruleHelper object (CollectionsHelper) with information needed to create a Set.
   *
   * @param fieldName field name that will have a set.
   * @param value the string isList wrote in XML setup file.
   * @throws ValueSpecializedException if any problem happens.
   */
  private void popularInfo(String fieldName, String value) {
    String clazz = StringUtils.substringBetween(value, "isSet{", "}");
    String minMax = StringUtils.substringBetween(value, "[", "]");
    String min = minMax.split(",")[0];
    String max = minMax.split(",")[1];
    LogInfo.logDebugInformation("MakeSet", "Class: " + clazz + " min:" + min + " max: " + max);
    try {
      this.ruleHelper.put(fieldName, new CollectionsHelper(Class.forName(clazz), new Integer(min),
          new Integer(max)));
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
   * @return a instance for MakeSet class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueSpecializedFactory getInstance() {
    if (instance == null) {
      instance = new MakeSet();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
