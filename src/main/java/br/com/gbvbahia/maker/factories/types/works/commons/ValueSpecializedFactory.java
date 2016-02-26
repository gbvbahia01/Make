package br.com.gbvbahia.maker.factories.types.works.commons;

import br.com.gbvbahia.maker.factories.types.common.ValueFactory;

/**
 * Esta interface irá definir o valor no field, atravéz de ValueFactory, mas antes responde se
 * trabalha com o mesmo através da resposta de workValue.
 *
 * @since 09/06/2012
 * @author Guilherme
 */
public interface ValueSpecializedFactory extends ValueFactory {

  /**
   * Inform if can make the value for a field informed.
   * 
   * @param String relative to a name of a field declared in xml setup file.
   * @param value the rule put between node field in xml setup <field name="">HERE</field>.
   * @return true if can or false if cannot.
   */
  public boolean workValue(String fieldName, String value);
}
