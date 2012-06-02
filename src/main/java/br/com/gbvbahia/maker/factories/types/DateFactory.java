/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.date.MakeCalendar;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

/**
 *
 * @author Guilherme
 */
public class DateFactory implements ValueFactory {

    public <T> void makeValue(Field f, T entity, boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        if (f.getType().equals(Date.class)) {
            f.set(entity, valueTime(f).getTime());
        } else if (f.getType().equals(Calendar.class)) {
            f.set(entity, valueTime(f));
        }
    }

    private Calendar valueTime(Field f) {
        if (f.isAnnotationPresent(Future.class)) {
            return MakeCalendar.getInFuture();
        } else if (f.isAnnotationPresent(Past.class)) {
            return MakeCalendar.getInPast();
        }
        return MakeCalendar.getCalendar();
    }
}
