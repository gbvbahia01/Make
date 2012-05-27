/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.types.string.MakeCharacter;
import br.com.gbvbahia.maker.types.string.MakeString;
import br.com.gbvbahia.maker.types.wrappers.MakeBoolean;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

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
        if (f.getType().equals(String.class)) {
            LogInfo.logDefaultValue(entity, f, "DefaultFactory");
            f.set(entity,
                    MakeString.getString(MakeString.MIN_LENGTH_DEFAULT,
                    MakeString.MAX_LENGTH_DEFAULT,
                    MakeString.StringType.LETTER));
            return;
        }
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
            } else if (isDate(f)) {
                LogInfo.logDefaultValue(entity, f, "DateFactory");
                new DateFactory().makeValue(f, entity);
            } else {
                throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoDefault"));
            }
        }
    }

    /**
     * Verifica se o field é do tipo Date ou Calendar.
     *
     * @param f Atributo da classe.
     * @return True para Calendar ou Date, false se não for.
     */
    private boolean isDate(Field f) {
        if (f.getType().equals(Date.class)
                || f.getType().equals(Calendar.class)) {
            return true;
        }
        return false;
    }
}
