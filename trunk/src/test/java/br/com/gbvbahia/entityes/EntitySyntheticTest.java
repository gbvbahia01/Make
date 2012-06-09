/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
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
        return "EntitySyntheticTest{"
                + "notNullTest=" + notNullTest
                + ", listBooleanTests=" + listBooleanTests
                + ", setDateTest=" + setDateTest
                + ", mapTest=" + mapTest
                + ", noEntity=" + noEntity
                + '}';
    }

    public EntitySyntheticCicleTest getCicleTest() {
        return cicleTest;
    }

    public List<EntityBooleanTest> getListBooleanTests() {
        return listBooleanTests;
    }

    public Map<String, Double> getMapTest() {
        return mapTest;
    }

    public EntityNotNullTest getNotNullTest() {
        return notNullTest;
    }

    public Set<EntityDateTest> getSetDateTest() {
        return setDateTest;
    }

    public EntityEnumTest getNoEntity() {
        return noEntity;
    }
    
}
