/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types.works.commons;

import br.com.gbvbahia.maker.factories.types.common.ValueFactory;

/**
 * Esta interface irá definir o valor no field, atravéz de
 * ValueFactory, mas antes responde se trabalha com o mesmo através da
 * resposta de workValue.
 *
 * @since 09/06/2012
 * @author Guilherme
 */
public interface ValueSpecializedFactory extends ValueFactory {

    /**
     * Recebe o valor declarado no propertie e responde se trabalha
     * com o mesmo, true para sim, false para não.
     *
     * @param value Valor inserido na propriedade do arquivo
     * make.properties.
     * @return True para trabalha e false para não trabalha.
     */
    public boolean workValue(String value);
}
