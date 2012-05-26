/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.string.MakeCharacter;
import br.com.gbvbahia.maker.types.wrappers.MakeBoolean;
import java.lang.reflect.Field;

/**
 * Deve ser utilizado como <b>Factory Padrão</b>, para atributos
 * somente anotados com @NotNull.<br> Extende NumberFactory, por já
 * ter implementação de valores default para algumas classes.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class DefaultFactory extends NumberFactory {

    @Override
    public <T> void makeValue(Field f, T entity)
            throws IllegalAccessException, IllegalArgumentException {
        try {
            super.makeValue(f, entity);
        } catch (IllegalArgumentException ex) {
            if (f.getType().equals(Character.class)) {
                LogInfo.logDefaultValue(entity, f, "DefaultFactory");
                f.set(entity, MakeCharacter.getCharacter());
            } else if (f.getType().equals(char.class)) {
                LogInfo.logDefaultValue(entity, f, "DefaultFactory");
                f.set(entity, MakeCharacter.getCharacter().charValue());
            } else if (f.getType().equals(Boolean.class)) {
                LogInfo.logDefaultValue(entity, f, "DefaultFactory");
                f.set(entity, MakeBoolean.getBoolean());
            } else if (f.getType().equals(boolean.class)) {
                LogInfo.logDefaultValue(entity, f, "DefaultFactory");
                f.set(entity, MakeBoolean.getBoolean().booleanValue());
            } else {
                throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoDefault"));
            }
        }
    }


}
