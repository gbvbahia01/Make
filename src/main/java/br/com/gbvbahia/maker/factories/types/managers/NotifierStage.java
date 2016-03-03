package br.com.gbvbahia.maker.factories.types.managers;

import java.util.ArrayList;
import java.util.List;

/**
 * This object has the duty to notify to notify all objects thats need to be informed about the
 * start and/or the end of the Make object creation.<br>
 * To add a object use the method addObserver(Observer observer)<br>
 * To remove a object first implement removeObserver(Observer observer) and after that use it;)<br>
 * To now the stage of the Make object creation use the constants NotifierStage.MAKE_BEGIN,
 * NotifierStage.MAKE_END, NotifierStage.MAKE_RECURSION_BEGIN, NotifierStage.MAKE_RECURSION_END.
 * Will be passed as argument of update method for all Observers.
 * 
 * @author Guilherme
 * @since v.2 02/24/2016
 *
 */
public class NotifierStage {

  /**
   * Use for all classes that needs to be informed about the stage of Make object creation is
   * changed.
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
   * Singleton of NotifierStage.
   */
  private static NotifierStage notifier = null;
  public static final Integer MAKE_BEGIN = 1;
  public static final Integer MAKE_END = 2;
  public static final Integer MAKE_RECURSION_BEGIN = 3;
  public static final Integer MAKE_RECURSION_END = 4;

  /**
   * Recover a instance of NotifierStage.
   * 
   * @return a Notifier object
   */
  public static synchronized NotifierStage getNotifyer() {
    if (notifier == null) {
      notifier = new NotifierStage();
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
  private NotifierStage() {}

  /**
   * Notify all Observers that the Make object creation started. Need to be called after
   * Factory.configureFactories(testName) because all factories are listener of stage Make object
   * creation and they will only be made after this call.
   * 
   * @param testName the tests names that will be used.
   */
  public void notifyMakeBegin(String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(MAKE_BEGIN, testName));
    }
  }

  /**
   * Notify all observer about recursion in the Make object creation is about to begin. Amount of
   * calls made at MakeEntity.makeEntity method after first. If the value is 1 means that: two calls
   * are made: the first call by developer and the second call made by framework to create a new
   * complex object.
   * 
   * @param amount of recursion made by framework.
   * @param testName the name of the test.
   */
  public void notifyMakeRecursionBegin(Integer amount, String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(MAKE_RECURSION_BEGIN, amount, testName));
    }
  }

  /**
   * Notify all observer about recursion in the Make object creation is finished. Amount of calls
   * made at MakeEntity.makeEntity method after first. If the value is 1 means that: two calls are
   * made: the first call by developer and the second call made by framework to create a new complex
   * object.
   * 
   * @param amount of recursion made by framework.
   * @param testName the name of the test.
   */
  public void notifyMakeRecursionEnd(Integer amount, String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(MAKE_RECURSION_END, amount, testName));
    }
  }

  /**
   * Notify all Observers that the Make object creation end.
   */
  public void notifyMakeEnd(String... testName) {
    for (Notified observer : this.observers) {
      observer.updateStage(new Notification(MAKE_END, testName));
    }
    this.objectCreationOver();
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
   * Must be called when the Make object creation is over.
   */
  private void objectCreationOver() {
    this.observers.clear();
  }
}
