import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.Assert;
public class MapDataTest {




	@Test
	public void testingCreateFileName()
	{

		MapData mapData = new MapData(1998,12,21,5,30,"");

		mapData.createFileName();

		String expected = "199812210530.mdf";

		String actual = mapData.createFileName();

		assertEquals(expected,actual);
	}



	@Test
	public void testingGetSradAverage() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 828.1;
		double actual = mapData.getSradAverage().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetSradMax() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 968.0;
		double actual = mapData.getSradMax().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetSradMin() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 163.0;
		double actual = mapData.getSradMin().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetSradTotal() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 874.0 ;
		double actual = mapData.getSradTotal().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetTa9mAverage() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 31.6;
		double actual = mapData.getTa9mAverage().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetTa9mMax() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 34.9;
		double actual = mapData.getTa9mMax().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetTa9mMin() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 20.7;
		double actual = mapData.getTa9mMin().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetTairAverage() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 32.4;
		double actual = mapData.getTairAverage().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetTairMax() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 36.5;
		double actual = mapData.getTairMax().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingGetTairMin() throws IOException
	{

		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		double expected = 20.8;
		double actual = mapData.getTairMin().getValue();
		assertEquals(expected, actual, .1);
	}


	@Test
	public void testingToString() throws IOException
	{
		MapData mapData = new MapData( 2018, 8 , 30 , 17 , 45 , "data");
		mapData.parseFile();
		String expected = ("========================================================\n"
				+ "=== 2018-08-30 17:45 ===\n"
				+ "========================================================\n"
				+ "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\n" + 
				"Minimum Air Temperature[1.5m] = 20.8 C at MIAM\n" + 
				"Average Air Temperature[1.5m] = 32.4 C at Mesonet\n"
				+ "========================================================\n"
				+ "========================================================\n"
				+ "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\n" + 
				"Minimum Air Temperature[9.0m] = 20.7 C at MIAM\n" + 
				"Average Air Temperature[9.0m] = 31.6 C at Mesonet\n"
				+ "========================================================\n"
				+ "========================================================\n"
				+ "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\n" + 
				"Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\n" + 
				"Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet\n"
				+ "========================================================");

		String actual = mapData.toString();
		assertEquals(expected, actual);

	}
}
