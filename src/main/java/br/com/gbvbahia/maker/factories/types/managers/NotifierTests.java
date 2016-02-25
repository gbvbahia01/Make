package br.com.gbvbahia.maker.factories.types.managers;

import java.util.ArrayList;
import java.util.List;

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

  /**
   * Use for all classes that needs to be informed about the stage of test is changed.
   * 
   * @author Guilherme Braga
   *
   */
  public interface Notified {
    public void updateStage(Notification notification);
  }

  // ==============
  // Static control
  // ==============
  /**
   * Singleton of NotifierTests.
   */
  private static NotifierTests notifier = null;
  public static final Integer TEST_BEGIN = 1;
  public static final Integer TEST_END = 2;
  public static final Integer TEST_RECURSION_BEGIN = 3;
  public static final Integer TEST_RECURSION_END = 4;

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

  private List<Notified> observers = new ArrayList<Notified>();

  /**
   * Cannot be instantiated outside.
   */
  private NotifierTests() {}

  /**
   * Notify all Observers that the test started.
   */
  public void notifyTestBegin(String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(TEST_BEGIN, testName));
    }
  }

  /**
   * Notify all observer about recursion in the test is about to begin. Amount of calls made at
   * MakeEntity.makeEntity method after first. If the value is 1 means that: two calls are made: the
   * first call by developer and the second call made by framework to create a new complex object.
   * 
   * @param amount of recursion made by framework.
   * @param testName the name of the test.
   */
  public void notifyTestRecursionBegin(Integer amount, String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(TEST_RECURSION_BEGIN, amount, testName));
    }
  }

  /**
   * Notify all observer about recursion in the test is finished. Amount of calls made at
   * MakeEntity.makeEntity method after first. If the value is 1 means that: two calls are made: the
   * first call by developer and the second call made by framework to create a new complex object.
   * 
   * @param amount of recursion made by framework.
   * @param testName the name of the test.
   */
  public void notifyTestRecursionEnd(Integer amount, String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(TEST_RECURSION_END, amount, testName));
    }
  }

  /**
   * Notify all Observers that the test end.
   */
  public void notifyTestEnd(String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(TEST_END, testName));
    }
    this.testOver();
  }

  /**
   * Add a observer to be notified.
   * 
   * @param observer
   */
  public void addObserver(Notified observer) {
    this.observers.add(observer);
  }

  /**
   * Must be called when the test is over.
   */
  private void testOver() {
    this.observers.clear();
  }
}
