package br.com.gbvbahia.entities;

import java.util.Set;

/**
 * @since v.1
 * @author Guilherme
 */
public class EntitySetTest {

  private Set<EntityPatternTest> setPattern;
  private Set<EntitySetComplexTest> setComplex;

  public Set<EntitySetComplexTest> getSetComplex() {
    return this.setComplex;
  }

  public Set<EntityPatternTest> getSetPattern() {
    return this.setPattern;
  }

  @Override
  public String toString() {
    return "EntityListTest{" + "listPattern=" + this.setPattern + "listComplex=" + this.setComplex
        + '}';
  }
}
