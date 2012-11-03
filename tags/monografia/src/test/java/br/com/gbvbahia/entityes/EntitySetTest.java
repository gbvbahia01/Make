/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import java.util.Set;

/**
 *
 * @author Guilherme
 */
public class EntitySetTest {

    private Set<EntityPatternTest> setPattern;
    private Set<EntitySetComplexTest> setComplex;

    public Set<EntitySetComplexTest> getSetComplex() {
        return setComplex;
    }

    public Set<EntityPatternTest> getSetPattern() {
        return setPattern;
    }

    @Override
    public String toString() {
        return "EntityListTest{"
                + "listPattern=" + setPattern
                + "listComplex=" + setComplex
                + '}';
    }
}
