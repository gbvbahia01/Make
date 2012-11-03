/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.date;

import br.com.gbvbahia.maker.types.complex.MakeCalendar;
import br.com.gbvbahia.maker.log.LogInfo;
import java.util.Calendar;
import org.apache.commons.logging.Log;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class MakeCalendarTest {

    private static Log logger = LogInfo.getLog("Test :: MakeCalendarTest");

    public MakeCalendarTest() {
    }

    /**
     * Test of getInFuture method, of class MakeCalendar.
     */
    @Test
    public void testGetInFuture() {
        logger.info("Calendar - GetInFuture");
        Calendar result = MakeCalendar.getInFuture();
        assertNotNull("Data Futura nula", result);
        Calendar now = Calendar.getInstance();
        assertTrue("Now é depois de result", now.before(result));
    }

    /**
     * Test of getInPast method, of class MakeCalendar.
     */
    @Test
    public void testGetInPast() {
        logger.info("Calendar - GetInPast");
        Calendar result = MakeCalendar.getInPast();
        assertNotNull("Data Passada nula", result);
        Calendar now = Calendar.getInstance();
        assertTrue("Now é antes de result", now.after(result));
    }

    /**
     * Test of getCalendar method, of class MakeCalendar.
     */
    @Test
    public void testGetCalendar() {
        logger.info("Calendar - GetCalendar");
        Calendar result = MakeCalendar.getCalendar();
        assertNotNull("Data Aeatória nula", result);
        Calendar now = Calendar.getInstance();
        assertTrue("Now é agora",
                now.after(result) || now.before(result));
    }
}
