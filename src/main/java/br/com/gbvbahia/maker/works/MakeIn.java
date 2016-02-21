package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.MaxMinFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import br.com.gbvbahia.maker.works.common.ValueSpecializedFactory;
import br.com.gbvbahia.maker.works.execeptions.MakeWorkException;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use a value informed at field.<br>
 * in{1,3} Will be used 1 or 3.<br>
 * If the separator needs to be a value different of "," (comma) you define at [ ].<br>
 * in{1A3}[A] will be used 1 or 3<br>
 * 
 * @since v.1 18/06/2012
 * @author Guilherme
 */
public class MakeIn implements ValueSpecializedFactory {

  /**
   * Guarda informações que serão necessárias para popular o field.
   */
  private List<String> inList;
  /**
   * No arquivo make.properties deve estár definido no valor para o field: "in\\{.*\\}(\\[.+\\])?".
   * <br>
   * Regex: deve iniciar com <i>in{</i>conter qualquer tipo de caractere. Fecha com <i>}</i> podendo
   * seguir com <i>[</i> conter qualquer caractere "." (ponto) fechando com <i>]</i>. O que está
   * entre <i>[?]</i> Será utilizado como separador, se não houver valor explícito, será considerado
   * "," (virgula). <br>
   * Ex: Inteiros: in{1,1}[,], in{10|100}[|], in{-10ab10}[ab]<br>
   * Fracionádos: in{1.12,1.22}[,] in{-10.33,100.40} in{1!10.20}[!]<br>
   * Letras: in{abc|abg}[|] <br>
   * Se o segundo número for menor que o primeiro um erro será gerado.
   */
  public static final String KEY_PROPERTY = "in\\{.*\\}(\\[.+\\])?";
  /**
   * Compilador regex que realiza a comparação.
   */
  private static final Pattern PATTERN = Pattern.compile(KEY_PROPERTY);

  @Override
  public boolean workValue(final String value) {
    LogInfo.logDebugInformation("MakeIn", I18N.getMsg("workValueMake", value));
    Matcher matcher = PATTERN.matcher(value);
    if (matcher.find()) {
      LogInfo.logDebugInformation("MakeIn", I18N.getMsg("isWork", "In", value));
      LogInfo.logDebugInformation("MakeIn", matcher.group());
      this.popularInfo(value);
      return true;
    } else {
      LogInfo.logDebugInformation("MakeIn", I18N.getMsg("notIsWork", "In", value));
      return false;
    }
  }

  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    for (MakeNumber number : MaxMinFactory.NUMBERS_FACTORYS) {
      if (number.isMyType(field)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    for (MakeNumber number : MaxMinFactory.NUMBERS_FACTORYS) {
      if (number.isMyType(field)) {
        try {
          number.insertValue(field, entity,
              this.inList.get(MakeInteger.getIntervalo(0, this.inList.size() - 1)));
        } catch (NumberFormatException nf) {
          nf.printStackTrace();
          throw new MakeWorkException("MakeIn", "NumberFormatException",
              new String[] {this.inList.toString()}, nf);
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
  private void popularInfo(final String value) {
    String in = StringUtils.substringBetween(value, "{", "}");
    String split = StringUtils.substringBetween(value, "[", "]");
    if (StringUtils.isBlank(split)) {
      split = ",";
    }
    this.inList = Arrays.asList(StringUtils.split(in, split));
    LogInfo.logDebugInformation("MakeIn", "split:" + split + " in: " + in);
  }
}
