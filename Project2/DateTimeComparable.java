
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public interface DateTimeComparable
{
    boolean newerThan(GregorianCalendar inDateTime);

    boolean olderThan(GregorianCalendar inDateTime);

    boolean sameAs(GregorianCalendar inDateTime);

    boolean newerThan(ZonedDateTime inDateTime);

    boolean olderThan(ZonedDateTime inDateTime);

    boolean sameAs(ZonedDateTime inDateTime);
}
