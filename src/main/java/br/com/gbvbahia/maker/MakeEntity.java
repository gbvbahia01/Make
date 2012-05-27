/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.LogInfo;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Null;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEntity {

    private static Log logger = LogFactory.getLog("MakeEntity");
    /**
     * Devido a complexidade de gerar uma string com base em uma
     * expressão regular é disponibilizado uma forma de passar várias
     * strings que atendam um field.<br> key: NomeClasse.nomeFiled
     * valor: Uma lista com várias possibilidades para o field. Minimo
     * de uma possibilidade deve ser inserida.
     */
    public static Map<String, List<String>> patternsList;

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
     * @param patterns Map com o nome da classe e do campo a ser
     * definido. E uma lista de possibilidades.
     * @return Uma entidade populada com base na JSR303.
     */
    public static <T> T makeEntity(Class<T> entity,
            Map<String, List<String>> patterns) {
        patternsList = patterns;
        return makeEntity(entity);
    }

    public static <T> T makeEntity(Class<T> entity) {
        try {
            LogInfo.logMakeStartInfo("MakeEntity", entity);
            T toReturn = entity.newInstance();
            
            for (Field f : toReturn.getClass().getDeclaredFields()) {
                boolean accessField = f.isAccessible();
                try {
                    f.setAccessible(true);
                    if (!f.isAnnotationPresent(Null.class)) {
                        ValueFactory valueFactory = Factory.makeFactory(f);
                        valueFactory.makeValue(f, toReturn);
                    }
                    LogInfo.logFieldInfo("MakeEntity", f, toReturn);
                } finally {
                    f.setAccessible(accessField);
                }
            }
            logger.info("********************//********************");
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
}
