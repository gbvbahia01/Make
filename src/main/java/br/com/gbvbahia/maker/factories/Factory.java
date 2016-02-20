package br.com.gbvbahia.maker.factories;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import br.com.gbvbahia.maker.factories.types.DefaultFactory;
import br.com.gbvbahia.maker.factories.types.DefaultValuesFactory;
import br.com.gbvbahia.maker.factories.types.EnumFactory;
import br.com.gbvbahia.maker.factories.types.FuturePastFactory;
import br.com.gbvbahia.maker.factories.types.MaxMinFactory;
import br.com.gbvbahia.maker.factories.types.SizeFactory;
import br.com.gbvbahia.maker.factories.types.TrueFalseFactory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.properties.MakeWorksFactory;
import br.com.gbvbahia.maker.factories.types.properties.XMLoader;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public final class Factory {

  /**
   * Configura o nome do teste para recuperar informações no arquivo make.properties.
   */
  private static String[] testName;
  public static final Setup SETUP;
  /**
   * Contém uma lista das Factories para cada tipo, ao ser solicitado uma será retornada.
   */
  public static final Set<ValueFactory> FACTORIES = new LinkedHashSet<ValueFactory>();

  static {
    SETUP = new Setup(XMLoader.getLoader().loadSetup());
  }

  private static MakeWorksFactory makeProperties = null;

  /**
   * Utilize para configurar informações do teste.
   * 
   * @param testNameProp nome do teste configurado no arquivo make.properties: Exemplo:
   *        test1.Usuario.email Onde test1 é o nome do teste, Usuario a classe e email o field.
   */
  public static void configureProperties(final String... testNameProp) {
    Factory.testName = testNameProp;
    Factory.makeProperties = new MakeWorksFactory(testNameProp);
    // This order is important do not change.
    FACTORIES.add(Factory.makeProperties);
    FACTORIES.add(new SizeFactory());
    FACTORIES.add(new MaxMinFactory());
    FACTORIES.add(new FuturePastFactory());
    FACTORIES.add(new TrueFalseFactory());
    FACTORIES.add(new EnumFactory());
  }

  /**
   * Percorre as factories até encontrar uma que trabalha com o field passado, retornando a mesma,
   * como ultima tentativa retorna a DefaultFactory.
   *
   * @param <T> Tipo da entidade.
   * @param f Field a ser populado.
   * @param entity Entidade que contém o field.
   * @return ValueFactory capaz de popular o field.
   */
  public static <T> ValueFactory makeFactory(final Field f, final T entity) {
    if (useDefaultValuesFactory(f, entity)) {
      return new DefaultValuesFactory();
    }
    for (ValueFactory vf : FACTORIES) {
      if (vf.isWorkWith(f, entity)) {
        return vf;
      }
    }
    return new DefaultFactory(testName);
  }

  private static <T> boolean useDefaultValuesFactory(final Field f, final T entity) {
    if (SETUP.neverNull() || isKeyField(f)) {
      return false;
    }
    if (SETUP.readJSR303()) {
      if (!f.isAnnotationPresent(NotNull.class)) {
        return true;
      }
      return false;
    }
    if (SETUP.alwaysNull()) {
      return true;
    }
    if (SETUP.someNull()) {
      if (MakeInteger.getIntervalo(1, 6) == 3) {
        return true;
      }
    }
    return false;
  }

  /**
   * br.com.gvt.testes.MockEntities.enities.Employee.age class
   * br.com.gvt.testes.MockEntities.enities.Employee.name
   * 
   * @param f
   * @return
   */
  private static boolean isKeyField(Field f) {
    String key = f.getDeclaringClass().getName() + "." + f.getName();
    return Factory.makeProperties.isFieldMapped(key);
  }

  /**
   * Não pode ser instânciado.
   */
  private Factory() {}

  public final static class Setup {
    private static final String JSR303_READ = "read";
    private static final String JSR303_IGNORE = "ignore";
    private static final String NULL_ALWAYS = "all";
    private static final String NULL_SOME = "some";
    private static final String NULL_NEVER = "never";
    private final String JSR303;
    private final String NULL_FIELDS;

    public Setup(Map<String, String> setupMap) {
      this.JSR303 = setupMap.get("JSR303");
      this.NULL_FIELDS = setupMap.get("Null");
    }

    public boolean readJSR303() {
      return this.JSR303.equals(JSR303_READ);
    }

    public boolean ignoreJSR303() {
      return this.JSR303.equals(JSR303_IGNORE);
    }

    public boolean alwaysNull() {
      return this.NULL_FIELDS.equals(NULL_ALWAYS);
    }

    public boolean someNull() {
      return this.NULL_FIELDS.equals(NULL_SOME);
    }

    public boolean neverNull() {
      return this.NULL_FIELDS.equals(NULL_NEVER);
    }
  }
}
