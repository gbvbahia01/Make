/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import br.com.gbvbahia.entityes.constantes.EnumCleanTest;
import br.com.gbvbahia.entityes.constantes.EnumExternalTest;
import javax.validation.constraints.NotNull;

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
        return testEnum;
    }

    public void setTestEnum(TestEnum testEnum) {
        this.testEnum = testEnum;
    }

    public EnumExternalTest getEnumExternalTest() {
        return enumExternalTest;
    }

    public void setEnumExternalTest(EnumExternalTest enumExternalTest) {
        this.enumExternalTest = enumExternalTest;
    }

    public EnumCleanTest getEnumCleanTest() {
        return enumCleanTest;
    }

    public void setEnumCleanTest(EnumCleanTest enumCleanTest) {
        this.enumCleanTest = enumCleanTest;
    }

    @Override
    public String toString() {
        return "EntityEnumTest{"
                + "testEnum=" + testEnum
                + "enumExternalTest=" + enumExternalTest
                + "enumCleanTest=" + enumCleanTest
                + '}';
    }
}
