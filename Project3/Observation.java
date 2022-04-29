
public class Observation extends AbstractObservation
{
    private double value;
    private String stid;
    
    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;
        //TODO
    }
    
    public double getValue()
    {
        return value;
    }
    
    public boolean isValid()
    {
        return super.valid;
    }
    
    public String getStid()
    {
        return stid;
    }
    
    public String toString()
    {
        //TODO
        return null;
    }
}
