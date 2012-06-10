/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.log.LogInfo;
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
    public MakeProperties(String testName) {
        this.testName = testName;
        try {
            for (String key : ResourceBundle.getBundle("make").keySet()) {
                if (StringUtils.substringBefore(key, ".").equals(testName)) {
                    LogInfo.logInfoInformation("MakeProperties", I18N.getMsg("intTest", key));
                    inserirValueFactory(StringUtils.substringAfter(key, "."),
                            ResourceBundle.getBundle("make").getString(key));
                } else {
                    LogInfo.logDebugInformation("MakeProperties", I18N.getMsg("outTest", key));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(MakeProperties.class.getName()).log(Level.WARNING,
                    I18N.getMsg("makePropertiesNotFound"));
        }
    }

    public String getTestName() {
        return testName;
    }

    /**
     * Procura um ValuePropertiesFactory registrado para tratar a
     * propriedade registrada no arquivo make.properties.
     *
     * @param key Chave no arquivo make.properties.
     * @param valor Valor no arquivo make.properties.
     */
    private void inserirValueFactory(final String key, final String valor) {
        ValuePropertiesFactory fac =
                MakePropertiesDefaultFactories.getPropertiesFactory(valor);
        if (fac != null) {
            valueFactories.put(key, fac);
        } else {
            LogInfo.logWarnInformation("MakeProperties",
                    I18N.getMsg("notFactoryWork", valor));
        }
    }

    /**
     * Retira o nome completo da classe mais o nome do parâmetro
     * que deverá estar declarado no propertie.
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
