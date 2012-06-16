/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public final class MakeEntity {

    /**
     * Logger de informações ao desenvolvedor.
     */
    private static Log logger = LogFactory.getLog(MakeEntity.class.getSimpleName());

    /**
     * Cria a entidade com atributos populados, somente os que tiverem
     * a anotação javax.validation.constraints.NotNull, Coleções, Set,
     * List e Map não serão criados. Relacionamentos com outras
     * classes, somente terão objetos criados se a classe relacionada
     * contiver a anotação javax.persistence.Entity.
     *
     * @param <T> Tipo de retorno, é o mesmo tipo da classe que foi
     * criada.
     * @param testName representa o nome do teste, não é obrigatório,
     * mas deve ser utilizado se for ler propriedades do arquivo
     * make.properties.
     * @param entity Classe da entidade a ser populada.
     * @param makeRelationships True irá criar objetos para ManyToOne
     * ou OneToOne <b>que tenham anotação</b>
     * javax.persistence.Entity.
     * @return Uma entidade populada com base na JSR303.
     */
    public static <T> T makeEntity(
            final String testName,
            final Class<T> entity,
            final boolean makeRelationships) {
        try {
            LogInfo.logMakeStartDebug(MakeEntity.class.getSimpleName(), entity);
            T toReturn = entity.newInstance();
            Factory.configureProperties(testName);
            for (Field f : toReturn.getClass().getDeclaredFields()) {
                boolean accessField = f.isAccessible();
                try {
                    f.setAccessible(true);
                    try {
                        ValueFactory valueFactory =
                                Factory.makeFactory(f, entity);
                        valueFactory.makeValue(testName, f, toReturn,
                                makeRelationships);
                    } catch (IllegalArgumentException e) {
                        LogInfo.logFieldNull(MakeEntity.class.getSimpleName(), f);
                    }
                    LogInfo.logFieldDebug(MakeEntity.class.getSimpleName(), f, toReturn);
                } finally {
                    f.setAccessible(accessField);
                }
            }
            LogInfo.logMakeEndDebug(MakeEntity.class.getSimpleName(),
                    entity);
            return toReturn;
        } catch (InstantiationException ex) {
            logger.error(I18N.getMsg("instantiationException",
                    entity.getName()), ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            logger.error(I18N.getMsg("illegalAccessException",
                    entity.getName()), ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Utilize para criar uma lista de entidades.
     *
     * @param <T> Tipo da entidade solicitada.
     * @param entity Classe da entidade solicitada.
     * @param amount Quantidade de entidades criadas dentro da lista.
     * @param makeRelationships Se entidades relacionadas devem ser
     * criados
     * @return Lista com a quantidade de entidades solicitadas em
     * amount.
     */
    public static <T> List<T> makeEntitys(
            final String testName,
            final Class<T> entity,
            final int amount,
            final boolean makeRelationships) {
        if (amount < 1) {
            throw new IllegalArgumentException(I18N.getMsg("qutdadeEntityInvalida", amount));
        }
        List<T> toReturn = new ArrayList<T>();
        for (int i = 0; i < amount; i++) {
            toReturn.add(makeEntity(testName, entity, makeRelationships));
        }
        return toReturn;
    }

    /**
     * Não pode ser instânciado.
     */
    private MakeEntity() {
    }
}
