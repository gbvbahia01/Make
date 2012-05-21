/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.maker.string.MakeCharacter;
import br.com.gbvbahia.maker.wrappers.MakeBoolean;
import java.lang.reflect.Field;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Deve ser utilizado como <b>Factory Padrão</b>, para atributos
 * somente anotados com @NotNull.<br>
 * Extende NumberFactory, por já ter implementação de valores default
 * para algumas classes.
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class DefaultFactory extends NumberFactory {

    /**
     * Logger para depuração.
     */
    private static Log logger = LogFactory.getLog("DefaultFactory");

    @Override
    public <T> void makeValue(Field f, T entity) 
            throws IllegalAccessException, IllegalArgumentException {
        try {
            super.makeValue(f, entity);
        } catch (IllegalArgumentException ex) {
            if (f.getType().equals(Character.class)) {
                logger.debug("Default value for Character");
                f.set(entity, MakeCharacter.getCharacter());
            } else if (f.getType().equals(char.class)) {
                logger.debug("Default value for char");
                f.set(entity, MakeCharacter.getCharacter().charValue());
            } else if (f.getType().equals(Boolean.class)) {
                logger.debug("Default value for Boolean");
                f.set(entity, MakeBoolean.getBoolean());
            } else if (f.getType().equals(boolean.class)) {
                logger.debug("Default value for boolean");
                f.set(entity, MakeBoolean.getBoolean().booleanValue());
            }
        }
    }
}
