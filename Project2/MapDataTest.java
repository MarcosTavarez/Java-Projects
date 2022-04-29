import static org.junit.Assert.*;

import org.junit.Test;

public class MapDataTest
{

    @Test
    public void testCreateFileName()
    {
        MapData mapData = new MapData(1998, 12, 21, 5, 30, "");

        String expected = "/199812210530.mdf";

        String actual = mapData.createFileName(1998, 12, 21, 5, 30, "");

        assertEquals(expected, actual);
    }

    @Test
    public void testToString()
    {
        MapData mapData = new MapData(2018, 8, 30, 17, 45, "data");
        mapData.parseFile();
        String expected = ("========================================================\n" + "=== 2018-08-30 17:45 ===\n"
                + "========================================================\n"
                + "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\n"
                + "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\n"
                + "Average Air Temperature[1.5m] = 32.4 C at Mesonet\n"
                + "========================================================\n"
                + "========================================================\n"
                + "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\n"
                + "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\n"
                + "Average Air Temperature[9.0m] = 31.6 C at Mesonet\n"
                + "========================================================\n"
                + "========================================================\n"
                + "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\n"
                + "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\n"
                + "Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet\n"
                + "========================================================");

        String actual = mapData.toString();
        assertEquals(expected, actual);
    }

}
