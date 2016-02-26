package br.com.gbvbahia.entities;

import javax.validation.constraints.NotNull;

import br.com.gbvbahia.entityes.constantes.EnumCleanTest;
import br.com.gbvbahia.entityes.constantes.EnumExternalTest;

/**
 *
 * @author Guilherme
 */
public class EntityEnumTest2 {

  enum TestEnum {

    PRIMERO, SEGUNDO, TERCEIRO, QUARTO
  }

  @NotNull
  private TestEnum testEnum;
  @NotNull
  private EnumExternalTest enumExternalTest;
  @NotNull
  private EnumCleanTest enumCleanTest;

  public TestEnum getTestEnum() {
    return this.testEnum;
  }

  public void setTestEnum(TestEnum testEnum) {
    this.testEnum = testEnum;
  }

  public EnumExternalTest getEnumExternalTest() {
    return this.enumExternalTest;
  }

  public void setEnumExternalTest(EnumExternalTest enumExternalTest) {
    this.enumExternalTest = enumExternalTest;
  }

  public EnumCleanTest getEnumCleanTest() {
    return this.enumCleanTest;
  }

  public void setEnumCleanTest(EnumCleanTest enumCleanTest) {
    this.enumCleanTest = enumCleanTest;
  }

  @Override
  public String toString() {
    return "EntityEnumTest{" + "testEnum=" + this.testEnum + "enumExternalTest="
        + this.enumExternalTest + "enumCleanTest=" + this.enumCleanTest + '}';
  }
}
