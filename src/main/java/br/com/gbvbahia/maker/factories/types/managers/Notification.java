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

  public boolean isTestStarted() {
    return NotifierTests.TEST_BEGIN.equals(this.stageTest);
  }

  public boolean isTestFinished() {
    return NotifierTests.TEST_END.equals(this.stageTest);
  }

  public String[] getTestName() {
    return this.testName;
  }
}
