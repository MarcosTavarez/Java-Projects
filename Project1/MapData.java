import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Class to represent  weather Data across Oklahoma. Map Data have a year, month, day, hour and minute.
 * 
 * Recieved help and refrenced from Andrew Greer and Cody
 * 
 * @author Marcos Tavarez, references previous code from Past labs written by Stephen Thung and refrenced from Andrew Fagg
 * @version 2018-09-18
 * 
 */
public class MapData {
	/**
	 * The MapData from received
	 */
	private Observation[] sradData;
	/**
	 * The sradData given by value and station name
	 */
	private Observation[] tairData;
	/**
	 * The tairData given by value and station name
	 */
	private Observation[] ta9mData;
	/**
	 * The ta9mData given by value and station name
	 */
	private static int SIZE = 1000;
	/**
	 * Size of the Object array
	 * Meant to be downsized later in the program
	 */
	private static final int NUMBER_OF_MISSING_OBSERVATIONS = 10;
	/**
	 * Observations missing
	 */
	private Integer numberOfStations = null;

	private final int STID_POSITION = 0;
	/**
	 * Position of the data within the mdf file.
	 */
	private final int TAIR_POSITION = 4;
	/**
	 * Position of the data within the mdf file.
	 */
	private final int SRAD_POSITION = 13;
	/**
	 * Position of the data within the mdf file.
	 */
	private final int TA9M_POSITION = 14;
	/**
	 * Position of the data within the mdf file.
	 */
	private String MESONET = "Mesonet";
	/**
	 * Name of the string meant to be within the newly created object within the get average methods
	 */
	private String directory;
	private Observation tairMin;/**
     /** 
	 *  return Temperature of air's minimum across 
	 */
	private Observation tairMax;
	/** 
	 *  return Temperature of air's maximum across 
	 */
	private Observation tairAverage;
	/** 
	 *  return Temperature average as Observation
	 */
	private Observation ta9mMin;
	/** 
	 *  return ta9m minimum 
	 */
	private Observation ta9mMax;
	/** 
	 *  return ta9m maximum 
	 */
	private Observation ta9mAverage;
	/** 
	 *  return ta9m average as Observation 
	 */
	private Observation sradMin;
	/** 
	 *  return solar radiation minimum as Observation 
	 */
	private Observation sradMax;
	/** 
	 *  return solar radiation maximum as Observation 
	 */
	private Observation sradAverage;
	/** 
	 *  return solar radiation average as Observation 
	 */
	private Observation sradTotal;
	/** 
	 *  return solar radiation total as Observation 
	 */
	private int year;
	/** 
	 *  return year
	 */
	private int month;
	/** 
	 *  return month 
	 */
	private int day;
	/** 
	 *  return day
	 */
	private int hour;
	/** 
	 *  return hour 
	 */
	private int minute;
	/** 
	 *  return minute 
	 */

	public MapData(int year, int month, int day, int hour, int minute, String directory)
	{
		/**
		 *  Constructor with following parameters
		 * 
		 * Set's the year and all else that follows to itself
		 */

		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.directory = directory;
	}

	public int getYear()
	{
		return year;
	}

	public int getMonth()
	{
		return month;
	}

	public int getDay()
	{
		return day;
	}

	public int getHour()
	{
		return hour;
	}

	public int getMinutes()
	{
		return minute;
	}

	public String getDirectory()
	{
		return directory;
	}
	public String createFileName()
	{	
		/**
		 * When the user needs to print out info about an station and the weather, present
		 * the information in order of year, month, day, hour, and minute
		 * 
		 * @return The string representation of the MapData class, formatted as:
		 *         "(year)(month)(day)(hour)(minute).mdf"
		 */
		String fileFormat = String.format("%4d%02d%02d%02d%02d.mdf" , getYear() , getMonth() , getDay() , getHour(), getMinutes());

		return fileFormat;
	}
	public void parseFile() throws IOException

	/**Help from Reagan from CS 2334
	 * Loads a list of weather stations accompanied with a value of type double from a mdf file. Each line of the mdf represents a single Station.
	 * The first three lines of the mdf are simply the headers,
	 * 
	 * @param fileName The file to read from.
	 * @throws IOException
	 */
	{
		numberOfStations = 0;

		sradData = new Observation[SIZE];

		tairData = new Observation[SIZE];

		ta9mData = new Observation[SIZE];



		BufferedReader br = new BufferedReader(new FileReader(createFileName()));

		String fileData = br.readLine();
		fileData = br.readLine();

		fileData = br.readLine();

		fileData = br.readLine();



		String[] masterSplitter1 = new String[24];

		while (fileData != null)
		{

			masterSplitter1 = fileData.trim().split("\\s+");
			Observation sradDataInfo = new Observation(Double.parseDouble(masterSplitter1[SRAD_POSITION]), masterSplitter1[STID_POSITION]);

			sradData[numberOfStations] = sradDataInfo;

			Observation tairDataInfo = new Observation(Double.parseDouble(masterSplitter1[TAIR_POSITION]), masterSplitter1[STID_POSITION]);
			tairData[numberOfStations] = tairDataInfo;

			Observation ta9mDataInfo = new Observation(Double.parseDouble(masterSplitter1[TA9M_POSITION]), masterSplitter1[STID_POSITION]);
			ta9mData[numberOfStations] = ta9mDataInfo;
			numberOfStations++;


			fileData = br.readLine();
		}

		Observation[] actualSizeSradData = new Observation[numberOfStations];
		for (int i = 0; i < actualSizeSradData.length; i++ )
		{
			actualSizeSradData[i] = sradData[i];
		}
		sradData = actualSizeSradData;


		Observation[] actualSizeTairData = new Observation[numberOfStations];
		for (int j = 0; j < actualSizeTairData.length; j++ )
		{
			actualSizeTairData[j] = tairData[j];
		}
		tairData = actualSizeTairData;


		Observation[] actualSizeTa9mData = new Observation[numberOfStations];
		for (int k = 0; k < actualSizeTairData.length; k++ )
		{
			actualSizeTa9mData[k] = ta9mData[k];
		}
		ta9mData = actualSizeTa9mData;

		calculateAirTemperatureStatistics();

		calculateTa9mTemperatureStatistics();

		calculateSolarRadiationStatistics();


		br.close();

	}

	public Observation getSradAverage()
	{
		return sradAverage;
	}



	public Observation getSradMax()
	{
		return sradMax;
	}



	public Observation getSradMin()
	{
		return sradMin;
	}



	public Observation getSradTotal()
	{
		return sradTotal;
	}


	public Observation getTa9mAverage()
	{
		return ta9mAverage;
	}


	public Observation getTa9mMax()
	{
		return ta9mMax;
	}


	public Observation getTa9mMin()
	{
		return ta9mMin;
	}


	public Observation getTairAverage()
	{
		return tairAverage;
	}


	public Observation getTairMax()
	{
		return tairMax;
	}


	public Observation getTairMin()
	{
		return tairMin;
	}


	private void calculateAirTemperatureStatistics()
	{
		/**
		 * Calculates the min, max, and average of air temperature to later be called as a method.
		 *
		 */
		//MAX
		double max = tairData[0].getValue();
		int index = 0;

		for ( int i = 0; i < tairData.length; i++)
		{
			if (tairData[i] != null && tairData[i].isValid() && max < tairData[i].getValue())
			{
				max = tairData[i].getValue();
				index = i;
			}
		}
		tairMax = tairData[index];


		//MIN
		double min = tairData[0].getValue();
		int index0 = 0;

		for ( int i =0; i < tairData.length; i++ )
		{
			if (tairData[i] != null && tairData[i].isValid() && min > tairData[i].getValue())
			{
				min = tairData[i].getValue();
				index0 = i;
			}
		}
		tairMin = tairData[index0];


		//AVERAGE
		double total = 0;
		double average = 0;
		int valid = 0;
		for ( int index2 = 0; index2 < tairData.length; index2++ )
		{
			if(tairData[index2] != null && tairData[index2].isValid())
			{
				valid++;
				total += tairData[index2].getValue();
			}
		}
		average = (total/valid);

		tairAverage = new Observation(average, MESONET);
	}


	private void calculateTa9mTemperatureStatistics()
	{
		/**
		 * Calculates the min, max, and average of ta9m temperature to later be called as a method.
		 *
		 */
		//MAX
		double max = ta9mData[0].getValue();
		int index = 0;
		for ( int i = 0; i < ta9mData.length; i++)
		{
			if (ta9mData[i] != null && ta9mData[i].isValid() && max < ta9mData[i].getValue())
			{
				max = ta9mData[i].getValue();
				index = i;
			}
		}
		ta9mMax = ta9mData[index];


		// MIN
		double min = ta9mData[0].getValue();
		int index0 = 0;

		for ( int i =0; i < ta9mData.length; i++ )
		{
			if (ta9mData[i] != null && ta9mData[i].isValid() && min > ta9mData[i].getValue())
			{
				min = ta9mData[i].getValue();
				index0 = i;
			}
		}
		ta9mMin = ta9mData[index0];


		//AVERAGE
		double total = 0.0;
		double average = 0.0;
		int valid = 0;
		for ( int index2 = 0; index2 < ta9mData.length; index2++ )
		{
			if (ta9mData[index2] != null && ta9mData[index2].isValid())
			{
				total += ta9mData[index2].getValue();
				valid++; 
			}
		}
		average = (total/valid);
		ta9mAverage = new Observation(average, MESONET);
	}


	private void calculateSolarRadiationStatistics()
	{
		/**
		 * Calculates the min, max, and average of solar radiation to later be called as a method.
		 *
		 */

		//AVERAGE
		double total = 0.0;
		double average = 0.0;
		int valid = 0;
		for ( int index = 0; index < sradData.length; index++ )
		{
			if (sradData[index] != null && sradData[index].isValid())
			{
				total += sradData[index].getValue();
				valid++;
			}
		}		
		average = (total/valid);
		sradAverage = new Observation(average, MESONET);


		//MAX
		double max = sradData[0].getValue();
		int index = 0;
		for ( int i = 0; i < sradData.length; i++ )
		{
			if (sradData[i] != null && sradData[i].isValid() && max < sradData[i].getValue() )
			{
				max = sradData[i].getValue();
				index = i;
			}
		}
		sradMax = sradData[index];


		//MIN
		double min = sradData[0].getValue();
		int index0 = 0;

		for (int i = 0; i < sradData.length;i++)
		{
			if (sradData[i] != null && sradData[i].isValid() && min > sradData[i].getValue() )
			{
				min = sradData[i].getValue();
				index0 = i;
			}
		}
		sradMin = sradData[index0];


		//TOTAL

		int index1 = 0;
		for ( int i = 0; i < sradData.length; i++)
		{
			sradTotal = sradData[i];
			index1 = i;
		}
		sradTotal = sradData[index1];
	}
	public String toString()
	{
		/**
		 *Implements a program that outputs the minimum, average, and maximum temperatures 
		 * at both heights(1.5m and 9.0m and minimum, average, and maximum solar radiation for a day.
		 *
		 */
		return String.format("========================================================\n"
				+ "=== %4d-%02d-%02d %02d:%02d ===\n"
				+ "========================================================\n"
				+ "Maximum Air Temperature[1.5m] = %.1f C at %s\n" + 
				"Minimum Air Temperature[1.5m] = %.1f C at %s\n" + 
				"Average Air Temperature[1.5m] = %.1f C at %s\n"
				+ "========================================================\n"
				+ "========================================================\n"
				+ "Maximum Air Temperature[9.0m] = %.1f C at %s\n" + 
				"Minimum Air Temperature[9.0m] = %.1f C at %s\n" + 
				"Average Air Temperature[9.0m] = %.1f C at %s\n"
				+ "========================================================\n"
				+ "========================================================\n"
				+ "Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n" + 
				"Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n" + 
				"Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
				+ "========================================================" ,
				getYear() , getMonth(), getDay(), getHour(), getMinutes(), tairMax.getValue()
				, tairMax.getStid(), tairMin.getValue() ,
				tairMin.getStid() ,
				tairAverage.getValue() , tairAverage.getStid() , ta9mMax.getValue()
				, ta9mMax.getStid() , ta9mMin.getValue() , ta9mMin.getStid() ,
				ta9mAverage.getValue() , ta9mAverage.getStid(), sradMax.getValue(),
				sradMax.getStid() , sradMin.getValue(), sradMin.getStid(),
				sradAverage.getValue(), sradAverage.getStid());

	}
}
