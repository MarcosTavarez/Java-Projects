import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import org.junit.Test;

public class StatisticsTest
{
    @Test
    public void testStatisticsDoubleStringInStatsType()
    {
        GregorianCalendar yeet = new GregorianCalendar(1, 2, 3, 4, 5);
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);
        assertNotNull(mapData);
    }

    @Test
    public void testStatisticsDoubleStringGregorianCalendar()
    {
        GregorianCalendar yeet = new GregorianCalendar(1, 2, 3, 4, 5);
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);
        assertNotNull(mapData);
    }

    @Test
    public void createDateFromString()
    {
        GregorianCalendar yeet = new GregorianCalendar(1, 2, 3, 4, 5);
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);

        String expected = "yyyy-MM-dd'T'HH:mm:ss z";
        String actual = mapData.DATE_TIME_FORMAT;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNumberOfReportingStations()
    {
        GregorianCalendar yeet = new GregorianCalendar(1, 2, 3, 4, 5);
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);

        int expected = 10;
        int actual = mapData.getNumberOfReportingStations();
        assertEquals(expected, actual, .1);
    }

    @Test
    public void testGetUTCDateTimeString()
    {
        GregorianCalendar yeet = new GregorianCalendar(1, 2, 3, 4, 5);
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);

        String expected = "yyyy-MM-dd'T'HH:mm:ss z";
        String actual = mapData.getUTCDateTimeString();
        assertEquals(expected, actual);
    }

    @Test
    public void testNewerThanGregorianCalender()
    {
        GregorianCalendar yeet = new GregorianCalendar(2018, 8, 30, 15, 13);
        GregorianCalendar yate = new GregorianCalendar(2018, 7, 20, 15, 6);
        Statistics mapData = new Statistics(2.0, "yo", yeet, 10, StatsType.MINIMUM);

        assertEquals(true, mapData.newerThan(yate));
    }

    @Test
    public void testOlderThanGregorianCalendar()
    {
        GregorianCalendar yeet = new GregorianCalendar(2018, 8, 30, 15, 13);
        GregorianCalendar yate = new GregorianCalendar(2018, 9, 20, 15, 6);
        Statistics mapData = new Statistics(2.0, "yo", yeet, 10, StatsType.MINIMUM);

        assertEquals(true, mapData.olderThan(yate));
    }

    @Test
    public void testSameAsGregorianCalendar()
    {
        GregorianCalendar yeet = new GregorianCalendar(2018, 9, 30, 15, 13);
        GregorianCalendar yate = new GregorianCalendar(2018, 9, 30, 15, 13);
        Statistics mapData = new Statistics(2.0, "yo", yeet, 10, StatsType.MINIMUM);

        assertEquals(true, mapData.sameAs(yate));
    }

    @Test
    public void testNewerThanZonedDateTie()
    {

        ZonedDateTime yeet = ZonedDateTime.of(1998, 12, 03, 06, 05, 10, 0, ZoneId.of("America/Chicago"));
        ZonedDateTime yate = ZonedDateTime.of(1998, 8, 03, 06, 05, 10, 0, ZoneId.of("America/Chicago"));
        Statistics mapData = new Statistics(2.0, "yo", yeet, 10, StatsType.MINIMUM);
        boolean actual = mapData.newerThan(yate);

        assertEquals(true, actual);
    }

    @Test
    public void testOlderThanZonedDateTime()
    {
        ZonedDateTime yeet = ZonedDateTime.of(1998, 8, 03, 06, 05, 10, 0, ZoneId.of("America/Chicago"));
        ZonedDateTime yate = ZonedDateTime.of(1998, 12, 03, 06, 05, 10, 0, ZoneId.of("America/Chicago"));
        Statistics mapData = new Statistics(2.0, "yo", yeet, 10, StatsType.MINIMUM);
        boolean actual = mapData.olderThan(yate);

        assertEquals(true, actual);
    }

    @Test
    public void testSameAsZonedDateTime()
    {
        ZonedDateTime yeet = ZonedDateTime.of(1998, 12, 03, 06, 05, 10, 0, ZoneId.of("America/Chicago"));
        ZonedDateTime yate = ZonedDateTime.of(1998, 12, 03, 06, 05, 10, 0, ZoneId.of("America/Chicago"));
        Statistics mapData = new Statistics(2.0, "yo", yeet, 10, StatsType.MINIMUM);
        boolean actual = mapData.sameAs(yate);

        assertEquals(true, actual);
    }

    @Test
    public void testEnum()
    {
        assertEquals("MAXIMUM", StatsType.MAXIMUM.name());
        assertEquals("MINIMUM", StatsType.MINIMUM.name());
        assertEquals("AVERAGE", StatsType.AVERAGE.name());
        assertEquals("TOTAL", StatsType.TOTAL.name());
    }

    @Test
    public void testCreateDateFromString()
    {
        GregorianCalendar yeet = new GregorianCalendar(1, 2, 3, 4, 5);
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);

        String expected = "yyyy-MM-dd'T'HH:mm:ss z";
        String actual = mapData.DATE_TIME_FORMAT;
        assertEquals(expected, actual);
    }

    @Test
    public void testZCreateDateFromString() throws ParseException
    {
        ZonedDateTime yeet = ZonedDateTime.of(1998, 12, 03, 06, 05, 10, 0, ZoneId.of("America/Chicago"));
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);
        String s = "1998-12-03'T'06:05:10 z";

        GregorianCalendar actual = mapData.createDateFromString(s);
        assertEquals("", actual);
    }

    @Test
    public void testStatisticsToString()
    {
        GregorianCalendar yeet = new GregorianCalendar(1, 2, 3, 4, 5);
        GregorianCalendar yate = new GregorianCalendar(1, 2, 3, 4, 5);
        Statistics mapData = new Statistics(2.0, "", yeet, 10, StatsType.MINIMUM);
        Statistics mapData0 = new Statistics(2.0, "", yate, 10, StatsType.MINIMUM);
        assertEquals("The date is yyyy-MM-dd'T'HH:mm:ss z", mapData.toString());
        assertEquals("The date is yyyy-MM-dd'T'HH:mm:ss z", mapData0.toString());
    }

}
