package br.com.gbvbahia.entities;

import java.util.List;

/**
 * @since v.1
 * @author Guilherme
 */
public class EntityListTest {

  private List<EntityPatternTest> listPattern;
  private List<EntityListComplexTest> listComplex;

  public List<EntityListComplexTest> getListComplex() {
    return this.listComplex;
  }

  public List<EntityPatternTest> getListPattern() {
    return this.listPattern;
  }

  @Override
  public String toString() {
    return "EntityListTest{" + "listPattern=" + this.listPattern + "listComplex=" + this.listComplex
        + '}';
  }
}
