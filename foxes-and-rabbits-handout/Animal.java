
/**
 * Common elements of all animals
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public abstract class Animal
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's position.
    private Location location;
    // Animal is a male or not
    private boolean male;
    // Animal's age
    protected int age;
    
    // The environemnt of the simulation
    protected Environment environment;


    /**
     * Constructor for objects of class Animal.
     * @param location The animal's location.
     */
    public Animal(Location location, Environment env)
    {
        this.alive = true;
        this.location = location;
        this.environment = env;
    }
    
    /**
     * Act.
     * @param currentField The current state of the field.
     * @param nextFieldState The new state being built.
     */
    abstract public void act(Field currentField, Field nextFieldState);
    
    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     */
    protected void setDead()
    {
        alive = false;
        location = null;
    }
    
    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Set the animal's location.
     * @param location The new location.
     */
    protected void setLocation(Location location)
    {
        this.location = location;
    }
    
    /**
     * Return the Animal's age
     * @return The Animal's age
     */
    protected int getAge()
    {
        return age;
    }
    
    protected void setAge(int newAge)
    {
        age = newAge;
    }
    
    /**
     * Increase the age. This could result in the fox's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > getMaxAge()) {
            setDead();
        }
    }
    
    /**
     *  Returns the MAX_AGE value from the spcific animal 
     *  @return The MAX_AGE value from the speicfic animal
     */
    abstract protected int getMaxAge();
}
