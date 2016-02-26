package br.com.gbvbahia.entities;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityBooleanTest {

  @AssertFalse
  @NotNull
  private Boolean booleanObjectFalse;
  @AssertTrue
  @NotNull
  private Boolean booleanObjectTrue;
  @AssertFalse
  @NotNull
  private Boolean booleanPrimitiveFalse;
  @AssertTrue
  @NotNull
  private Boolean booleanPrimitiveTrue;
  @NotNull
  private Boolean booleanTrueOrFalse;
  @NotNull
  private boolean primitiveBoolean;
  @NotNull
  @AssertTrue
  private boolean primitiveTrueBoolean;
  @NotNull
  @AssertFalse
  private boolean primitiveFalseBoolean;

  public Boolean getBooleanObjectFalse() {
    return this.booleanObjectFalse;
  }

  public void setBooleanObjectFalse(Boolean booleanObjectFalse) {
    this.booleanObjectFalse = booleanObjectFalse;
  }

  public Boolean getBooleanObjectTrue() {
    return this.booleanObjectTrue;
  }

  public void setBooleanObjectTrue(Boolean booleanObjectTrue) {
    this.booleanObjectTrue = booleanObjectTrue;
  }

  public Boolean getBooleanPrimitiveFalse() {
    return this.booleanPrimitiveFalse;
  }

  public void setBooleanPrimitiveFalse(Boolean booleanPrimitiveFalse) {
    this.booleanPrimitiveFalse = booleanPrimitiveFalse;
  }

  public Boolean getBooleanPrimitiveTrue() {
    return this.booleanPrimitiveTrue;
  }

  public void setBooleanPrimitiveTrue(Boolean booleanPrimitiveTrue) {
    this.booleanPrimitiveTrue = booleanPrimitiveTrue;
  }

  public Boolean getBooleanTrueOrFalse() {
    return this.booleanTrueOrFalse;
  }

  public void setBooleanTrueOrFalse(Boolean booleanTrueOrFalse) {
    this.booleanTrueOrFalse = booleanTrueOrFalse;
  }

  @Override
  public String toString() {
    return "EntityBooleanTest{" + "booleanObjectFalse=" + this.booleanObjectFalse
        + ", booleanObjectTrue=" + this.booleanObjectTrue + ", booleanPrimitiveFalse="
        + this.booleanPrimitiveFalse + ", booleanPrimitiveTrue=" + this.booleanPrimitiveTrue
        + ", booleanTrueOrFalse=" + this.booleanTrueOrFalse + ", primitiveBoolean="
        + this.primitiveBoolean + ", primitiveTrueBoolean=" + this.primitiveTrueBoolean
        + ", primitiveFalseBoolean=" + this.primitiveFalseBoolean + '}';
  }
}
