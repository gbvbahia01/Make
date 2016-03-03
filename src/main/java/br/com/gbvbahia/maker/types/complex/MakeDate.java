package br.com.gbvbahia.maker.types.complex;

import java.util.Date;

/**
 * Random Date maker.<br>
 * Random period is limited in 1800 days forward or behind.
 *
 * @since v.1 24/05/2012
 * @author Guilherme
 */
public final class MakeDate {

  /**
   * A Date in the future limited to 1800 days forward.
   *
   * @return Date in the future.
   */
  public static Date getInFuture() {
    return MakeCalendar.getInFuture().getTime();
  }

  /**
   * A Date in the past limited to 1800 days behind.
   *
   * @return Date in the past
   */
  public static Date getInPast() {
    return MakeCalendar.getInPast().getTime();
  }

  /**
   * A Date in the past limited to 1800 days behind or a Date in the future limited to 1800 days
   * forward.
   *
   * @return Date in the past or future.
   */
  public static Date getDate() {
    return MakeCalendar.getCalendar().getTime();
  }

  /**
   * Cannot be instantiated.
   */
  private MakeDate() {}
}
