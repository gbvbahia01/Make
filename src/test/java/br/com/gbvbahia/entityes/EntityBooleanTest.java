/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

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

    public Boolean getBooleanObjectFalse() {
        return booleanObjectFalse;
    }

    public void setBooleanObjectFalse(Boolean booleanObjectFalse) {
        this.booleanObjectFalse = booleanObjectFalse;
    }

    public Boolean getBooleanObjectTrue() {
        return booleanObjectTrue;
    }

    public void setBooleanObjectTrue(Boolean booleanObjectTrue) {
        this.booleanObjectTrue = booleanObjectTrue;
    }

    public Boolean getBooleanPrimitiveFalse() {
        return booleanPrimitiveFalse;
    }

    public void setBooleanPrimitiveFalse(Boolean booleanPrimitiveFalse) {
        this.booleanPrimitiveFalse = booleanPrimitiveFalse;
    }

    public Boolean getBooleanPrimitiveTrue() {
        return booleanPrimitiveTrue;
    }

    public void setBooleanPrimitiveTrue(Boolean booleanPrimitiveTrue) {
        this.booleanPrimitiveTrue = booleanPrimitiveTrue;
    }

    @Override
    public String toString() {
        return "EntityBooleanTest{"
                + "booleanObjectFalse=" + booleanObjectFalse
                + ", booleanObjectTrue=" + booleanObjectTrue
                + ", booleanPrimitiveFalse=" + booleanPrimitiveFalse
                + ", booleanPrimitiveTrue=" + booleanPrimitiveTrue
                + '}';
    }
}
