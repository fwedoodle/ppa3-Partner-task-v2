import java.util.ArrayList;
import java.util.Random;

/** 
 * Tthe Environment class is responsible for the progression of time and change in weather in the simulation.
 * ///
 * @author Matthew Kelsey
 * @version 1.0
 */
public class Environment
{
    private int timeStep;
    private String weather;
    private String timeOfDay;
    
    private static final String[] WEATHER_STATES = {"Clear", "Rainy", "Foggy"};
    private static final double[] WEATHER_WEIGHTS = {0.7, 0.2, 0.1};    // clear, rainy, foggy
    private static final String[] TIME_STATES = {"Dawn", "Day", "Dusk", "Dawn"};
    
    private static final Random random = new Random();

    /**
     * Constructor for Environment class.
     * Initalizes time step (0) and sets a default weather and time state (Sunny, Dawn).
     */
    public Environment()
    {
        // initialize instance variables
        timeStep = 0;
        weather = "Clear";
        timeOfDay = "Dawn";
    }

    /**
     * Increments timeStep, update the time of day one cycle, and randomly update the weather
     *
     */
    public void simulateOneStep()
    {
        timeStep++;
        updateTimeOfDay();
        weather = selectedWeightedWeather();
    }
    
    /**
     * Selects random weather based on weighthed probrabilities 
     * 
     * @return Weighted weather selection
     */
    private String selectedWeightedWeather()
    {
        double rand = random.nextDouble();
        
        if (rand < WEATHER_WEIGHTS[0]) {
            return WEATHER_STATES[0];   // Clear, 70% chance
        }
        else if (rand < WEATHER_WEIGHTS[0] + WEATHER_WEIGHTS[1]) {
            return WEATHER_STATES[1];   // Rainy, 20% chance
        }
        else {
            return WEATHER_STATES[2];   // Foggy, 10% chance
        }
    }
    
    
    /**
     * Updates time of day one step
     * 
     */
    private void updateTimeOfDay()
    {
        timeOfDay = TIME_STATES[timeStep % TIME_STATES.length];
    }

    /**
     * Returns time step
     *
     * @return Current time step
     */
    public int getTimeStep()
    {
        return timeStep;
    }

    /**
     * Returns current weather state
     *
     * @return Current weather state
     */
    public String getWeather()
    {
        return weather;
    }

    /**
     * Returns current time state
     *
     * @return Current time state
     */
    public String getTimeOfDay()
    {
        return timeOfDay;
    }
    
    /**
     * Get the current status of the environment.
     * @return A string representing the time of day and weather.
     */
    public void getStatus() {
        System.out.println("Time of Day: " + timeOfDay + ", Weather: " + weather);
    }
}