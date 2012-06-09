/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.i18n.I18N;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class MakeProperties {
    
    private String testName = "";
    
    
    /**
     * Lê o arquivo  make.properties, preparando os valores das classes
     * para os testes.
     *
     * @param testName java.lang.String chave da mensagem que será
     * enviada.
     * @return java.lang.String a ser utilizada para a chave passada,
     * caso ocorra algum erro ou a chave não exista, será retornado o
     * mesmo valor passado como parâmetro chave.
     */
    public static void factoryProperties(String testName) {
        
        try {
            for (String key : ResourceBundle.getBundle("make").keySet()){
                System.out.println(key);
            }
        } catch (Exception e) {
                Logger.getLogger(MakeProperties.class.getName()).log(Level.WARNING,
                        I18N.getMsg("makePropertiesNotFound"));
        }
    }

    public String getTestName() {
        return testName;
    }
}
