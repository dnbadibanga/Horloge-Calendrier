
/**
 * The NumberDisplay class represents a digital number display that can hold
 * values from zero to a given limit. The limit can be specified when creating
 * the display. The values range from zero (inclusive) to limit-1. If used,
 * for example, for the seconds on a digital clock, the limit would be 60, 
 * resulting in display values from 0 to 59. When incremented, the display 
 * automatically rolls over to zero when reaching the limit.When decremented,
 * the display authomaticaly rolls over to limit-1 when reaching zero.
 * 
 * @author Michael Kölling and David J. Barnes, 
 * revised by Divine Badibanga(201765203) and Kelachi Charles-Beke
 * @version 2019.09.24
 */
public class NumberDisplay
{
    private int limit;
    private int value;

    /**
     * Constructor for objects of class NumberDisplay.
     * Set the limit at which the display rolls over.
     */
    public NumberDisplay(int rollOverLimit)
    {
        limit = rollOverLimit;
        value = 0;
    }

    /**
     * Return the current value.
     */
    public int getValue()
    {
        return value;
    }


    /**
     * Set the value of the display to the new specified value. If the new
     * value is less than zero or over the limit, do nothing.
     */
    public void setValue(int replacementValue)
    {
        if((replacementValue >= 0) && (replacementValue < limit)) {
            value = replacementValue;
        }
    }

    /**
     * Increment the value by one, rolling over to zero if the
     * limit is reached.
     */
    public void increment()
    {
        value = (value + 1) % limit;
    }
    
    /**
     * Decrement the value by one, rolling over to limit-1 if zero
     * is reached.
     */
    public void decrement()
    {
            value = (value+(limit-1)) % limit;
            
            String array[] = {"M", "T", "W", "R", "F", "S"};
    }
}
