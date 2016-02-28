package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import org.apache.commons.logging.Log;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;

/**
 * Passe uma lista com possibilidades para ser definida no field. <br>
 * Somente utilizado com a anotação @Pattern(regexp = "//@//[]sS") da especificação JSR303
 * (Hibernate Validator).
 *
 * @since v.1 26/05/2012
 * @author Guilherme
 */

public class PatternFactory implements ValueFactory {
  /**
   * Devido a complexidade de gerar uma string com base em uma expressão regular é disponibilizado
   * uma forma de passar várias strings que atendam um field.<br>
   * key: NomeClasse.nomeFiled valor: Uma lista com várias possibilidades para o field. Minimo de
   * uma possibilidade deve ser inserida.
   */
  public Map<String, List<String>> patternsList;

  public PatternFactory(Map<String, List<String>> patternsList) {
    this.patternsList = patternsList;
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    if (field.isAnnotationPresent(Pattern.class)) {
      return true;
    }
    return false;
  }

  public PatternFactory() {}

  /**
   * Log local, devido a complexidade de informações esse é necessário.
   */
  private Log logger = LogInfo.getLog("PatternFactory");

  @Override
  public <T> void makeValue(final Field field, final T entity, final String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    if (this.patternsList == null) {
      LogInfo.logWarnInformation("PatternFactory",
          I18N.getMsg("regexListNull", entity.getClass().getSimpleName(), field.getName()));
    } else {
      String keyExp = entity.getClass().getSimpleName() + "." + field.getName();
      this.logger.debug(I18N.getMsg("keyPatternInteration", keyExp));
      for (String key : this.patternsList.keySet()) {
        if (key.equals(keyExp)) {
          List<String> list = this.patternsList.get(key);
          Integer posicao = MakeInteger.getIntervalo(0, list.size() - 1);
          String valuePattern = list.get(posicao);
          field.set(entity, valuePattern);
        }
      }
      if (field.get(entity) == null) {
        LogInfo.logWarnInformation("PatternFactory", I18N.getMsg("regexListFieldNotFound", keyExp));
      }
    }
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {}
}
