package br.com.gbvbahia.entities;

/**
 * @since v.2
 * @author Guilherme Braga
 *
 */
public class ConstructorDefault {

  private int fields;

  private float timeConstruction;

  public ConstructorNotDefault notDefault;

  public ConstructorDefault() {
    super();
  }

  /**
   * Create with values.
   * 
   * @param fields to test;
   * @param timeConstruction to test.
   */
  public ConstructorDefault(int fields, float timeConstruction) {
    super();
    this.fields = fields;
    this.timeConstruction = timeConstruction;
  }

  public int getFields() {
    return this.fields;
  }

  public void setFields(int fields) {
    this.fields = fields;
  }

  public float getTimeConstruction() {
    return this.timeConstruction;
  }

  public void setTimeConstruction(float timeConstruction) {
    this.timeConstruction = timeConstruction;
  }

  public ConstructorNotDefault getNotDefault() {
    return this.notDefault;
  }

  public void setNotDefault(ConstructorNotDefault notDefault) {
    this.notDefault = notDefault;
  }
}
