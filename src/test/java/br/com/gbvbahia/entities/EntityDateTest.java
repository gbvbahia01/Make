package br.com.gbvbahia.entities;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * @since v.1
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
    return this.inFutureDate;
  }

  public void setInFutureDate(Date inFutureDate) {
    this.inFutureDate = inFutureDate;
  }

  public Date getNoNullDate() {
    return this.noNullDate;
  }

  public void setNoNullDate(Date noNullDate) {
    this.noNullDate = noNullDate;
  }

  public Date getInPassDate() {
    return this.inPassDate;
  }

  public void setInPassDate(Date inPassDate) {
    this.inPassDate = inPassDate;
  }

  public Calendar getNoNullCalendar() {
    return this.noNullCalendar;
  }

  public void setNoNullCalendar(Calendar noNullCalendar) {
    this.noNullCalendar = noNullCalendar;
  }

  public Calendar getInFutureCalendar() {
    return this.inFutureCalendar;
  }

  public void setInFutureCalendar(Calendar inFutureCalendar) {
    this.inFutureCalendar = inFutureCalendar;
  }

  public Calendar getInPassCalendar() {
    return this.inPassCalendar;
  }

  public void setInPassCalendar(Calendar inPassCalendar) {
    this.inPassCalendar = inPassCalendar;
  }

  @Override
  public String toString() {
    return "EntityDateTest{" + "noNullDate=" + this.noNullDate + ", inFutureDate="
        + this.inFutureDate + ", inPassDate=" + this.inPassDate + ", noNullCalendar="
        + this.noNullCalendar.getTime() + ", inFutureCalendar=" + this.inFutureCalendar.getTime()
        + ", inPassCalendar=" + this.inPassCalendar.getTime() + '}';
  }
}
