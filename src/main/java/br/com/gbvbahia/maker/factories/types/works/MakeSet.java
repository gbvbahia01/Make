package br.com.gbvbahia.maker.factories.types.works;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
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
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeSet implements ValueSpecializedFactory {

  /**
   * Guarda informações que serão necessárias para popular a lista;
   * 
   * @since v.1 01/05/2012
   */
  private CollectionsHelper info;
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

  @Override
  public boolean workValue(final String value) {
    LogInfo.logDebugInformation("MakeSet", I18N.getMsg("workValueMake", value));
    Matcher matcher = PATTERN.matcher(value);
    if (matcher.find()) {
      LogInfo.logDebugInformation("MakeSet", I18N.getMsg("isWork", "Set", value));
      LogInfo.logDebugInformation("MakeSet", matcher.group());
      this.popularInfo(value);
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
  public <T> void makeValue(Field f, T entity, String... testName) throws IllegalAccessException,
      IllegalArgumentException {
    Set toSet =
        new HashSet(MakeEntity.makeEntities(this.info.getClazz(),
            MakeInteger.getIntervalo(this.info.getMin(), this.info.getMax()), testName));
    if (toSet.size() < this.info.getMin()) {
      LogInfo.logWarnInformation("MakeSet",
          I18N.getMsg("setSizeMenorMin", this.info.getMin(), toSet.size()));
    }
    f.set(entity, toSet);
  }

  /**
   * Popula info (CollectionsHelper) com informações necessárias para criar e popular o List.
   *
   * @param value Valor declarado no make.properties.
   * @throws MakeWorkException Se não encontrar a classe informada no properties ou conversão
   *         numérica não for possível.
   */
  private void popularInfo(final String value) {
    String clazz = StringUtils.substringBetween(value, "isSet{", "}");
    String minMax = StringUtils.substringBetween(value, "[", "]");
    String min = minMax.split(",")[0];
    String max = minMax.split(",")[1];
    LogInfo.logDebugInformation("MakeSet", "Class: " + clazz + " min:" + min + " max: " + max);
    try {
      this.info = new CollectionsHelper(Class.forName(clazz), new Integer(min), new Integer(max));
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
}
