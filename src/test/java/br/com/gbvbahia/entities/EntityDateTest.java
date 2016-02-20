/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entities;

import java.util.Calendar;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author Guilherme
 */
public class EntityDateTest {

    @NotNull
    private Date noNullDate;
    @Future
    @NotNull
    private Date inFutureDate;
    @Past
    @NotNull
    private Date inPassDate;
    @NotNull
    private Calendar noNullCalendar;
    @Future
    @NotNull
    private Calendar inFutureCalendar;
    @Past
    @NotNull
    private Calendar inPassCalendar;

    public Date getInFutureDate() {
        return inFutureDate;
    }

    public void setInFutureDate(Date inFutureDate) {
        this.inFutureDate = inFutureDate;
    }

    public Date getNoNullDate() {
        return noNullDate;
    }

    public void setNoNullDate(Date noNullDate) {
        this.noNullDate = noNullDate;
    }

    public Date getInPassDate() {
        return inPassDate;
    }

    public void setInPassDate(Date inPassDate) {
        this.inPassDate = inPassDate;
    }

    public Calendar getNoNullCalendar() {
        return noNullCalendar;
    }

    public void setNoNullCalendar(Calendar noNullCalendar) {
        this.noNullCalendar = noNullCalendar;
    }

    public Calendar getInFutureCalendar() {
        return inFutureCalendar;
    }

    public void setInFutureCalendar(Calendar inFutureCalendar) {
        this.inFutureCalendar = inFutureCalendar;
    }

    public Calendar getInPassCalendar() {
        return inPassCalendar;
    }

    public void setInPassCalendar(Calendar inPassCalendar) {
        this.inPassCalendar = inPassCalendar;
    }

    @Override
    public String toString() {
        return "EntityDateTest{"
                + "noNullDate=" + noNullDate
                + ", inFutureDate=" + inFutureDate
                + ", inPassDate=" + inPassDate
                + ", noNullCalendar=" + noNullCalendar.getTime()
                + ", inFutureCalendar=" + inFutureCalendar.getTime()
                + ", inPassCalendar=" + inPassCalendar.getTime()
                + '}';
    }
}
