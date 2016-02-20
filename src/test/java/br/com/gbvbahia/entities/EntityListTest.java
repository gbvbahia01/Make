/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entities;

import java.util.List;

/**
 *
 * @author Guilherme
 */
public class EntityListTest {

    private List<EntityPatternTest> listPattern;
    private List<EntityListComplexTest> listComplex;

    public List<EntityListComplexTest> getListComplex() {
        return listComplex;
    }

    public List<EntityPatternTest> getListPattern() {
        return listPattern;
    }

    @Override
    public String toString() {
        return "EntityListTest{"
                + "listPattern=" + listPattern
                + "listComplex=" + listComplex
                + '}';
    }
}
