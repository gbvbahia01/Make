package br.com.gbvbahia.maker.factories.types.works;

import java.lang.reflect.Field;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierTests;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

/**
 * Cria nomes aleatórios, para que funcione deve ter o valor isName no arquivo make.properties.
 *
 * @since v.1 10/06/2012
 * @author Guilherme
 */
public class MakeName implements ValueSpecializedFactory {

  /**
   * Como o propertie deve estár definido no valor: "isName".
   */
  public static final String KEY_PROPERTY = "isName";
  /**
   * O arquivo nomes.properties possui uma quantidade limitada de nomes, essa variável armazena o
   * limite.
   */
  private static final int MAX_PROPERTIES_NAMES = 1281;

  /**
   * Cannot be instantiated outside.
   */
  private MakeName() {
    super();
  }

  @Override
  public boolean workValue(String fieldName, String value) {
    LogInfo.logDebugInformation("MakeName", I18N.getMsg("workValueMake", value));
    if (KEY_PROPERTY.equals(StringUtils.trim(value))) {
      return true;
    }
    LogInfo.logDebugInformation("MakeName", I18N.getMsg("notIsWork", "Name", value));
    return false;
  }

  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    return field.getType().equals(String.class);
  }

  @Override
  public <T> void makeValue(Field field, final T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    field.set(entity, getName());
  }

  /**
   * Observer to warn about the test stage.
   */
  @Override
  public void testStageChanged(Notification notification) {
    if (notification.isTestFinished()) {
      instance = null;
    }
  }

  /**
   * Retira dois valores do arquivo: nomes.properties, onde todos os nomes ficam armazenados.
   *
   * @param posicao indica qual posição deverá ser trazido o nome, de 0 até 1281.
   * @return java.lang.String referente a um nome.
   */
  private static String getMsg(final int posicao) {
    try {
      return ResourceBundle.getBundle("names_make").getString("nome" + posicao);
    } catch (MissingResourceException e) {
      e.printStackTrace();
      Logger.getLogger(I18N.class.getName()).log(Level.SEVERE,
          "Maker: Nome não encontrada para {0}", new Object[] {"nome" + posicao});
      return "nome" + posicao;
    }
  }

  /**
   * Retira dois valores do arquivo: nomes.properties, onde todos os nomes ficam armazenados.
   *
   * @return String nome.
   */
  public static String getName() {
    int firstName = MakeInteger.getMax(MAX_PROPERTIES_NAMES);
    int secondName;
    do {
      secondName = MakeInteger.getMax(MAX_PROPERTIES_NAMES);
    } while (secondName == firstName);
    return getMsg(firstName) + " " + getMsg(secondName);
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
      instance = new MakeName();
      NotifierTests.getNotifyer().addObserver(instance);
    }
    return instance;
  }
}
