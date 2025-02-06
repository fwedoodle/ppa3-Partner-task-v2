
/**
 * Write a description of class Plants here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plant
{

    // Location of the plant
    private Location location;
    // Current growth level of the plant
    private int growthLevel;
    // The max growth level the plant can grow to
    private static final int maxGrowthLevel = 5;

    /**
     * Constructor for objects of class Plant
     */
    public Plant(Location loc)
    {
        location = loc;
        growthLevel = 0;
    }
    
    /**
     *  
     */
    public void grow()
    {   
        growthLevel++;
        if(growthLevel > maxGrowthLevel)
        {
            growthLevel = maxGrowthLevel;
        }
    }
    
    /**
     * Plant returns the growth level and resets it to 0
     * @returns The growth level of the plant before it was eaten
     */
    public int getsEaten()
    {
        int growthTemp = growthLevel;
        growthLevel = 0;
        return growthTemp;
    }
    
}
