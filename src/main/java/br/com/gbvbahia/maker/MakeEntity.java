/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.string.MakeCharacter;
import br.com.gbvbahia.maker.wrappers.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.validation.constraints.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @since v.1 01/05/2012
 * @author Guilherme
 */
public class MakeEntity {

    private static Log logger = LogFactory.getLog("MakeEntity");

    public static <T> T makeEntity(Class<T> entity) {
        try {
            T toReturn = entity.newInstance();
            for (Field f : toReturn.getClass().getDeclaredFields()) {
                logger.debug("Type: " + f.getType().toString());
                boolean accessField = f.isAccessible();
                try {
                    f.setAccessible(true);
                    if (!f.isAnnotationPresent(Null.class)) {
                        ValueFactory valueFactory = Factory.makeFactory(f);
                        valueFactory.makeValue(f, toReturn);
                    }
                    logger.debug("GenericString: "
                            + f.toGenericString()
                            + " value: "
                            + f.get(toReturn));
                } finally {
                    f.setAccessible(accessField);
                }
            }
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
     * Verifica o tipo do campo e insere o valor correspondente.
     *
     * @param <T> Generic que representa proprietário do campo. Por
     * exemplo: Classe carro tem um campo int rodas, o Field f seria
     * rodas e T seria a classe Carro que terá o campo definido.
     * @param f O campo que terá o valor definido.
     * @param toReturn Objeto que contém o Field.
     * @throws IllegalAccessException se no momento de execução não
     * houver acesso ao campo.
     * @throws IllegalArgumentException Se algum argumento anotado não
     * for válido.
     */
    private static <T> void insertValue(Field f, T toReturn)
            throws IllegalAccessException, IllegalArgumentException {
        if (f.getType().equals(Character.class)) {
            f.set(toReturn, MakeCharacter.getCharacter());
        } else if (f.getType().equals(char.class)) {
            f.set(toReturn, MakeCharacter.getCharacter().charValue());
        }
    }
}
