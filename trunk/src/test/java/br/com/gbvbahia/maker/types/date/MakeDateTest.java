/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.date;

import br.com.gbvbahia.maker.factories.types.LogInfo;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeDateTest {

    private static Log logger = LogInfo.getLog("Test :: MakeDateTest");
    
    public MakeDateTest() {
    }
    
    /**
     * Test of getInFuture method, of class MakeDate.
     */
    @Test
    public void testGetInFuture() {
        logger.info("Date - GetInFuture");
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
        logger.info("Date - GetInPast");
        Date result = MakeDate.getInPast();
        assertNotNull("Data Passada nula", result);
        Date now = Calendar.getInstance().getTime();
        assertTrue("Now é antes de result", now.after(result));
    }

    /**
     * Test of getDate method, of class MakeDate.
     */
    @Test
    public void testGetCalendar() {
        logger.info("Date - GetCalendar");
        Date result = MakeDate.getDate();
        assertNotNull("Data Aeatória nula", result);
        Date now = Calendar.getInstance().getTime();
        assertTrue("Now é agora",
                now.after(result) || now.before(result));
    }
}
