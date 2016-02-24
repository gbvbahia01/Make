package br.com.gbvbahia.maker.factories.types.works;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.types.managers.NamesManager;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.factories.types.works.commons.CollectionsHelper;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeSet implements ValueSpecializedFactory {

  /**
   * A setup for a lot of objects that will be created.
   */
  private Map<String, CollectionsHelper> ruleHelper = new HashMap<String, CollectionsHelper>();
  /**
   * No arquivo make.properties deve estár definido no valor para o field:
   * "[isSet{][a-zA-Z0-9\\.]+[}][\\[][\\d]+,[\\d]+[\\]]".<br>
   * Regex: deve iniciar com <i>isList{</i> contendo letras maiusculas ou minusculas, numéros e o
   * caractere "." (ponto). Fecha com <i>}</i> e deve terminar com <i>[</i> qualquer número ","
   * (virgula) qualquer número <i>]</i>.
   */
  public static final String KEY_PROPERTY = "isSet\\{[a-zA-Z0-9\\.]+\\}[\\[][\\d]+,[\\d]+[\\]]";
  /**
   * Compilador regex que realiza a comparação.
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
        new HashSet(MakeEntity.makeEntities(valueHelper.getClazz(),
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
  public void testStageChanged(Notification notification) {
    if (notification.isTestFinished()) {
      this.ruleHelper.clear();
      instance = null;
    }
  }

  /**
   * Popula info (CollectionsHelper) com informações necessárias para criar e popular o List.
   *
   * @param value Valor declarado no make.properties.
   * @throws MakeWorkException Se não encontrar a classe informada no properties ou conversão
   *         numérica não for possível.
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
      ce.printStackTrace();
      throw new ValueSpecializedException(this.getClass(), "ClassNotFoundException",
          new String[] {clazz}, ce);
    } catch (NumberFormatException nf) {
      nf.printStackTrace();
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
   * @return
   */
  public static synchronized ValueSpecializedFactory getInstance() {
    if (instance == null) {
      instance = new MakeSet();
      NotifierTests.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
