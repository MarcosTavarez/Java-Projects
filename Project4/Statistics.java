import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Statistics extends Observation implements DateTimeComparable
{
    protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    protected DateTimeFormatter format;
    private GregorianCalendar utcDateTime;
    private ZonedDateTime zdtDateTime;
    private int numberOfReportingStations;
    private StatsType statType;

    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        this.statType = inStatType;
        this.numberOfReportingStations = numberOfValidStations;
        this.utcDateTime = dateTime;

    }

    /**
     * Statistics Constructor Holds Gregorian Calendar Date Time Takes a value and
     * Stid from Observation Class
     */

    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        this.statType = inStatType;
        this.numberOfReportingStations = numberOfValidStations;
        this.zdtDateTime = dateTime;

    }

    /**
     * Very Similar to First Statistics Class but holds a ZonedDateTime date time
     * instead
     */

    public GregorianCalendar createDateFromString(String dateTimeStr) throws ParseException
    {
        SimpleDateFormat form = new SimpleDateFormat(DATE_TIME_FORMAT);
        form.parse(dateTimeStr);
        return (GregorianCalendar) form.getCalendar();

    }

    /**
     * Creates a date from a string in Gregorian Time format
     */

    public ZonedDateTime createZDateFromString(String dateTimeStr) throws ParseException
    {
        // TODO
        format = DateTimeFormatter.ofPattern(dateTimeStr);
        return (ZonedDateTime) format.parse(dateTimeStr);

    }

    /**
     * Creates a date from a string in ZonedDateTime format
     */

    public String createStringFromDate(GregorianCalendar calendar)
    {
        // TODO
        return String.format(DATE_TIME_FORMAT, utcDateTime.get(Calendar.YEAR), utcDateTime.get(Calendar.MONTH),
                utcDateTime.get(Calendar.DAY_OF_MONTH), utcDateTime.get(Calendar.DAY_OF_MONTH),
                utcDateTime.get(Calendar.HOUR_OF_DAY), utcDateTime.get(Calendar.MINUTE));

    }

    /**
     * Creates a String when given a date in Gregorian format
     */

    public String createStringFromDate(ZonedDateTime calendar)
    {
        // TODO
        return String.format(DATE_TIME_FORMAT, (Calendar.YEAR), (Calendar.MONTH), (Calendar.DAY_OF_MONTH),
                 (Calendar.HOUR_OF_DAY), (Calendar.MINUTE),(Calendar.MILLISECOND));
    }

    /**
     * Create a String when given a date in ZonedDateTime format
     */
    public int getNumberOfReportingStations()
    {

        return numberOfReportingStations;

    }

    /**
     * Returns the number of reporting stations
     */
    public String getUTCDateTimeString()
    {
        // TODO
       int year = utcDateTime.get(Calendar.YEAR);
       int month = utcDateTime.get(Calendar.MONTH);
       int day = utcDateTime.get(Calendar.DAY_OF_MONTH);
       int hour = utcDateTime.get(Calendar.HOUR_OF_DAY);
       int minute = utcDateTime.get(Calendar.MINUTE);
       int second = utcDateTime.get(Calendar.SECOND);
       String zone = utcDateTime.getTimeZone().getID();
       return String.format("%d-%02d-%02dT%02d:%02d %s", year, month, day,hour, minute,second,zone);
    }

    /**
     * Returns the number of reporting stations
     */
    @Override
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.after(inDateTime);
    }

    /**
     * Checks to see if the date is newer than which ever it is compared to Returns
     * true if so
     */
    @Override
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.before(inDateTime);
    }

    /**
     * Checks to see if the date is older than which ever it is compared to Returns
     * true if so
     */
    @Override
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        return utcDateTime.equals(inDateTime);
    }

    /**
     * Checks to see if the date is equal to which ever it is compared to Returns
     * true if so
     */
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        return zdtDateTime.isAfter(inDateTime);
    }

    /**
     * Checks to see if the date is newer than which ever it is compared to Returns
     * true if so
     */
    public boolean olderThan(ZonedDateTime inDateTime)
    {
        return zdtDateTime.isBefore(inDateTime);
    }

    /**
     * Checks to see if the date is older than which ever it is compared to Returns
     * true if so
     */
    public boolean sameAs(ZonedDateTime inDateTime)
    {
        return zdtDateTime.equals(inDateTime);
    }

    /**
     * Checks to see if the date is same as which ever it is compared to Returns
     * true if so
     */
    public StatsType getStatType()
    {
        // Satisfy WebCat
        return statType;
    }

    public String toString()
    {
        // TODO
        return String.format("The date is %s", createStringFromDate(utcDateTime));
    }
    /**
     * Formats and returns a string
     * 
     */
}
