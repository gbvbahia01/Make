package br.com.gbvbahia.maker.factories.types.properties;

import java.util.ArrayList;
import java.util.List;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.works.DefaultValuesFactory;
import br.com.gbvbahia.maker.factories.types.works.MakeBetween;
import br.com.gbvbahia.maker.factories.types.works.MakeCNPJ;
import br.com.gbvbahia.maker.factories.types.works.MakeCPF;
import br.com.gbvbahia.maker.factories.types.works.MakeEmail;
import br.com.gbvbahia.maker.factories.types.works.MakeIn;
import br.com.gbvbahia.maker.factories.types.works.MakeList;
import br.com.gbvbahia.maker.factories.types.works.MakeName;
import br.com.gbvbahia.maker.factories.types.works.MakeSet;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;

/**
 * All specialized factories original in the framework must be put here.<br>
 * THis class will manager all specialized factories, made in framework or made by developer.<br>
 * Classes that was made by developer will be loaded in:<br>
 * void insertImplFactory(String factoryClass) method.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakeWorksDefaultFactories {

  static final List<Class<? extends ValueSpecializedFactory>> WORK_FACTORIES =
      new ArrayList<Class<? extends ValueSpecializedFactory>>();

  /**
   * Load all specialized factories made with the framework.
   */
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
   * Check with specialized factory will be needed. This is defined check with them were declared in
   * xml file setup. Looking the field setup and check one by one with key property of each one.<br>
   * The all specialized factory made by developer have preferences on default specialized
   * factories.
   *
   * @param The value used a node fild by developer. isDefault, between[1,2], etc
   * @return A specialized factory, made by framework or developer.
   */
  static ValueSpecializedFactory getPropertiesFactory(String value) {
    for (int i = 0; i < WORK_FACTORIES.size(); i++) {
      try {
        ValueSpecializedFactory vpf = WORK_FACTORIES.get(i).newInstance();
        if (vpf.workValue(value)) {
          return vpf;
        }
      } catch (ValueSpecializedException ex) {
        LogInfo.logErrorInformation(ex.getOrigemClass().getSimpleName(),
            I18N.getMsg(ex.getMsgPropertieKey(), (Object[]) ex.getVarArgMsgVariations()),
            ex.getCause());
        throw ex;
      } catch (InstantiationException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories", I18N.getMsg(
            "propertiesFactoryInstantiationException", WORK_FACTORIES.get(i).getSimpleName()));
      } catch (IllegalAccessException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories", I18N.getMsg(
            "propertiesFactoryIllegalAccessException", WORK_FACTORIES.get(i).getSimpleName()));
      } catch (IllegalArgumentException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories", I18N.getMsg(
            "propertiesFactoryIllegalArgumentException", WORK_FACTORIES.get(i).getSimpleName(),
            value));
      } catch (SecurityException ex) {
        LogInfo.logWarnInformation("MakeDefaultFactories", I18N.getMsg(
            "propertiesFactorySecurityException", WORK_FACTORIES.get(i).getSimpleName()));
      }
    }
    return null;
  }

  /**
   * Load all factories made by developer and declared in node factories in xml setup file.
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
