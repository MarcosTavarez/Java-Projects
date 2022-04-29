import static org.junit.Assert.*;

import org.junit.Test;

public class ObservationTest
{

    @Test
    public void testGetValue()
    {
        Observation obs = new Observation(2.0, "");

        double expected = 2.0;
        double actual = obs.getValue();
        assertEquals(expected, actual, .1);
        
        Observation obs1 = new Observation(-999.0, "");
        double expected0 = -999.0;
        double actual0 = obs1.getValue();
        assertEquals(expected0, actual0, .01);
    }

    @Test
    public void testGetStid()
    {

        Observation obs = new Observation(2.0, "Stid");
        String expected = "Stid";
        String actual = obs.getStid();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsValid()
    {
        Observation obs = new Observation(2.0, "Stid");
        Observation obs1 = new Observation(-999.0, "Stid");
        assertEquals(false, obs1.isValid());
        assertEquals(true, obs.isValid());
    }

    @Test
    public void testToString()
    {
        Observation mapData = new Observation(0, "");
        String expected = "";
        String actual = mapData.toString();
        assertEquals(expected, actual);
    }
}
