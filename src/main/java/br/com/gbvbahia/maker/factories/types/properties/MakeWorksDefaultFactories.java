package br.com.gbvbahia.maker.factories.types.properties;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.DefaultValuesFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.works.MakeBetween;
import br.com.gbvbahia.maker.works.MakeCNPJ;
import br.com.gbvbahia.maker.works.MakeCPF;
import br.com.gbvbahia.maker.works.MakeEmail;
import br.com.gbvbahia.maker.works.MakeIn;
import br.com.gbvbahia.maker.works.MakeList;
import br.com.gbvbahia.maker.works.MakeName;
import br.com.gbvbahia.maker.works.MakeSet;
import br.com.gbvbahia.maker.works.common.ValueSpecializedFactory;
import br.com.gbvbahia.maker.works.execeptions.MakeWorkException;

import java.util.ArrayList;
import java.util.List;

/**
 * Todas as classes default devem ser declaradas aqui, adicionadas na lista WORK_FACTORIES no corpo
 * estático.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakeWorksDefaultFactories {

  static final List<Class<? extends ValueSpecializedFactory>> WORK_FACTORIES =
      new ArrayList<Class<? extends ValueSpecializedFactory>>();

  static {
    WORK_FACTORIES.add(MakeCPF.class);
    WORK_FACTORIES.add(MakeCNPJ.class);
    WORK_FACTORIES.add(MakeName.class);
    WORK_FACTORIES.add(MakeEmail.class);
    WORK_FACTORIES.add(MakeList.class);
    WORK_FACTORIES.add(MakeSet.class);
    WORK_FACTORIES.add(MakeBetween.class);
    WORK_FACTORIES.add(MakeIn.class);
    WORK_FACTORIES.add(DefaultValuesFactory.class);
  }

  /**
   * Verifica se os default são necessários ao teste, carrega somente se houver necessidade.<br>
   * Os implementados pelo desenvolvedor tem preferência sobre os default.
   *
   * @param value Valor declarado no properties pelo usuário.
   * @return A fabrica personalizada de valores, implementada pelo desenvolvedor ou default do Make.
   */
  static ValueSpecializedFactory getPropertiesFactory(String value) {
    for (int i = 0; i < WORK_FACTORIES.size(); i++) {
      try {
        ValueSpecializedFactory vpf = WORK_FACTORIES.get(i).newInstance();
        if (vpf.workValue(value)) {
          return vpf;
        }
      } catch (MakeWorkException ex) {
        LogInfo.logErrorInformation(ex.getClassOrigem(),
            I18N.getMsg(ex.getMsgProperties(), (Object[]) ex.getVariations()), ex.getCause());
        ex.printStackTrace();
        throw ex;
      } catch (InstantiationException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories", I18N.getMsg(
            "propertiesFactoryInstantiationException", WORK_FACTORIES.get(i).getSimpleName()));
        ex.printStackTrace();
      } catch (IllegalAccessException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories", I18N.getMsg(
            "propertiesFactoryIllegalAccessException", WORK_FACTORIES.get(i).getSimpleName()));
        ex.printStackTrace();
      } catch (IllegalArgumentException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories",
            I18N.getMsg("propertiesFactoryIllegalArgumentException",
                WORK_FACTORIES.get(i).getSimpleName(), value));
        ex.printStackTrace();
      } catch (SecurityException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories", I18N
            .getMsg("propertiesFactorySecurityException", WORK_FACTORIES.get(i).getSimpleName()));
        ex.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Carrega as classes da fabricas personalizadas implementadas pelo desenvolvedor.
   *
   * @param key
   * @param value
   */
  @SuppressWarnings("unchecked")
  static void insertImplFactory(final String factoryClass) {
    try {
      Class<? extends ValueSpecializedFactory> fac =
          (Class<? extends ValueSpecializedFactory>) Class.forName(factoryClass);
      if (!WORK_FACTORIES.contains(fac)) {
        WORK_FACTORIES.add(0, fac);
      }
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
      LogInfo.logErrorInformation("MakePropertiesDefaultFactories",
          I18N.getMsg("workUserNotFound", factoryClass), ex);
    }
  }
}
