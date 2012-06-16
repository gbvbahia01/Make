/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia;

import br.com.gbvbahia.i18n.I18NTest;
import br.com.gbvbahia.maker.MakeEntitySynteticTest;
import br.com.gbvbahia.maker.MakeEntityTest;
import br.com.gbvbahia.maker.MakeEntitysTest;
import br.com.gbvbahia.maker.properties.MakePropertiesTest;
import br.com.gbvbahia.maker.types.date.MakeCalendarTest;
import br.com.gbvbahia.maker.types.date.MakeDateTest;
import br.com.gbvbahia.maker.types.string.MakeCharacterTest;
import br.com.gbvbahia.maker.types.string.MakeStringTest;
import br.com.gbvbahia.maker.types.wrappers.*;
import br.com.gbvbahia.maker.works.MakeCNPJTest;
import br.com.gbvbahia.maker.works.MakeCPFTest;
import br.com.gbvbahia.maker.works.MakeListTest;
import br.com.gbvbahia.maker.works.MakeSetTest;
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
    MakeCalendarTest.class,
    MakeDateTest.class,
    MakeCPFTest.class,
    MakeCNPJTest.class,
    MakeEntityTest.class,
    MakeEntitySynteticTest.class,
    MakeEntitysTest.class,
    MakePropertiesTest.class,
    MakeListTest.class,
    MakeSetTest.class
})
public class Testes {
}
