package br.com.gbvbahia.maker.factories;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.DefaultFactory;
import br.com.gbvbahia.maker.factories.types.EnumFactory;
import br.com.gbvbahia.maker.factories.types.FuturePastFactory;
import br.com.gbvbahia.maker.factories.types.MaxMinFactory;
import br.com.gbvbahia.maker.factories.types.SizeFactory;
import br.com.gbvbahia.maker.factories.types.TrueFalseFactory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.managers.ValueFactoryManager;
import br.com.gbvbahia.maker.factories.types.managers.XMLoader;
import br.com.gbvbahia.maker.factories.types.works.DefaultValuesFactory;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * This class will call the XML loader to read the xml setup and prepare all values factories to
 * create the values.
 * 
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public final class Factory {

  /**
   * For logging changes edit the log4j.properties inside src/test/resources
   */
  private static Log logger = LogFactory.getLog(Factory.class.getSimpleName());

  /**
   * Configura o nome do teste para recuperar informações no arquivo make.properties.
   */
  private static String[] testName;
  /**
   * Setup is instantiated but not configured.
   */
  public static Setup SETUP = new Setup(null);

  /**
   * Contém uma lista das Factories para cada tipo, ao ser solicitado uma será retornada.
   */
  public static final Set<ValueFactory> FACTORIES = new LinkedHashSet<ValueFactory>();

  private static ValueFactoryManager specializedFactoryManager = null;

  /**
   * If you need another xml file for setup or you want rename make.xml you must call this method
   * before start tests.
   * 
   * @param xmlSetupFile the xml setup file to load all test setup.
   */
  public static void loadSetup(String xmlSetupFile) {
    SETUP = new Setup(XMLoader.getLoader(xmlSetupFile).loadSetup());
  }


  /**
   * Start to read the xml to setup all factories that will be used in the test.
   * 
   * @param testNameProp all tests that will be loaded.
   */
  public static void configureFactories(final String... testNameProp) {
    if ((SETUP == null) || !SETUP.isCreated()) {
      Factory.logger.info(I18N.getMsg("setupMakeXmlDefault"));
      Factory.logger.info(I18N.getMsg("warnAboutLoadXml"));
      loadSetup(null);
    }
    FACTORIES.clear();
    Factory.testName = testNameProp;
    Factory.specializedFactoryManager = new ValueFactoryManager(testNameProp);
    // This order is important do not change.
    FACTORIES.add(Factory.specializedFactoryManager);
    FACTORIES.add(SizeFactory.getInstance());
    FACTORIES.add(MaxMinFactory.getInstance());
    FACTORIES.add(FuturePastFactory.getInstance());
    FACTORIES.add(TrueFalseFactory.getInstance());
    FACTORIES.add(EnumFactory.getInstance());
    DefaultFactory.loadInstance();
  }

  /**
   * Run all factories looking for the one that works with the field. If no one factory works with
   * then DefaultFactory will be used.<br>
   * Keep attention in xml file setup test like make.xml. The values created will be reflection from
   * this file.
   *
   * @param <T> Represent the class of entity
   * @param field to be created
   * @param entity entity that contains the field.
   * @return ValueFactory that will create the value to put at field.
   */
  public static <T> ValueFactory getFactory(final Field field, final T entity) {
    if (SETUP.useDefaultValuesFactory(field, entity)) {
      return DefaultValuesFactory.getInstance();
    }
    for (ValueFactory vf : FACTORIES) {
      if (vf.isWorkWith(field, entity)) {
        return vf;
      }
    }
    return DefaultFactory.getInstance(testName);
  }



  /**
   * Não pode ser instânciado.
   */
  private Factory() {}

  /**
   * Where xml setup is kept for all tests.
   * 
   * @author Guilherme Braga
   *
   */
  public static final class Setup {
    private static final String JSR303_READ = "read";
    private static final String JSR303_IGNORE = "ignore";
    private static final String NULL_ALWAYS = "all";
    private static final String NULL_SOME = "some";
    private static final String NULL_NEVER = "never";
    private String jsr303;
    private String nullFields;
    private boolean created;

    /**
     * Define all behavior of framework reading the setupMap.<br>
     * If a null or empty map is sent the setup rules will not be loaded but any exception will be
     * launched.<br>
     * To load setup again call Factory.loadSetup(nameXmlSetup) with correctly name of XML setup
     * file.
     * 
     * @param setupMap a map with all rules declared in XML setup file.
     */
    public Setup(Map<String, String> setupMap) {
      this.created = false;
      if ((setupMap == null) || setupMap.isEmpty()) {
        return;
      }
      this.changeSetup(setupMap);
      this.created = true;
    }

    private void changeSetup(Map<String, String> setupMap) {
      this.jsr303 = setupMap.get("JSR303");
      this.nullFields = setupMap.get("Null");
      this.checkJsr303SetupValue();
      this.checkNullSetupValue();
    }

    private void checkJsr303SetupValue() {
      if (StringUtils.equals(this.jsr303, JSR303_READ)) {
        return;
      }
      if (StringUtils.equals(this.jsr303, JSR303_IGNORE)) {
        return;
      }
      throw new IllegalArgumentException(
          I18N.getMsg("JSR303SetupError", new Object[] {JSR303_READ, JSR303_IGNORE}));
    }

    private void checkNullSetupValue() {
      if (StringUtils.equals(this.nullFields, NULL_ALWAYS)) {
        return;
      }
      if (StringUtils.equals(this.nullFields, NULL_SOME)) {
        return;
      }
      if (StringUtils.equals(this.nullFields, NULL_NEVER)) {
        return;
      }
      throw new IllegalArgumentException(
          I18N.getMsg("NullSetupError", new Object[] {NULL_ALWAYS, NULL_SOME, NULL_NEVER}));
    }

    /**
     * Engine that defines the behavior of the test reading the setup test information.<br>
     * 
     */
    private <T> boolean useDefaultValuesFactory(final Field field, final T entity) {
      if (this.readJsr303()) {
        if (field.isAnnotationPresent(Null.class)) {
          return true;
        }
        if (field.isAnnotationPresent(NotNull.class)) {
          return false;
        }
        if (this.neverNull()) {
          return false;
        }
        if (isKeyField(field)) {
          return false;
        }
        if (this.alwaysNull()) {
          return true;
        }
        if (this.someNull() && (MakeInteger.getIntervalo(1, 6) == 3)) {
          return true;
        }
        return false;
      } else {
        if (this.neverNull() || isKeyField(field)) {
          return false;
        }
        if (this.alwaysNull()) {
          return true;
        }
        if (this.someNull()) {
          if (MakeInteger.getIntervalo(1, 6) == 3) {
            return true;
          }
        }
        return false;
      }
    }

    /**
     * Check if the field is mapped to be worked for some work class. MakeBetween, MakeEmail...
     * 
     * @param field the field to check
     * @return true is mapped false is not.
     */
    private static boolean isKeyField(Field field) {
      String key = field.getDeclaringClass().getName() + "." + field.getName();
      return Factory.specializedFactoryManager.isFieldMapped(key);
    }

    public boolean readJsr303() {
      return JSR303_READ.equals(this.jsr303);
    }

    public boolean ignoreJsr303() {
      return JSR303_IGNORE.equals(this.jsr303);
    }

    public boolean alwaysNull() {
      return NULL_ALWAYS.equals(this.nullFields);
    }

    public boolean someNull() {
      return NULL_SOME.equals(this.nullFields);
    }

    public boolean neverNull() {
      return NULL_NEVER.equals(this.nullFields);
    }

    /**
     * If setup was defined with a valid map this will be true.
     * 
     * @return true if all SETUP was set and false if not.
     */
    public boolean isCreated() {
      return this.created;
    }

  }
}
