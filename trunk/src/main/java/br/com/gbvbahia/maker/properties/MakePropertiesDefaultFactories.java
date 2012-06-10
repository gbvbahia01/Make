/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.MakeCPF;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Todas as classes default devem ser declaradas aqui, adicionadas
 * na lista defaultPropertiesFactories no corpo estático
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakePropertiesDefaultFactories {

    public final static List<Class<? extends ValuePropertiesFactory>> defaultPropertiesFactories =
            new ArrayList<Class<? extends ValuePropertiesFactory>>();

    static {
        defaultPropertiesFactories.add(MakeCPF.class);
    }

    public static ValuePropertiesFactory getPropertiesFactory(String value) {
        for (Class<? extends ValuePropertiesFactory> clazz :
                defaultPropertiesFactories) {
            try {
                ValuePropertiesFactory vpf = clazz.newInstance();
                if (vpf.workValue(value)) {
                    return vpf;
                }
            } catch (InstantiationException ex) {
                LogInfo.logWarnInformation("MakeDefaultFactories",
                        I18N.getMsg("propertiesFactoryInstantiationException",
                        clazz.getSimpleName()));
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                LogInfo.logWarnInformation("MakeDefaultFactories",
                        I18N.getMsg("propertiesFactoryIllegalAccessException",
                        clazz.getSimpleName()));
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                LogInfo.logWarnInformation("MakeDefaultFactories",
                        I18N.getMsg("propertiesFactoryIllegalArgumentException",
                        clazz.getSimpleName(), value));
                ex.printStackTrace();
            } catch (SecurityException ex) {
                LogInfo.logWarnInformation("MakeDefaultFactories",
                        I18N.getMsg("propertiesFactorySecurityException",
                        clazz.getSimpleName()));
                ex.printStackTrace();
            }
        }
        return null;
    }
}
