package br.com.gbvbahia.maker.factories.types.managers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.works.DefaultValuesFactory;
import br.com.gbvbahia.maker.factories.types.works.MakeBetween;
import br.com.gbvbahia.maker.factories.types.works.MakeCnpj;
import br.com.gbvbahia.maker.factories.types.works.MakeCpf;
import br.com.gbvbahia.maker.factories.types.works.MakeEmail;
import br.com.gbvbahia.maker.factories.types.works.MakeIn;
import br.com.gbvbahia.maker.factories.types.works.MakeList;
import br.com.gbvbahia.maker.factories.types.works.MakeName;
import br.com.gbvbahia.maker.factories.types.works.MakeSet;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.factories.types.works.exceptions.ValueSpecializedException;
import br.com.gbvbahia.maker.log.LogInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * All specialized factories original in the framework must be put here.<br>
 * THis class will manager all specialized factories, made in framework or made by developer.<br>
 * Classes that was made by developer will be loaded in:<br>
 * void insertImplFactory(String factoryClass) method.
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public final class ValueSpecializedFactoryManager {

  /**
   * SpecializedFactories default and made by developer.
   */
  private final Map<Class<? extends ValueSpecializedFactory>, ValueSpecializedFactory> spzFactories;

  /**
   * Visible only in the same package.
   */
  protected ValueSpecializedFactoryManager() {
    super();
    this.spzFactories =
        new HashMap<Class<? extends ValueSpecializedFactory>, ValueSpecializedFactory>();
    this.loadDeveloperFactories();
    this.loadSpecializedFactories();
  }

  /**
   * Load all specialized factories made with the framework.
   */
  private void loadSpecializedFactories() {
    this.spzFactories.put(MakeCpf.class, MakeCpf.getInstance());
    this.spzFactories.put(MakeCnpj.class, MakeCnpj.getInstance());
    this.spzFactories.put(MakeName.class, MakeName.getInstance());
    this.spzFactories.put(MakeEmail.class, MakeEmail.getInstance());
    this.spzFactories.put(MakeList.class, MakeList.getInstance());
    this.spzFactories.put(MakeSet.class, MakeSet.getInstance());
    this.spzFactories.put(MakeBetween.class, MakeBetween.getInstance());
    this.spzFactories.put(MakeIn.class, MakeIn.getInstance());
    this.spzFactories.put(DefaultValuesFactory.class, DefaultValuesFactory.getInstance());
  }

  /**
   * Load all factories made by developer and declared in node factories in xml setup file.
   */
  @SuppressWarnings("unchecked")
  private void loadDeveloperFactories() {
    XMLoader loader = XMLoader.getLoader();
    List<String> factories = loader.getFactories();
    for (String factory : factories) {
      try {
        Class<? extends ValueSpecializedFactory> fac =
            (Class<? extends ValueSpecializedFactory>) Class.forName(factory);
        if (!this.spzFactories.containsKey(fac)) {
          ValueSpecializedFactory specializedFactory = fac.newInstance();
          this.spzFactories.put(fac, specializedFactory);
          NotifierTests.getNotifyer().addObserver(specializedFactory);
        }
      } catch (ClassNotFoundException ex) {
        LogInfo.logErrorInformation("MakePropertiesDefaultFactories",
            I18N.getMsg("workUserNotFound", factory), ex);
      } catch (IllegalAccessException iEx) {
        LogInfo.logWarnInformation("MakeDefaultFactories",
            I18N.getMsg("propertiesFactoryIllegalAccessException", factory));
      } catch (InstantiationException iEx) {
        LogInfo.logWarnInformation("MakeDefaultFactories",
            I18N.getMsg("propertiesFactoryInstantiationException", factory));
      }
    }
  }

  /**
   * Check with specialized factory will be needed. This is defined check with them were declared in
   * xml file setup. Looking the field setup and check one by one with key property of each one.<br>
   * The all specialized factory made by developer have preferences on default specialized
   * factories.
   *
   * @param keyField The value used a node fild by developer. isDefault, between[1,2], etc
   * @return A specialized factory, made by framework or developer.
   */
  protected ValueSpecializedFactory getFactory(String fieldName, String keyField) {
    Set<Class<? extends ValueSpecializedFactory>> keyClass = this.spzFactories.keySet();
    for (Class<? extends ValueSpecializedFactory> clazz : keyClass) {
      try {
        ValueSpecializedFactory vpf = this.spzFactories.get(clazz);
        if (vpf.workValue(fieldName, keyField)) {
          return vpf;
        }
      } catch (ValueSpecializedException ex) {
        LogInfo.logErrorInformation(ex.getOrigemClass().getSimpleName(),
            I18N.getMsg(ex.getMsgPropertieKey(), (Object[]) ex.getVarArgMsgVariations()),
            ex.getCause());
        throw ex;
      }
    }
    return null;
  }

  /**
   * Clear all objects put at specializedFactories.
   */
  protected void clear() {
    this.spzFactories.clear();
  }

}
