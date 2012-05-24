/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia;

import br.com.gbvbahia.maker.types.wrappers.MakeShortTest;
import br.com.gbvbahia.maker.types.wrappers.MakeFloatTest;
import br.com.gbvbahia.maker.types.wrappers.MakeDoubleTest;
import br.com.gbvbahia.maker.types.wrappers.MakeIntegerTest;
import br.com.gbvbahia.maker.types.wrappers.MakeBooleanTest;
import br.com.gbvbahia.maker.types.wrappers.MakeByteTest;
import br.com.gbvbahia.maker.types.wrappers.MakeLongTest;
import br.com.gbvbahia.i18n.I18NTest;
import br.com.gbvbahia.maker.MakeEntityTest;
import br.com.gbvbahia.maker.types.string.MakeCharacterTest;
import br.com.gbvbahia.maker.types.string.MakeStringTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Guilherme
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    I18NTest.class,
    MakeBooleanTest.class,
    MakeByteTest.class,
    MakeShortTest.class,
    MakeIntegerTest.class,
    MakeLongTest.class,
    MakeFloatTest.class,
    MakeDoubleTest.class,
    MakeCharacterTest.class,
    MakeStringTest.class,
    MakeEntityTest.class
})
public class Testes {
}
