package br.com.gbvbahia.maker.factories.types.managers;

import java.util.Observable;
import java.util.Observer;

/**
 * This is responsible to notify all objects thats need to be informed about the start and/or the
 * end of the test.<br>
 * To add a object use the method addObserver(Observer observer)<br>
 * To remove a object first implement removeObserver(Observer observer) and after that use it;)<br>
 * To now the satage of the test use the constants NotifierTests.TEST_BEGIN or
 * NotifierTests.TEST_END. Will be passed as argument of update method for all Observers.
 * 
 * @author Guilherme
 * @since v.2 02/24/2016
 *
 */
public class NotifierTests {

  // ==============
  // Static control
  // ==============
  /**
   * Singleton of NotifierTests.
   */
  private static NotifierTests notifier = null;
  public static final Integer TEST_BEGIN = 1;
  public static final Integer TEST_END = 2;

  /**
   * Recover a instance of NotifierTests.
   * 
   * @return a Notifier object
   */
  public static synchronized NotifierTests getNotifyer() {
    if (notifier == null) {
      notifier = new NotifierTests();
    }
    return notifier;
  }

  // ================
  // Instance control
  // ================
  private Observable reported;

  private NotifierTests() {
    this.reported = new Observable();
  }

  /**
   * Notify all Observers that the test started.
   */
  public void notifyTestBegin(String... testName) {
    this.reported.notifyObservers(new Notification(TEST_BEGIN, testName));
  }

  /**
   * Notify all Observers that the test end.
   */
  public void notifyTestEnd(String... testName) {
    this.reported.notifyObservers(new Notification(TEST_END, testName));
    this.testOver();
  }

  /**
   * Add a observer to be notified.
   * 
   * @param observer
   */
  public void addObserver(Observer observer) {
    this.reported.addObserver(observer);
  }

  /**
   * Must be called when the test is over.
   */
  private void testOver() {
    this.reported.deleteObservers();
  }
}
