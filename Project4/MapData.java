import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class MapData
{
    /**
     * Class to represent weather Data across Oklahoma. Map Data have a year, month,
     * day, hour and minute.
     * 
     * Recieved help and refrenced from Andrew Greer and Cody
     * 
     * @author Marcos Tavarez, references previous code from Past labs written by
     *         Stephen Thung and refrenced from Andrew Fagg
     * @version 2018-09-18
     * 
     */
    private HashMap<String, ArrayList<Observation>> dataCatalog = new HashMap<String, ArrayList<Observation>>();
    /**
     * HashMap that hold a value of an ArrayList of Observation Stores the arraylist
     * and is accessed by a string
     * 
     */
    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(
            StatsType.class);
    /**
     * EnumMap, holds a TreeMap of Statistics
     * 
     */
    private TreeMap<String, Integer> paramPositions = new TreeMap<String, Integer>();
    /**
     * Tree Map that holds the ParamId and its position
     * 
     */
    // private int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    private Integer numberOfStations = null;
    /**
     * The number of valid stations
     * 
     */
    private String TAIR = "TAIR";
    /**
     * Tair ParamID
     */
    private String TA9M = "TA9M";
    /**
     * Ta9m ParamId
     */
     private String SRAD = "SRAD";
    /**
     * SRAD ParamId
     */
    private String STID = "STID";
    /**
     * STID ParamId
     */
    private String MESONET = "Mesonet";
    /**
     * String to be used when calculating Averages
     */
    private String fileName = "";
    /**
     * String meant to hold the file name created in createFileName() method
     */
    private GregorianCalendar utcDateTime;

    /**
     * GregorianCalendar which holds a time (UTC)
     */

    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        fileName = createFileName(year, month, day, hour, minute, directory);
        this.utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
        
    }
    public MapData(String file)
    {
        fileName = "data/"+file;
        this.utcDateTime = new GregorianCalendar(Integer.parseInt(file.substring(0, 3)), Integer.parseInt(file.substring(4,5))
                , Integer.parseInt(file.substring(6,7)),Integer.parseInt(file.substring(8,9)), Integer.parseInt(file.substring(10,11)) );
        
    }

    /**
     * Map Data Constructor MapData implements these variables to the parameters.
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {
        fileName = String.format("%s/%4d%02d%02d%02d%02d.mdf", directory, year, month, day, hour, minute);

        return fileName;
    }

    /**
     * createFileName takes in whatever file name there is and formats it to be read
     * later.
     * 
     */

    private void parseParamHeader(String inParamStr)
    {
        String[] masterSplitter = inParamStr.trim().split("\\s+");

        for (int i = 0; i < masterSplitter.length; i++)
        {
            paramPositions.put(masterSplitter[i], i);
        }
        // TODO
    }

    /**
     * Finds the positions of TA9M, TAIR, SRAD and the STID so that the program
     * knows where to assign the specific Data given.
     */
    public Integer getIndexOf(String inParamStr)
    {
        return paramPositions.get(inParamStr);

        // TODO
    }

    /**
     * Gets the index of the Parameter Id
     */
    public void parseFile()
    {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {

            String fileData = br.readLine();
            fileData = br.readLine();

            fileData = br.readLine();
            parseParamHeader(fileData);
            prepareDataCatalog();
            fileData = br.readLine();

            String[] masterSplitter = new String[24];
            Set<String> data = paramPositions.keySet();
            while (fileData != null)
            {
                masterSplitter = fileData.trim().split("\\s+");

                for (String strangs : data)
                {
                    if (!strangs.equalsIgnoreCase(STID))
                    {
                        dataCatalog.get(strangs)
                                .add(new Observation(Double.parseDouble(masterSplitter[getIndexOf(strangs)]),
                                        (masterSplitter[getIndexOf(STID)])));
                    }
                }

                fileData = br.readLine();
            }

            calculateStatistics();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        // TODO
    }

    /**
     * Parses the file and stores specific data and stores it all into dataCatalog.
     * 
     */

    private void calculateAllStatistics()
    {
        TreeMap<String, Statistics> data0 = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> data1 = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> data2 = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> data3 = new TreeMap<String, Statistics>();

        String stidMax = "";
        String stidMin = "";

        Set<String> parameterIds = dataCatalog.keySet();

        for (String paramId : parameterIds)
        {
            double max = 0;
            double min = 0.0;
            double average = 0.0;
            double total = 0.0;
            numberOfStations = 0;

            if (!paramId.equalsIgnoreCase(STID))
            {
                ArrayList<Observation> data = dataCatalog.get(paramId);

                max = data.get(0).getValue();
                min = data.get(0).getValue();
                // MAX
                for (Observation obs : data)
                {
                    if (obs.getValue() > max && obs.isValid())
                    {

                        max = obs.getValue();
                        stidMax = obs.getStid();

                    }
                    // MIN
                    else if (obs.getValue() < min && obs.isValid())
                    {

                        min = obs.getValue();
                        stidMin = obs.getStid();

                    }
                    // AVERAGE
                    if (obs.isValid())
                    {

                        total += obs.getValue();
                        numberOfStations++;
                    }
                }
            }
            average = (total / numberOfStations);

            Statistics thisAverage = new Statistics(average, MESONET, utcDateTime, numberOfStations, StatsType.AVERAGE);
            Statistics thisMin = new Statistics(min, stidMin, utcDateTime, numberOfStations, StatsType.MINIMUM);
            Statistics thisMax = new Statistics(max, stidMax, utcDateTime, numberOfStations, StatsType.MAXIMUM);
            Statistics thisTotal = new Statistics(total, MESONET, utcDateTime, numberOfStations, StatsType.TOTAL);
            // Still need to fix the Total.
            data0.put(paramId, thisAverage);
            data1.put(paramId, thisMin);
            data2.put(paramId, thisMax);
            data3.put(paramId, thisTotal);

        }
        statistics.put(StatsType.MAXIMUM, data2);
        statistics.put(StatsType.TOTAL, data3);
        statistics.put(StatsType.AVERAGE, data0);
        statistics.put(StatsType.MINIMUM, data1);
        // TODO
    }

    /**
     * Calculates the min max and average of all ParamId's except STID Stores
     * corresponding StatsType into Statistics
     */

    private void prepareDataCatalog()
    {

        Set<String> parameterIds = paramPositions.keySet();

        for (String strang : parameterIds)
        {
            dataCatalog.put(strang, new ArrayList<Observation>());
        }
        
    }

    /**
     * Create's new ArrayList of observation for each ParamId
     */

    private void calculateStatistics()
    {
        calculateAllStatistics();
    }

    /**
     * Calculate's statistics for MAX, MIN, and Average
     */

    public Statistics getStatistics(StatsType type, String paramid)
    {
        return statistics.get(type).get(paramid);
        // TODO
    }

    /**
     * Mean't to return specific statistics paired with which ParamId it is from.
     */

    public String toString()
    {
        return String.format("========================================================\n"
                + "=== %4d-%02d-%02d %02d:%02d ===\n" + "========================================================\n"
                + "Maximum Air Temperature[1.5m] = %.1f C at %s\n" + "Minimum Air Temperature[1.5m] = %.1f C at %s\n"
                + "Average Air Temperature[1.5m] = %.1f C at %s\n"
                + "========================================================\n"
                + "========================================================\n"
                + "Maximum Air Temperature[9.0m] = %.1f C at %s\n" + "Minimum Air Temperature[9.0m] = %.1f C at %s\n"
                + "Average Air Temperature[9.0m] = %.1f C at %s\n"
                + "========================================================\n"
                + "========================================================\n"
                + "Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                + "Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                + "Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                + "========================================================", utcDateTime.get(Calendar.YEAR),
                utcDateTime.get(Calendar.MONTH), utcDateTime.get(Calendar.DAY_OF_MONTH),
                utcDateTime.get(Calendar.HOUR_OF_DAY), utcDateTime.get(Calendar.MINUTE),
                getStatistics(StatsType.MAXIMUM, TAIR).getValue(), getStatistics(StatsType.MAXIMUM, TAIR).getStid(),
                getStatistics(StatsType.MINIMUM, TAIR).getValue(), getStatistics(StatsType.MINIMUM, TAIR).getStid(),
                getStatistics(StatsType.AVERAGE, TAIR).getValue(), getStatistics(StatsType.AVERAGE, TAIR).getStid(),
                getStatistics(StatsType.MAXIMUM, TA9M).getValue(), getStatistics(StatsType.MAXIMUM, TA9M).getStid(),
                getStatistics(StatsType.MINIMUM, TA9M).getValue(), getStatistics(StatsType.MINIMUM, TA9M).getStid(),
                getStatistics(StatsType.AVERAGE, TA9M).getValue(), getStatistics(StatsType.AVERAGE, TA9M).getStid(),
                getStatistics(StatsType.MAXIMUM, SRAD).getValue(), getStatistics(StatsType.MAXIMUM, SRAD).getStid(),
                getStatistics(StatsType.MINIMUM, SRAD).getValue(), getStatistics(StatsType.MINIMUM, SRAD).getStid(),
                getStatistics(StatsType.AVERAGE, SRAD).getValue(), getStatistics(StatsType.AVERAGE, SRAD).getStid());
        // TODO
    }

}
