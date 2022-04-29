
public class Observation extends AbstractObservation
{
    private double value;
    private String stid;

    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;
        if (value <= -900)
        {
            super.valid = false;
        }
        else
        {
            super.valid = true;
        }
    }

    /**
     * Observation Constructor Implements a value of double and a Station Id
     */

    public double getValue()
    {
        return value;
    }

    /**
     * Returns value
     */

    public String getStid()
    {
        return stid;
    }

    /**
     * Returns Station ID
     */

    public boolean isValid()
    {
        return valid;

    }

    /**
     * Returns Valid
     */

    public String toString()
    {
        return String.format("");
    }
    /**
     * To String for observation class
     */

}
