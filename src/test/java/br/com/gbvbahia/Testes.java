/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia;

import br.com.gbvbahia.maker.string.MakeStringTest;
import br.com.gbvbahia.maker.wrappers.MakeIntegerTest;
import br.com.gbvbahia.maker.wrappers.MakeDoubleTest;
import br.com.gbvbahia.maker.wrappers.MakeFloatTest;
import br.com.gbvbahia.maker.wrappers.MakeByteTest;
import br.com.gbvbahia.maker.wrappers.MakeShortTest;
import br.com.gbvbahia.maker.wrappers.MakeLongTest;
import br.com.gbvbahia.i18n.I18NTest;
import br.com.gbvbahia.maker.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Guilherme
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    I18NTest.class,
    MakeByteTest.class,
    MakeShortTest.class,
    MakeIntegerTest.class,
    MakeLongTest.class,
    MakeFloatTest.class,
    MakeDoubleTest.class,
    MakeStringTest.class,
    MakeEntityTest.class
})
public class Testes {
}
