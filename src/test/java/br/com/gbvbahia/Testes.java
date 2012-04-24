/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia;

import br.com.gbvbahia.i18n.I18NTest;
import br.com.gbvbahia.maker.MakeIntegerTest;
import br.com.gbvbahia.maker.MakeStringTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Guilherme
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    I18NTest.class,
    MakeIntegerTest.class,
    MakeStringTest.class
})
public class Testes {
    
}
