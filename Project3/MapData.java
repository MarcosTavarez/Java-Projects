import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class MapData 
{
    private HashMap<String, ArrayList<Observation>> dataCatalog = new HashMap<String, ArrayList<Observation>>();
   // private EnumMap<StatsType, TreeMap<String, Statistics>> statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>();
    private TreeMap<String, Integer> paramPositions = new TreeMap<String,Integer>();
    private int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    private Integer numberOfStations = null;
    private String TAIR = "TAIR";
    private String TA9M = "TA9M";
    private String SRAD = "SRAD";
    private String STID = "STID";
    private String MESONET = "Mesonet";
    private String fileName = "";
    private GregorianCalendar utcDateTime;


    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        fileName = createFileName(year, month, day, hour, minute, directory);
        this.utcDateTime = new GregorianCalendar(year, month, day, hour, minute);

        //TODO
    }

    public String createFileName( int year, int month, int day, int hour, int minute, String directory)
    {
        fileName = String.format("%s/%4d%02d%02d%02d%02d.mdf", directory, year, month, day, hour, minute);

        return fileName;
    }

    private void parseParamHeader(String inParamStr)
    {
        String[] masterSplitter = inParamStr.trim().split("\\s+");

        for (int i = 0; i < masterSplitter.length; i++)
        {
            if (masterSplitter[i].equalsIgnoreCase(TAIR))
            {
                paramPositions.put(TAIR, i);
            }
            else if (masterSplitter[i].equalsIgnoreCase(TA9M))
            {
                paramPositions.put(TA9M, i);
            }
            else if (masterSplitter[i].equalsIgnoreCase(STID))
            {
                paramPositions.put(STID, i);
            }
            else if (masterSplitter[i].equalsIgnoreCase(SRAD))
            {
                paramPositions.put(SRAD, i);
            }
        }
        //TODO
    }

    public Integer getIndexOf(String inParamStr)
    {
        return paramPositions.get(inParamStr);

        //TODO
    }

    public void parseFile()
    {
        ArrayList<Observation> sradData = new ArrayList<Observation>();
        ArrayList<Observation> tairData = new ArrayList<Observation>();
        ArrayList<Observation> ta9mData = new ArrayList<Observation>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {

            String fileData = br.readLine();
            fileData = br.readLine();

            fileData = br.readLine();
            parseParamHeader(fileData);
            fileData = br.readLine();

            String[] masterSplitter = new String[24];
            while (fileData != null)
            {
                masterSplitter = fileData.trim().split("\\s+");
                Observation tairDataInfo = new Observation(Double.parseDouble(masterSplitter[paramPositions.get(STID)]),
                        (masterSplitter)[getIndexOf(TAIR)]); //ERROR
                Observation sradDataInfo = new Observation(Double.parseDouble(masterSplitter[paramPositions.get(STID)]),
                        (masterSplitter)[getIndexOf(SRAD)]);
                Observation ta9mDataInfo = new Observation(Double.parseDouble(masterSplitter[paramPositions.get(STID)]),
                        (masterSplitter)[getIndexOf(TA9M)]);

                tairData.add(tairDataInfo);
                sradData.add(sradDataInfo);
                ta9mData.add(ta9mDataInfo);

                dataCatalog.put(TAIR, tairData);
                dataCatalog.put(SRAD, sradData);
                dataCatalog.put(TA9M, ta9mData);

                numberOfStations = tairData.size();

                fileData = br.readLine();
            }
            System.out.println(dataCatalog);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }


        //TODO
    }

    private void calculateAllStatistics()
    {
        double max = 0.0;
        double min = 0.0;
        double average = 0.0;
        double total = 0.0;
        
        Set<String> parameterIds = dataCatalog.keySet();

        for (String paramId: parameterIds)
        {
            
            
            ArrayList<Observation> data = dataCatalog.get(paramId);

            for (Observation obs: data)
            {
                if ( obs.getValue() > max && obs.isValid())
                {
                    calculateStatistics();
                    max = obs.getValue();
                    
                }
                else if(obs.getValue() < min && obs.isValid())
                {
                    calculateStatistics();
                    min = obs.getValue();
                }
                
            }
        }
        //TODO
    }

    private void prepareDataCatalog()
    {
        
        //TODO
    }

    private void calculateStatistics()
    {
    
        String stidMax = "";
        TreeMap<String, Statistics> max = statistics.get(0);

        for (int i = 0; i < dataCatalog.size(); i++)
        {
            if (dataCatalog.get(i) != null && dataCatalog.get(i).isValid() && max < statistics.get(i).getValue())
            {

                max = dataCatalog.get(i).getValue();
                stidMax = inData.get(i).getStid();

            }

        }

        // MIN
        String stidMin = "";
        double min = inData.get(0).getValue();

        for (int i = 0; i < inData.size(); i++)
        {
            if (inData.get(i) != null && inData.get(i).isValid() && min > inData.get(i).getValue())
            {
                min = inData.get(i).getValue();
                stidMin = inData.get(i).getStid();

            }
        }
        // AVERAGE

        double total = 0;
        double average = 0;
        int valid = 0;
        for (int i = 0; i < inData.size(); i++)
        {
            if (inData.get(i) != null && inData.get(i).isValid())
            {
                valid++;
                total += inData.get(i).getValue();
            }
        }
        average = (total / valid);
        
        //TOTAL
        double thisTotal = 0.0;
        for (int i = 0; i < inData.size(); i++)
        {
            if (inData.get(i) != null && inData.get(i).isValid())
            {
                thisTotal += inData.get(i).getValue();

            }
        }

        //TODO
    }

    public Statistics getStatistics(StatsType type, String paramid)
    {
        return dataCatalog.
        //TODO
    }

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
                + "========================================================");
        //TODO
    }

}
