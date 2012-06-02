/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.date;

import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.util.Calendar;

/**
 *
 * Cria uma data aleatória limitada em 1800 dias para frente, futuro
 * ou 1800 dias para traz, passado.
 *
 * @since v.1 24/05/2012
 * @author Guilherme
 */
public final class MakeCalendar {

    /**
     * Quantidade maxima de dias no intervalo.
     */
    public static final int MAX_DAYS = 1800;

    /**
     * Cria um Calendar no futuro, a partir de um dia a mais de hoje
     * até 1800 dias a frente.
     *
     * @return Calendar no futuro.
     */
    public static Calendar getInFuture() {
        Calendar calendar = Calendar.getInstance();
        int days = MakeInteger.getIntervalo(1, MAX_DAYS);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar;
    }

    /**
     * Cria um Calendar no passado, a aprtir de um dia a menos de hoje
     * até 1800 dias para traz.
     *
     * @return Calendar no passado.
     */
    public static Calendar getInPast() {
        Calendar calendar = Calendar.getInstance();
        int days = MakeInteger.getIntervalo(1, MAX_DAYS);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        return calendar;
    }

    /**
     * Cria um calendar no futuro ou passado, podendo ser até 1800
     * dias para frente ou 1800 dias para traz.
     *
     * @return Calendar passado ou futuro.
     */
    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        int days = MakeInteger.getIntervalo(-MAX_DAYS, MAX_DAYS);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar;
    }

    /**
     * Não pode ser instânciado.
     */
    private MakeCalendar() {
    }
}
