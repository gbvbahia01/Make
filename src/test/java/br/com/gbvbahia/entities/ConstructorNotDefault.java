package br.com.gbvbahia.entities;

public class ConstructorNotDefault {

  private int fields;

  private float timeConstruction;

  private ConstructorNotDefault(int fields, float timeConstruction) {
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
}
