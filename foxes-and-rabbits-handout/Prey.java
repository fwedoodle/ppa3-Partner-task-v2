import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * Write a description of class Prey here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Prey extends Animal
{
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();
    private int foodLevel;
    
    public Prey(Location loc)
    {
        super(loc);
    }
    
    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    protected void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for Plants adjacent to the current location.
     * Only the first Plant that has a current growth value is eaten from.
     * @param field The field currently occupied.
     * @return Where food was found, or null if it wasn't.
     */
    protected Location findFood(Field field)
    {
        List<Location> adjacent = field.getAdjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        Location foodLocation = null;
        while(foodLocation == null && it.hasNext()) {
            Location loc = it.next();
            FieldEntity entity = field.getEntityAt(loc);
            if(entity instanceof Plant plant) {
                int foodVal = plant.eaten();
                if(foodVal > 0) {
                    foodLevel += foodVal;
                    foodLocation = loc;
                }
            }
        }
        return foodLocation;
        
    }
    
    /**
     * Check whether this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param freeLocations The locations that are free in the current field.
     */
    protected void giveBirth(Field nextFieldState, List<Location> freeLocations)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        int births = breed();
        if(births > 0) {
            for (int b = 0; b < births && ! freeLocations.isEmpty(); b++) {
                Location loc = freeLocations.remove(0);
                Prey young = createNewChild(loc);
                nextFieldState.placeAnimal(young, loc);
            }
        }
    }
    
    abstract Prey createNewChild(Location loc);
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        else {
            births = 0;
        }
        return births;
    }

    /**
     * A fox can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= getBreedingAge();
    }
    
    abstract protected double getBreedingProbability();
    
    abstract protected int getBreedingAge();
    
    abstract protected int getMaxLitterSize();
}