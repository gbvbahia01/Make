/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.date;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeDateTest {

    private Log logger = LogFactory.getLog("MakeDateTest");
    
    public MakeDateTest() {
    }
    
    /**
     * Test of getInFuture method, of class MakeDate.
     */
    @Test
    public void testGetInFuture() {
        logger.info("Maker :: Date - GetInFuture");
        Date result = MakeDate.getInFuture();
        assertNotNull("Data Futura nula", result);
        Date now = Calendar.getInstance().getTime();
        assertTrue("Now é depois de result", now.before(result));
    }

    /**
     * Test of getInPast method, of class MakeDate.
     */
    @Test
    public void testGetInPast() {
        logger.info("Maker :: Date - GetInPast");
        Date result = MakeDate.getInPast();
        assertNotNull("Data Passada nula", result);
        Date now = Calendar.getInstance().getTime();
        assertTrue("Now é antes de result", now.after(result));
    }

    /**
     * Test of getCalendar method, of class MakeDate.
     */
    @Test
    public void testGetCalendar() {
        logger.info("Maker :: Date - GetCalendar");
        Date result = MakeDate.getCalendar();
        assertNotNull("Data Aeatória nula", result);
        Date now = Calendar.getInstance().getTime();
        assertTrue("Now é agora",
                now.after(result) || now.before(result));
    }
}
