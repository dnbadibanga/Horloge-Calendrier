
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours, minutes, and meridies.
 * The range of the clock is 12:00AM (midnight) to 11:59PM (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @revised by Divine Badibanga(201765203) and Kelachi Charles-Beke(201651312)
 * @version 2019.09.24
 */
public class ClockDisplay extends NumberDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        super();
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
    }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display from a 24hr
     * format to a 12hr format.
     */
    private void updateDisplay()
    {
        //format hours for consistent display
        String hoursString;
        int hoursValue = hours.getValue();
        //all hours are represented with integers less than 12
        hoursValue = hoursValue % 12;
        if(hoursValue == 0){
            hoursString = "12";   //represent midnight and noon as 12
        }
        else if(hoursValue < 10) {
            hoursString = "0" + hoursValue;
        }
        else{
            hoursString = "" + hoursValue;
        }
        
        //format minutes for consistent display
        String minutesString;
        int minutesValue = minutes.getValue();
        if(minutesValue < 10) {
            minutesString = "0" + minutesValue;
        }
        else {
            minutesString = "" + minutesValue;
        }
        
        //indicate whether time is before noon or after noon
        String meridies;
        hoursValue = hours.getValue();
        if (hoursValue >= 12) {
        meridies = "PM";
        }
        else {
        meridies = "AM";
        }
        
        //connect hours, minutes, and meridies to a single string
        displayString = hoursString + ":" + minutesString + " " + meridies;
    }
}
