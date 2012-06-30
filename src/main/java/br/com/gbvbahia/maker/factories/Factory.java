/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.*;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.factories.types.properties.MakeWorks;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

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
    public static final List<ValueFactory> DEFAULT_FACTORIES =
            new ArrayList<ValueFactory>();

    /**
     * Carrega todas as Factories para a respectiva lista
     */
    static {
        DEFAULT_FACTORIES.add(new SizeFactory());
        DEFAULT_FACTORIES.add(new NumberFactory());
        DEFAULT_FACTORIES.add(new DateFactory());
        DEFAULT_FACTORIES.add(new TrueFalseFactory());
        DEFAULT_FACTORIES.add(new EnumFactory());
    }

    /**
     * Utilize para configurar informações do teste.
     *
     * @param testNameProp nome do teste configurado no arquivo
     * make.properties: Exemplo: test1.Usuario.email Onde test1 é o
     * nome do teste, Usuario a classe e email o field.
     */
    public static void configureProperties(final String testNameProp) {
        Factory.testName = testNameProp;
        if (isReservedName(testNameProp)) {
            throw new IllegalArgumentException(I18N.getMsg("workReserved",
                    MakeWorks.WORK_USER_IMPL));
        }
        if (DEFAULT_FACTORIES.get(0).equals(new MakeWorks(testNameProp))) {
            DEFAULT_FACTORIES.remove(0);
        }
        MakeWorks makeProperties = new MakeWorks(testNameProp);
        DEFAULT_FACTORIES.add(0, makeProperties);
    }

    /**
     * Verifica se foi utilizado nome resenvado do make no nome do
     * teste.
     *
     * @param testN nome do teste.
     * @return true se for reservado false se não.
     */
    private static boolean isReservedName(final String testN) {
        return StringUtils.equalsIgnoreCase(MakeWorks.WORK_USER_IMPL,
                StringUtils.substring(testN, 0,
                MakeWorks.WORK_USER_IMPL.length()));
    }

    /**
     * Percorre as factories até encontrar uma que trabalha com o
     * field passado, retornando a mesma, como ultima tentativa
     * retorna a DefaultFactory.
     *
     * @param <T> Tipo da entidade.
     * @param f Field a ser populado.
     * @param entity Entidade que contém o field.
     * @return ValueFactory capaz de popular o field.
     */
    public static <T> ValueFactory makeFactory(final Field f, final T entity) {
        for (ValueFactory vf : DEFAULT_FACTORIES) {
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
