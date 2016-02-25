package br.com.exemplo.maker;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;

public class FactoryNomePontoAtendimento implements ValueSpecializedFactory {
  /**
   * Como o propertie deve estár definido no valor: "isNomePontoAtendimento".
   */
  public static final String KEY_PROPERTIE = "isNomePontoAtendimento";
  public static int contator = 1;

  /**
   * Construtor padrão.
   */
  public FactoryNomePontoAtendimento() {}

  @Override
  public <T> void makeValue(Field field, final T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    field.set(entity, "Ponto: " + contator);
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    if (KEY_PROPERTIE.equals(StringUtils.trim(value))) {
      return true;
    }
    return false;
  }

  /**
   * Irá avaliar se o tipo do Field é trabalhado pelo mesmo, aqui deve ser String.
   *
   * @param field Field a ter o valor definido.
   * @return True para String False para o resto.
   */
  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    return field.getType().equals(String.class);
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void updateStage(Notification notification) {}
}
