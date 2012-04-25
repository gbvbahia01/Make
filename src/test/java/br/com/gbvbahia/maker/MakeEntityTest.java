/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.entityes.EntityTest;
import junit.framework.TestCase;

/**
 *
 * @author Guilherme
 */
public class MakeEntityTest extends TestCase {

    public MakeEntityTest() {
        super("Make Entity");
    }

    public void testMakeEntity() throws Exception {
        EntityTest test = MakeEntity.makeEntity(EntityTest.class);
        assertNotNull("Test é nulo.", test);
        assertTrue("Valor inesperado", test.getInteiro() <= 5);
    }
}
