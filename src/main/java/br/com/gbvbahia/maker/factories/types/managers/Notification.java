package br.com.gbvbahia.maker.factories.types.managers;

import java.util.Arrays;

/**
 * The object passed when a notification need to be sent.
 * 
 * @since v.2 02/24/2016
 * @author Guilherme Braga
 *
 */
public class Notification {

  /**
   * Must be a value in a NotifierStage constants range: MAKE_BEGIN, MAKE_END, MAKE_RECURSION_BEGIN
   * or MAKE_RECURSION_END.
   */
  private Integer stageMake;
  private String[] testName;
  /**
   * If the value is 1 means that: two calls are made: the first call by developer and the second
   * call made by framework to create a new complex object.
   */
  private Integer recursion = 0;

  /**
   * Create a notification with information about the Make creation stage.
   * 
   * @param stageMake need to be informed and the value must be a NotifierStage constant.
   * @param testName The same value passed at makeEntity method.
   */
  public Notification(Integer stageMake, String[] testName) {
    super();
    this.stageMake = stageMake;
    this.testName = testName;
  }

  /**
   * Create a notification with information about the make creation stage.<br>
   * 
   * @param stageMake need to be informed and the value must be a NotifierStage constant.
   * @param recursion amount of calls did at MakeEntity.makeEntity.
   * @param testName The same value passed at makeEntity method.
   */
  public Notification(Integer stageMake, Integer recursion, String[] testName) {
    super();
    this.stageMake = stageMake;
    this.testName = testName;
    this.recursion = recursion;
  }

  public boolean isTestStarted() {
    return NotifierStage.MAKE_BEGIN.equals(this.stageMake);
  }

  public boolean isTestFinished() {
    return NotifierStage.MAKE_END.equals(this.stageMake);
  }

  public boolean isTestRecursionBegin() {
    return (this.recursion >= 1) && NotifierStage.MAKE_RECURSION_BEGIN.equals(this.stageMake);
  }

  public boolean isTestRecursionEnd() {
    return (this.recursion >= 1) && NotifierStage.MAKE_RECURSION_END.equals(this.stageMake);
  }

  public String[] getTestName() {
    return this.testName;
  }

  /**
   * Amount of calls made at MakeEntity.makeEntity method after first. If the value is 1 means that:
   * two calls are made: the first call by developer and the second call made by framework to create
   * a new complex object.
   * 
   * @return amount of call made i MakeEntity.makeEntity by framework.
   */
  public Integer getRecursion() {
    return this.recursion;
  }

  /**
   * public static final Integer MAKE_BEGIN = 1; public static final Integer MAKE_END = 2; public
   * static final Integer MAKE_RECURSION_BEGIN = 3; public static final Integer MAKE_RECURSION_END =
   * 4;
   * 
   * @since v.2
   */
  @Override
  public String toString() {
    String stage = "";
    if (NotifierStage.MAKE_BEGIN.equals(this.stageMake)) {
      stage = "Make begin";
    }
    if (NotifierStage.MAKE_END.equals(this.stageMake)) {
      stage = "Make end";
    }
    if (NotifierStage.MAKE_RECURSION_BEGIN.equals(this.stageMake)) {
      stage = "Make recursion begin";
    }
    if (NotifierStage.MAKE_RECURSION_END.equals(this.stageMake)) {
      stage = "Make recursion end";
    }
    return "Notification [ recursion:" + this.recursion + " stageMake:" + stage + ", testName:"
        + Arrays.toString(this.testName) + "]";
  }
}
