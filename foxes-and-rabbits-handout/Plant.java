import java.util.Random;

/**
 * Write a description of class Plants here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plant
{
    // Random number generator
    private static final Random rand = Randomizer.getRandom();
    
    // Current growth level of the plant
    private int currGrowth;
    // Max growth level the plant can reach
    private int maxGrowth;
    // The animal's position.
    private Location location;
    

    /**
     * Constructor for objects of class Plants
     */
    public Plant()
    {
        maxGrowth = 10;
        currGrowth = rand.nextInt(maxGrowth);
    }
    
    /**
     * Constructor for a plant which has a specific maxGrowth level
     */
    public Plant(int maxGrow)
    {
        maxGrowth = maxGrow;
        currGrowth = rand.nextInt(maxGrowth);
    }

    public Location getLocation()
    {
        return location;
    }
    
    public int eaten()
    {
        int foodVal = currGrowth;
        currGrowth = 0;
        return foodVal;
    }
    
    public void grow()
    {
        if(currGrowth < maxGrowth){
            currGrowth++;   
        } 
    }
}
