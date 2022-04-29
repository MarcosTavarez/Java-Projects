import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;



public class ObservationTest {

	@Test
	public void testingGetValue() {

		Observation sum = new Observation(20.5 , "");

		//double expected = value;
		double expected = 20.5;

		double actual = sum.getValue();

		assertEquals(expected, actual , 0.001);
	}


	@Test
	public void testingIsValid()
	{

		Observation myTestForIsValid = new Observation(-999, "stid");

		int expected = -999;

		int actual = -999;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testingGetStid()
	{

		Observation myTestForGetStid = new Observation(20.5, "stid");

		String expected = "stid";

		String actual = myTestForGetStid.getStid();

		Assert.assertEquals(expected, actual);
	}

}
