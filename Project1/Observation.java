
public class Observation {

	private double value;
	private String stid;
	private boolean valid;



	/**
	 *  Constructor with following parameters
	 * 
	 * Checks to see if the value is valid or not
	 * 
	 */

	public Observation(double value, String  stid)
	{
		this.value = value;
		this.stid = stid;
		if ( value <= -900)
		{
			valid = false;
		}
		else 
		{
			valid = true;
		}
	}

	public double getValue()
	{
		return value;
	}


	public boolean isValid()
	{
		return valid;
	}


	public String getStid()
	{
		return stid;
	}


	public String toString()
	{
		return String.format("");
	}


}
