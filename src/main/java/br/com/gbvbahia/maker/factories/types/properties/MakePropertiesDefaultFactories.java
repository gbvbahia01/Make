/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types.properties;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.MakeCNPJ;
import br.com.gbvbahia.maker.works.MakeCPF;
import br.com.gbvbahia.maker.works.MakeEmail;
import br.com.gbvbahia.maker.works.MakeList;
import br.com.gbvbahia.maker.works.MakeName;
import br.com.gbvbahia.maker.works.common.ValuePropertiesFactory;
import br.com.gbvbahia.maker.works.execeptions.MakeWorkException;
import java.util.ArrayList;
import java.util.List;

/**
 * Todas as classes default devem ser declaradas aqui, adicionadas na
 * lista defaultPropertiesFactories no corpo estático.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakePropertiesDefaultFactories {

    final static List<Class<? extends ValuePropertiesFactory>> defaultPropertiesFactories =
            new ArrayList<Class<? extends ValuePropertiesFactory>>();

    static {
        defaultPropertiesFactories.add(MakeCPF.class);
        defaultPropertiesFactories.add(MakeCNPJ.class);
        defaultPropertiesFactories.add(MakeName.class);
        defaultPropertiesFactories.add(MakeEmail.class);
        defaultPropertiesFactories.add(MakeList.class);
    }

    /**
     * Verifica se os default são necessários ao teste, carrega
     * somente se houver necessidade.<br> Os implementados pelo
     * desenvolvedor tem preferência sobre os default.
     *
     * @param value Valor declarado no properties pelo usuário.
     * @return A fabrica personalizada de valores, implementada pelo
     * desenvolvedor ou default do Make.
     */
    static ValuePropertiesFactory getPropertiesFactory(String value) {
        for (Class<? extends ValuePropertiesFactory> clazz :
                defaultPropertiesFactories) {
            try {
                ValuePropertiesFactory vpf = clazz.newInstance();
                if (vpf.workValue(value)) {
                    return vpf;
                }
            } catch (MakeWorkException ex) {
                LogInfo.logErrorInformation(ex.getClassOrigem(),
                        I18N.getMsg(ex.getMsgProperties(),
                        ex.getVariations()), ex.getCause());
                throw ex;
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

    /**
     * Carrega as classes da fabricas personalizadas implementadas
     * pelo desenvolvedor.
     *
     * @param key
     * @param value
     */
    static void insertImplFactory(final String key, final String value) {
        try {
            Class<? extends ValuePropertiesFactory> fac =
                    (Class<? extends ValuePropertiesFactory>) Class.forName(value);
            if (!defaultPropertiesFactories.contains(fac)) {
                defaultPropertiesFactories.add(0, fac);
            }
        } catch (ClassNotFoundException ex) {
            LogInfo.logErrorInformation("MakePropertiesDefaultFactories",
                    I18N.getMsg("workUserNotFound", value), ex);
        }
    }
}
