package br.com.gbvbahia.maker.factories.types.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.managers.MakeNumberManager;
import br.com.gbvbahia.maker.factories.types.managers.NamesManager;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>MakeIn</b><br>
 * tag: in{x,y,z}[,]<br>
 * Examples: in{10;20;30}[;] in{10.30|10.50|10.80}[|] in{A,B,C}<br>
 * in{1,3} Will be used 1 or 3.<br>
 * If the separator needs to be a value different of "," (comma) you define at [ ].<br>
 * in{1A3}[A] will be used 1 or 3<br>
 * Works with numbers and characters. MakeIn will choose a value in the range informed.<br>
 * Notice the character between the [?] is the separator between possible values. If is not informed
 * will be used comma.<br>
 * 
 * @since v.1 18/06/2012
 * @author Guilherme
 */
public class MakeIn implements ValueSpecializedFactory {

  /**
   * Guarda informações que serão necessárias para popular o field.
   */
  private Map<String, List<String>> mapInList = new HashMap<String, List<String>>();
  /**
   * Regex for in key.
   */
  public static final String KEY_PROPERTY = "in\\{.*\\}(\\[.+\\])?";
  /**
   * Regex compiler.
   */
  private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTY);

  /**
   * Manager the makeNumber classes.
   */
  public final MakeNumberManager numberManager = new MakeNumberManager();

  /**
   * Cannot be instantiated outside.
   */
  private MakeIn() {
    super();
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("MakeIn", I18N.getMsg("workValueMake", value));
    Matcher matcher = PATTERN.matcher(value);
    if (matcher.find()) {
      LogInfo.logDebugInformation("MakeIn", I18N.getMsg("isWork", "In", value));
      LogInfo.logDebugInformation("MakeIn", matcher.group());
      this.popularInfo(fieldName, value);
      return true;
    } else {
      LogInfo.logDebugInformation("MakeIn", I18N.getMsg("notIsWork", "In", value));
      return false;
    }
  }

  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    for (MakeNumber number : this.numberManager.getFactoriesNumber()) {
      if (number.isMyType(field)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    String keyField = NamesManager.getFiledName(field);
    List<String> inList = this.mapInList.get(keyField);
    for (MakeNumber number : this.numberManager.getFactoriesNumber()) {
      if (number.isMyType(field)) {
        try {
          number.insertValue(field, entity,
              inList.get(MakeInteger.getIntervalo(0, inList.size() - 1)));
        } catch (NumberFormatException nf) {
          throw new ValueSpecializedException(this.getClass(), "NumberFormatException",
              new String[] {inList.toString()}, nf);
        }
      }
    }
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {
    if (notification.isCreationFinished()) {
      this.numberManager.clear();
      instance = null;
    }
    if (notification.isCreationStarted()) {
      this.numberManager.loadNumberFactories();
    }
  }

  /**
   * It prepares the factory to work.
   *
   * @param fieldName the field that will receive the value.
   * @param value the value declared in XML setup file.
   */
  private void popularInfo(String fieldName, String value) {
    String in = StringUtils.substringBetween(value, "{", "}");
    String split = StringUtils.substringBetween(value, "[", "]");
    if (StringUtils.isBlank(split)) {
      split = ",";
    }
    this.mapInList.put(fieldName, Arrays.asList(StringUtils.split(in, split)));
    LogInfo.logDebugInformation("MakeIn", "split:" + split + " in: " + in);
  }

  // ==============
  // Static control
  // ==============
  private static ValueSpecializedFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return a instance for MakeIn class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueSpecializedFactory getInstance() {
    if (instance == null) {
      instance = new MakeIn();
      NotifierStage.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
