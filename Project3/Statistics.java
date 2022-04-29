import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public class Statistics extends Observation implements DateTimeComparable
{
    protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    protected DateTimeFormatter format;
    private GregorianCalendar utcDateTime;
    private ZonedDateTime zdtDateTime;
    private int numberOfReportingStations;
    private StatsType statType;
    
    
    public Statistics (double value, String stid, GregorianCalendar dateTime, int numberOfValidStations, StatsType inStatType)
{
        super(value,stid);
        this.statType = inStatType;
        this.numberOfReportingStations = numberOfValidStations;
        this.utcDateTime = dateTime;
        //TODO
}

    public Statistics (double value, String stid, ZonedDateTime dateTime, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        this.statType = inStatType;
        this.numberOfReportingStations = numberOfValidStations;
        this.zdtDateTime = dateTime;
        
        //TODO
    }
    
    public GregorianCalendar createDateFromString(String dateTimeStr)
    {
        //TODO
        return utcDateTime;
        
    }
    
    public ZonedDateTime createZDateFromString(String dateTimeStr)
    {
        //TODO
        return zdtDateTime;
        
    }
    
    public String createStringFromDate(GregorianCalendar calendar)
    {
        //TODO
        return DATE_TIME_FORMAT;
        
    }
    
    public String createStringFromDate(ZonedDateTime calendar)
    {
        //TODO
        return DATE_TIME_FORMAT;
    }
    
    public int getNumberOfReportingStations()
    {
        //TODO
        return numberOfReportingStations;
       
    }
    
    public String getUTCDateTimeString()
    {
        //TODO
        return DATE_TIME_FORMAT;
        
    }

    @Override
    public boolean newerThan(GregorianCalendar inDateTimeUTC)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean olderThan(GregorianCalendar inDateTimeUTC)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean sameAs(GregorianCalendar inDateTimeUTC)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean newerThan(ZonedDateTime inDateTimeUTC)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean olderThan(ZonedDateTime inDateTimeUTC)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean sameAs(ZonedDateTime inDateTimeUTC)
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    public String toString()
    {
        //TODO Must do
        return null;
    }
}
