/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.string.MakeString;
import static br.com.gbvbahia.maker.types.string.MakeString.MAX_LENGTH_SUPPORTS;
import java.lang.reflect.Field;
import javax.validation.constraints.Size;

/**
 * Cria String com base na anotação @Size
 * (javax.validation.constraints.Size) da JSR303.<br> <b> Para
 * coleções de elementos esta classe não irá gerar os elementos para
 * serem inseridos na coleção. </b>
 *
 * @since v.1 26/05/2012
 * @author Guilherme
 */
public class SizeFactory implements ValueFactory {

    public <T> void makeValue(Field f, T entity)
            throws IllegalAccessException, IllegalArgumentException {
        int min = f.getAnnotation(Size.class).min();
        int max = f.getAnnotation(Size.class).max();
        if (max > MAX_LENGTH_SUPPORTS && min < MAX_LENGTH_SUPPORTS) {
            LogInfo.logWarnInformation("SizeFactory",
                    I18N.getMsg("sizeLengh", MAX_LENGTH_SUPPORTS, max));
            max = MAX_LENGTH_SUPPORTS;
        }
        if (f.getType().equals(String.class)) {
            f.set(entity, MakeString.getString(min, max,
                    MakeString.StringType.LETTER));
        }
    }
}
