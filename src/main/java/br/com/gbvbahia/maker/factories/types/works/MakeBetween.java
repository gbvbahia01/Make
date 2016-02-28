package br.com.gbvbahia.maker.factories.types.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.managers.MakeNumberManager;
import br.com.gbvbahia.maker.factories.types.managers.NamesManager;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.factories.types.works.commons.NumberHelper;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilizada para criar valores limitados a um intervalo:<br>
 * testBetween1.br.com.gbvbahia.entityes.EntityBetweenTest.valor = between{5,10}<br>
 * A propriedade valor será definida entre 5 e 10. Sendo os dois valores inclusos, podendo ser
 * utilizados.
 *
 * @since v.1 18/06/2012
 * @author Guilherme
 */
public class MakeBetween implements ValueSpecializedFactory {

  /**
   * Guarda informações que serão necessárias para popular o field.
   */
  private Map<String, NumberHelper> numberHelper = new HashMap<String, NumberHelper>();
  /**
   * No arquivo make.properties deve estár definido no valor para o field:
   * "between\\{[-\\d]+[\\.\\d]?[\\d]*,[-\\d]+[\\.\\d]?[\\d]*\\}".<br>
   * Regex: deve iniciar com <i>between{</i> conter números positivos ou negativos (-) o caractere
   * "." (ponto) separando as casas decimais, se houver. Fecha com <i>}</i> .<br>
   * Ex: Inteiros: between{1,1}, between{10,100}, between{-10,10}<br>
   * Fracionádos: between{1.12,1.22} between{-10.33,100.40} between{1,10.20}<br>
   * Se o segundo número for menor que o primeiro um erro será gerado.
   */
  public static final String KEY_PROPERTY =
      "between\\{[-\\d]+[\\.\\d]?[\\d]*,[-\\d]+[\\.\\d]?[\\d]*\\}";
  /**
   * Compilador regex que realiza a comparação.
   */
  private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTY);

  /**
   * Manager the makeNumber classes.
   */
  public final MakeNumberManager numberManager = new MakeNumberManager();

  /**
   * Cannot be instantiated outside.
   */
  private MakeBetween() {
    super();
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("MakeBetween", I18N.getMsg("workValueMake", value));
    Matcher matcher = PATTERN.matcher(value);
    if (matcher.find()) {
      LogInfo.logDebugInformation("MakeBetween", I18N.getMsg("isWork", "Between", value));
      LogInfo.logDebugInformation("MakeBetween", matcher.group());
      this.popularInfo(fieldName, value);
      return true;
    } else {
      LogInfo.logDebugInformation("MakeBetween", I18N.getMsg("notIsWork", "Between", value));
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
    for (MakeNumber number : this.numberManager.getFactoriesNumber()) {
      if (number.isMyType(field)) {
        try {
          number.insertValue(field, entity, this.numberHelper.get(keyField).getValue());
        } catch (NumberFormatException nf) {
          throw new ValueSpecializedException(this.getClass(), "NumberFormatException",
              new String[] {this.numberHelper.get(keyField).toString()}, nf);
        }
      }
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
    String minMax = StringUtils.substringBetween(value, "{", "}");
    String min = minMax.split(",")[0];
    String max = minMax.split(",")[1];
    LogInfo.logDebugInformation("MakeBetween", "min:" + min + " max: " + max);
    this.numberHelper.put(fieldName, new NumberHelper(min, max));
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {
    if (notification.isTestFinished()) {
      this.numberManager.clear();
      this.numberHelper.clear();
      instance = null;
    }
    if (notification.isTestStarted()) {
      this.numberManager.loadNumberFactories();
    }
  }

  // ==============
  // Static control
  // ==============
  private static ValueSpecializedFactory instance = null;

  /**
   * Get a instance for this class encapsulated by ValueSpecializedFactory.
   * 
   * @return a instance for MakeBetween class encapsulated by ValueSpecializedFactory.
   */
  public static synchronized ValueSpecializedFactory getInstance() {
    if (instance == null) {
      instance = new MakeBetween();
      NotifierTests.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
