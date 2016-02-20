package br.com.gbvbahia.maker.factories.types.properties;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.common.ValueSpecializedFactory;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeWorksFactory implements ValueFactory {

  /**
   * Armazena o nome do teste configurado, podendo ser recuperado durante o teste se necessário.
   */
  private String[] testName;
  /**
   * Armazena as fábricas de que executam trabalho com base no arquivo make.properties.
   */
  private Map<String, ValueFactory> valueFactories = new HashMap<String, ValueFactory>();

  /**
   * Lê o arquivo make.properties, preparando os valores das classes para os testes.
   *
   * @param testName java.lang.String chave da mensagem que será enviada.
   */
  public MakeWorksFactory(final String... testName) {
    this.testName = testName;
    try {
      XMLoader loader = XMLoader.getLoader();
      List<String> factories = loader.getFactories();
      for (String factory : factories) {
        MakeWorksDefaultFactories.insertImplFactory(factory);
      }
      Map<String, Map<String, String>> mapRules = loader.getMapRulesFieldsByTestName(testName);
      Set<String> keysClass = mapRules.keySet();
      for (String keyClass : keysClass) {
        Map<String, String> fields = mapRules.get(keyClass);
        Set<String> keysFields = fields.keySet();
        for (String keyField : keysFields) {
          this.insertValueFactory(keyClass + "." + keyField, fields.get(keyField));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      Logger.getLogger(MakeWorksFactory.class.getName()).log(Level.WARNING,
          I18N.getMsg("makePropertiesNotFound", (Object[]) testName));
    }
  }

  /**
   * Perguta do factory especifico se ele trabalha com o field passado.<br>
   * Para works o tipo não é suficiente, é necessário o nome da classe do objeto que contém do field
   * juntamente com o nome do field declarado no make.properties se é igual com o nome da classe do
   * objeto que contém o field juntamnete com o nome do field passado.<br>
   * Ex: No properties está:<br>
   * teste2.br.com.meuprojeto.MinhaClasse.cpf = isCPF<br>
   * Quando <code>isWorkWith</code> for chamado, ele irá verificar se no field passado, a classe que
   * o contém, ou seja, se a classe de entity tem o nome: <i>br.com.meuprojeto.MinhaClasse</i> e se
   * o nome do field é <i>cpf</i>.
   *
   * @param <T> Tipo do objeto que contém o field.
   * @param field fieald que irá receber o valor.
   * @param entity Entidade que contém o field,
   * @return True para trabalha com, false para não trabalha.
   */
  @Override
  public <T> boolean isWorkWith(final Field field, final T entity) {
    String keyExp = this.getExpectKey(field);
    return this.valueFactories.containsKey(keyExp);
  }

  @Override
  public <T> void makeValue(final Field field, final T entity, final String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    String keyExp = this.getExpectKey(field);
    this.valueFactories.get(keyExp).makeValue(field, entity, testName);
  }

  /**
   * Retorna o nome do teste declarado pelo desenvolvedor no arquivo make.properties.
   *
   * @return nome do teste se houver, null se não foi declarado.
   */
  public String[] getTestName() {
    return this.testName;
  }

  /**
   * Procura um ValueSpecializedFactory registrado para tratar a propriedade registrada no arquivo
   * make.properties.
   *
   * @param key Chave no arquivo make.properties.
   * @param value Valor no arquivo make.properties.
   */
  private void insertValueFactory(final String key, final String value) {
    ValueSpecializedFactory fac = MakeWorksDefaultFactories.getPropertiesFactory(value);
    if (fac != null) {
      this.valueFactories.put(key, fac);
    } else {
      final String errorMsg = I18N.getMsg("notFactoryWork", value);
      IllegalArgumentException error = new IllegalArgumentException(errorMsg);
      LogInfo.logErrorInformation("MakeProperties", errorMsg, error);
    }
  }

  /**
   * Retira o nome completo da classe mais o nome do parâmetro que deverá estar declarado no
   * propertie.
   *
   * @param field field a ter o valor definido.
   * @return Nome esperado no make.properties.
   */
  private String getExpectKey(final Field field) {
    return StringUtils.substringAfter(field.getDeclaringClass().toString(), "class ") + "."
        + field.getName();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final MakeWorksFactory other = (MakeWorksFactory) obj;
    if ((this.testName == null) ? (other.testName != null)
        : !this.testName.equals(other.testName)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = (47 * hash) + (this.testName != null ? this.testName.hashCode() : 0);
    return hash;
  }

  /**
   * Verifica se o field está mapeado para alguma fábrica no arquivo make.xml.
   * 
   * @param keyField
   * @return
   */
  public boolean isFieldMapped(String keyField) {
    return this.valueFactories.containsKey(keyField);
  }
}
