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

    public Boolean getBooleanTrueOrFalse() {
        return booleanTrueOrFalse;
    }

    public void setBooleanTrueOrFalse(Boolean booleanTrueOrFalse) {
        this.booleanTrueOrFalse = booleanTrueOrFalse;
    }

    @Override
    public String toString() {
        return "EntityBooleanTest{"
                + "booleanObjectFalse=" + booleanObjectFalse
                + ", booleanObjectTrue=" + booleanObjectTrue
                + ", booleanPrimitiveFalse=" + booleanPrimitiveFalse
                + ", booleanPrimitiveTrue=" + booleanPrimitiveTrue
                + ", booleanTrueOrFalse=" + booleanTrueOrFalse
                + ", primitiveBoolean=" + primitiveBoolean
                + ", primitiveTrueBoolean=" + primitiveTrueBoolean
                + ", primitiveFalseBoolean=" + primitiveFalseBoolean
                + '}';
    }
}
