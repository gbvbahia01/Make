/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.date;

import br.com.gbvbahia.maker.types.wrappers.MakeBoolean;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * Cria uma data aleatória limitada em 1800 dias para frente, futuro
 * ou 1800 dias para traz, passado.
 *
 * @since v.1 24/05/2012
 * @author Guilherme
 */
public class MakeDate {

    /**
     * Cria um Date no futuro, a partir de um dia a mais de hoje até
     * 1800 dias a frente.
     *
     * @return Date no futuro.
     */
    public static Date getInFuture() {
        return MakeCalendar.getInFuture().getTime();
    }

    /**
     * Cria um Date no passado, a aprtir de um dia a menos de hoje até
     * 1800 dias para traz.
     *
     * @return Date no passado.
     */
    public static Date getInPast() {
        return MakeCalendar.getInPast().getTime();
    }

    /**
     * Cria um Date no futuro ou passado, podendo ser até 1800 dias
     * para frente ou 1800 dias para traz.
     *
     * @return Date passdo ou futuro.
     */
    public static Date getDate() {
        return MakeCalendar.getCalendar().getTime();
    }
}
