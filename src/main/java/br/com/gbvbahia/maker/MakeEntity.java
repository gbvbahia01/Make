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
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
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
     * Utilize para passar possibilidades para serem inseridas nos
     * campos anotados com javax.validation.constraints.Pattern.<br> O
     * map deve ter como chave: O nome da classe mais o nome do field
     * anotado com pattern, sepados por ponto. Ex: Usuario.email<br> O
     * valor deve ser uma Lista, java.util.List, com as possibilidades
     * que podem ser utilizadas, pelo menos um valor deve ser
     * inserido, mais de um valor a seleção será randômica.
     *
     * @param <T> Tipo da entidade a se populada.
     * @param entity Classe da entidade a ser populada.
     * @param makeRelationships True irá criar objetos para ManyToOne
     * ou OneToOne <b>que tenham anotação</b>
     * javax.persistence.Entity.
     * @param patterns Map com o nome da classe e do campo a ser
     * definido. E uma lista de possibilidades.
     * @return Uma entidade populada com base na JSR303.
     */
    public static <T> T makeEntity(Class<T> entity, boolean makeRelationships,
            Map<String, List<String>> patterns) {
        try {
            LogInfo.logMakeStartInfo(MakeEntity.class.getSimpleName(), entity);
            T toReturn = entity.newInstance();
            for (Field f : toReturn.getClass().getDeclaredFields()) {
                boolean accessField = f.isAccessible();
                try {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(NotNull.class)) {
                        try {
                            ValueFactory valueFactory =
                                    Factory.makeFactory(f, patterns);
                            valueFactory.makeValue(f, toReturn,
                                    makeRelationships);
                        } catch (IllegalArgumentException e) {
                            LogInfo.logFieldNull(MakeEntity.class.getSimpleName(), f);
                        }
                        LogInfo.logFieldInfo(MakeEntity.class.getSimpleName(), f, toReturn);
                    }
                } finally {
                    f.setAccessible(accessField);
                }
            }
            LogInfo.logMakeEndInfo(MakeEntity.class.getSimpleName(),
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
     * Cria a entidade com atributos populados, somente os que tiverem
     * a anotação javax.validation.constraints.NotNull, Coleções, Set,
     * List e Map não serão criados. Relacionamentos com outras
     * classes, somente terão objetos criados se a classe relacionada
     * contiver a anotação javax.persistence.Entity.
     *
     * @param <T> Tipo da entidade a se populada.
     * @param entity Classe da entidade a ser populada.
     * @param makeRelationships True irá criar objetos para ManyToOne
     * ou OneToOne <b>que tenham anotação</b>
     * javax.persistence.Entity.
     * @return Uma entidade populada com base na JSR303.
     */
    public static <T> T makeEntity(Class<T> entity, boolean makeRelationships) {
        return makeEntity(entity, makeRelationships, null);
    }

    /**
     * Não pode ser instânciado.
     */
    private MakeEntity() {
    }
}
