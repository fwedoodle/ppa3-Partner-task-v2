
/**
 * Write a description of class Environment here.
 *
 * @author (your name)
 * @version 1
 */
public class Environment
{
    
    private Weather weather;
    private Time time;

    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {
        weather = new Weather();
        time = new Time();
    }


}
