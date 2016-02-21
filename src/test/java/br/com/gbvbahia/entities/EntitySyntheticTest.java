package br.com.gbvbahia.entities;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @since v.1 01/05/2012
 * @author Guilherme
 */
@Entity
public class EntitySyntheticTest {

  @NotNull
  private EntityEnumTest noEntity;

  @NotNull
  private EntitySyntheticCicleTest cicleTest;
  @NotNull
  private EntityNotNullTest notNullTest;
  @NotNull
  private List<EntityBooleanTest> listBooleanTests;
  private Set<EntityDateTest> setDateTest;
  @NotNull
  private Map<String, Double> mapTest;

  @Override
  public String toString() {
    return "EntitySyntheticTest{" + "notNullTest=" + this.notNullTest + ", listBooleanTests="
        + this.listBooleanTests + ", setDateTest=" + this.setDateTest + ", mapTest=" + this.mapTest
        + ", noEntity=" + this.noEntity + '}';
  }

  public EntitySyntheticCicleTest getCicleTest() {
    return this.cicleTest;
  }

  public List<EntityBooleanTest> getListBooleanTests() {
    return this.listBooleanTests;
  }

  public Map<String, Double> getMapTest() {
    return this.mapTest;
  }

  public EntityNotNullTest getNotNullTest() {
    return this.notNullTest;
  }

  public Set<EntityDateTest> getSetDateTest() {
    return this.setDateTest;
  }

  public EntityEnumTest getNoEntity() {
    return this.noEntity;
  }

}
