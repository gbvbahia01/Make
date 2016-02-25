package br.com.gbvbahia.maker.factories.types.managers;

/**
 * The object passed when a notification need to be sent.
 * 
 * @since v.2 02/24/2016
 * @author Guilherme Braga
 *
 */
public class Notification {

  /**
   * Must be a value in a NotifierTests constants range: TEST_BEGIN or TEST_END.
   */
  private Integer stageTest;
  private String[] testName;
  private Integer recursion = 0;

  /**
   * Create a notification with information about the test.
   * 
   * @param stageTest need to be informed and the value must be a NotifierTests constant.
   * @param testName The same value passed at makeEntity method.
   */
  public Notification(Integer stageTest, String[] testName) {
    super();
    this.stageTest = stageTest;
    this.testName = testName;
  }

  /**
   * Create a notification with information about the test.<br>
   * 
   * @param stageTest need to be informed and the value must be a NotifierTests constant.
   * @param recursion amount of calls did at MakeEntity.makeEntity.
   * @param testName The same value passed at makeEntity method.
   */
  public Notification(Integer stageTest, Integer recursion, String[] testName) {
    super();
    this.stageTest = stageTest;
    this.testName = testName;
    this.recursion = recursion;
  }

  public boolean isTestStarted() {
    return NotifierTests.TEST_BEGIN.equals(this.stageTest);
  }

  public boolean isTestFinished() {
    return NotifierTests.TEST_END.equals(this.stageTest);
  }

  public boolean isTestInRecursion() {
    return this.recursion >= 1;
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


}
