package br.com.gbvbahia.maker.types.complex;

import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;

import java.util.Calendar;

/**
 * Random Calendar maker.<br>
 * Random period is limited in 1800 days forward or behind.
 *
 * @since v.1 24/05/2012
 * @author Guilherme
 */
public final class MakeCalendar {

  /**
   * Max days forward or behind.
   */
  public static final int MAX_DAYS = 1800;

  /**
   * A Calendar in the future limited to 1800 days forward.
   *
   * @return Calendar in the future.
   */
  public static Calendar getInFuture() {
    Calendar calendar = Calendar.getInstance();
    int days = MakeInteger.getRange(1, MAX_DAYS);
    calendar.add(Calendar.DAY_OF_MONTH, days);
    return calendar;
  }

  /**
   * A Calendar in the past limited to 1800 days behind.
   *
   * @return Calendar in the past.
   */
  public static Calendar getInPast() {
    Calendar calendar = Calendar.getInstance();
    int days = MakeInteger.getRange(1, MAX_DAYS);
    calendar.add(Calendar.DAY_OF_MONTH, -days);
    return calendar;
  }

  /**
   * A Calendar in the past limited to 1800 days behind or a Calendar in the future limited to 1800
   * days forward.
   * 
   * @return Calendar in the past or future.
   */
  public static Calendar getCalendar() {
    Calendar calendar = Calendar.getInstance();
    int days = MakeInteger.getRange(-MAX_DAYS, MAX_DAYS);
    calendar.add(Calendar.DAY_OF_MONTH, days);
    return calendar;
  }

  /**
   * Cannot be instantiated.
   */
  private MakeCalendar() {}
}
