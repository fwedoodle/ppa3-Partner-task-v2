import java.util.List;
import java.util.Random;

/**
 * A simple model of a elephant.
 * Elephants's age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Elephant extends Prey
{
    // Characteristics shared by all gazelles (class variables).
    // The age at which a gazelle can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a gazelle can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a gazelle breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The gazelle's age.
    private int age;

    /**
     * Create a new gazelle. A gazelle may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param location The location within the field.
     */
    public Elephant(boolean randomAge, Location location)
    {
        super(location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the gazalle does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param currentField The field occupied.
     * @param nextFieldState The updated field.
     */
    public void act(Field currentField, Field nextFieldState)
    {
        incrementAge();
        if(isAlive()) {
            List<Location> freeLocations = 
                nextFieldState.getFreeAdjacentLocations(getLocation());
            if(!freeLocations.isEmpty()) {
                giveBirth(nextFieldState, freeLocations);
            }
            // Try to move into a free location.
            if(! freeLocations.isEmpty()) {
                Location nextLocation = freeLocations.get(0);
                setLocation(nextLocation);
                nextFieldState.placeAnimal(this, nextLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    @Override
    public String toString() {
        return "Elephant{" +
                "age=" + age +
                ", alive=" + isAlive() +
                ", location=" + getLocation() +
                '}';
    }

    /**
     * Increase the age.
     * This could result in the gazelle's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }


    /**
     * A gazelle can breed if it has reached the breeding age.
     * @return true if the gazelle can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    protected int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    protected int getBreedingAge()
    {
        return BREEDING_AGE;
    }
    
    protected double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }
    
    protected Prey createNewChild(Location loc) {
        return new Elephant(false, loc);
    }
    
}
