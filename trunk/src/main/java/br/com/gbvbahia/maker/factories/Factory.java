/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories;

import br.com.gbvbahia.maker.factories.types.*;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.properties.MakeProperties;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public final class Factory {

    /**
     * Configura o nome do teste para recuperar informações no arquivo
     * make.properties.
     */
    private static String testName;
    /**
     * Contém uma lista das Factories para cada tipo, ao ser
     * solicitado uma será retornada.
     */
    public final static List<ValueFactory> defaultFactories =
            new ArrayList<ValueFactory>();

    /**
     * Carrega todas as Factories para a respectiva lista
     */
    static {
        defaultFactories.add(new SizeFactory());
        defaultFactories.add(new NumberFactory());
        defaultFactories.add(new DateFactory());
        defaultFactories.add(new TrueFalseFactory());
        defaultFactories.add(new EnumFactory());
    }

    /**
     * Utilize para configurar informações do teste.
     *
     * @param testNameProprerties nome do teste configurado no arquivo
     * make.properties: Exemplo: test1.Usuario.email Onde test1 é o
     * nome do teste, Usuario a classe e email o field.
     */
    public static void configureProperties(String testNameProprerties) {
        if(defaultFactories.get(0).equals(new MakeProperties(testName))){
            defaultFactories.remove(0);
        }
        Factory.testName = testNameProprerties;
        MakeProperties makeProperties = new MakeProperties(testName);
        defaultFactories.add(0, makeProperties);
    }

    public static <T> ValueFactory makeFactory(Field f, T entity) {
        for (ValueFactory vf : defaultFactories) {
            if (vf.isWorkWith(f, entity)) {
                return vf;
            }
        }
        return new DefaultFactory(testName);
    }

    /**
     * Não pode ser instânciado.
     */
    private Factory() {
    }
}
