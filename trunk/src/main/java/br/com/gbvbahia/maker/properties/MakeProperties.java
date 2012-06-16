/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.common.ValuePropertiesFactory;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Guilherme
 */
public class MakeProperties implements ValueFactory {

    public static final String WORK_USER_IMPL = "work";
    /**
     * Armazena o nome do teste configurado, podendo ser recuperado
     * durante o teste se necessário.
     */
    private String testName = "";
    /**
     * Armazena as fábricas de que executam trabalho com base no
     * arquivo make.properties.
     */
    private Map<String, ValueFactory> valueFactories =
            new HashMap<String, ValueFactory>();

    /**
     * Perguta do factory especifico se ele trabalha com o field
     * passado.<br> Para works o tipo não é suficiente, é necessário o
     * nome da classe do objeto que contém do field juntamente com o
     * nome do field declarado no make.properties se é igual com o
     * nome da classe do objeto que contém o field juntamnete com o
     * nome do field passado.<br> Ex: No properties está:<br>
     * teste2.br.com.meuprojeto.MinhaClasse.cpf = isCPF<br> Quando
     * <code>isWorkWith</code> for chamado, ele irá verificar se no
     * field passado, a classe que o contém, ou seja, se a classe de
     * entity tem o nome: <i>br.com.meuprojeto.MinhaClasse</i> e se o
     * nome do field é <i>cpf</i>.
     *
     * @param <T> Tipo do objeto que contém o field.
     * @param f fieald que irá receber o valor.
     * @param entity Entidade que contém o field,
     * @return True para trabalha com, false para não trabalha.
     */
    @Override
    public <T> boolean isWorkWith(final Field f, final T entity) {
        String keyExp = getExpectKey(f);
        return valueFactories.containsKey(keyExp);
    }

    @Override
    public <T> void makeValue(
            final Field f,
            final T entity,
            final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        String keyExp = getExpectKey(f);
        valueFactories.get(keyExp).makeValue(f, entity, makeRelationships);
    }

    /**
     * Lê o arquivo make.properties, preparando os valores das classes
     * para os testes.
     *
     * @param testName java.lang.String chave da mensagem que será
     * enviada.
     */
    public MakeProperties(final String testName) {
        this.testName = testName;
        try {
            for (String key : ResourceBundle.getBundle("make").keySet()) {
                if (getFactoriesImpl(key)) {
                    MakePropertiesDefaultFactories.insertImplFactory(StringUtils.substringAfter(key, "."),
                            ResourceBundle.getBundle("make").getString(key));

                } else if (checkSameTest(key, testName)) {
                    insertValueFactory(StringUtils.substringAfter(key, "."),
                            ResourceBundle.getBundle("make").getString(key));

                } else {
                    LogInfo.logDebugInformation("MakeProperties",
                            I18N.getMsg("outTest", key));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(MakeProperties.class.getName()).log(Level.WARNING,
                    I18N.getMsg("makePropertiesNotFound"));
        }
    }

    /**
     * Verifica se o teste atual é o mesmo que está declarado no
     * arquivo make.properties.
     *
     * @param key Chave no make.properties.
     * @param testName Nome do teste passado.
     * @return True para o mesmo, false para não.
     */
    private boolean checkSameTest(String key, String testName) {
        boolean toReturn = StringUtils.substringBefore(key, ".").equals(testName);
        if (toReturn) {
            LogInfo.logDebugInformation("MakeProperties", I18N.getMsg("intTest", key));
        }
        return toReturn;

    }

    /**
     * Verifica se a propriedade é uma propriedade referente a uma
     * fabrica desenvolvida pelo desenvolvedor.
     *
     * @param key Chave no arquivo make.properties
     * @return true se for uma work false se não for.
     */
    private boolean getFactoriesImpl(final String key) {
        return StringUtils.equalsIgnoreCase(MakeProperties.WORK_USER_IMPL,
                StringUtils.substring(key, 0,
                MakeProperties.WORK_USER_IMPL.length()));
    }

    /**
     * Retorna o nome do teste declarado pelo desenvolvedor no arquivo
     * make.properties.
     *
     * @return nome do teste se houver, null se não foi declarado.
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Procura um ValuePropertiesFactory registrado para tratar a
     * propriedade registrada no arquivo make.properties.
     *
     * @param key Chave no arquivo make.properties.
     * @param value Valor no arquivo make.properties.
     */
    private void insertValueFactory(final String key, final String value) {
        ValuePropertiesFactory fac =
                MakePropertiesDefaultFactories.getPropertiesFactory(value);
        if (fac != null) {
            valueFactories.put(key, fac);
        } else {
            LogInfo.logWarnInformation("MakeProperties",
                    I18N.getMsg("notFactoryWork", value));
        }
    }

    /**
     * Retira o nome completo da classe mais o nome do parâmetro que
     * deverá estar declarado no propertie.
     *
     * @param f field a ter o valor definido.
     * @return Nome esperado no make.properties.
     */
    private String getExpectKey(final Field f) {
        return StringUtils.substringAfter(f.getDeclaringClass().toString(),
                "class ") + "." + f.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MakeProperties other = (MakeProperties) obj;
        if ((this.testName == null) ? (other.testName != null) : !this.testName.equals(other.testName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.testName != null ? this.testName.hashCode() : 0);
        return hash;
    }
}
