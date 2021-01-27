import java.util.ArrayList;
/**
 * The CalendarDisplay class implements digital weekly calendar. The calendar
 * cycles through the days of the week, first day of the week being 
 * Monday (yes, Sunday is the weekend) and last day of the week is Sunday.
 * The days are accessed with indices from a list. Once Sunday is reached,
 * the calendar starts back again at Monday.
 *
 * @author Divine Badibanga(201765203) and Kelachi Charles-Beke
 * @version 2019.09.24
 */
public class CalendarDisplay
{
    private int currentDay, currentMonth;
    private ArrayList<String> daysOfWeek;
    private NumberDisplay dayDisplay;

    /**
     * Constructor for objects of class CalendarDisplay
     */
    public CalendarDisplay(int day)
    {
        // initialise instance variables
        currentDay = day;
        
        //create a list and initialise with all days of the week
        daysOfWeek = new ArrayList();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");
        
        //create a cycle through the seven days of the week
        dayDisplay = new NumberDisplay(7);
    }

    /**
     * Returns the index of the day of the week
     */
    public int getCurrentDay()
    {
        return currentDay;
    }
    
    
    /**
     * Goes to the next day (increments value of day by one)
     */
    public void nextDay()
    {
        dayDisplay.increment();
    }
    
    /**
     * Goes to the previous day (decrements value of day by one)
     */
    public void prevDay()
    {
        dayDisplay.decrement();
    }
    
    /**
     * Returns the current day of the week
     */
    public String getDay()
    {
        return daysOfWeek.get(dayDisplay.getValue());
    }
}
