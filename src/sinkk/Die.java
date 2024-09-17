/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Kyle Sink
 * Last Updated: 9/12/24
 */
package sinkk;
import java.util.Random;

/**
 * Creates a die, can roll it, and can obtain current value
 */
public class Die {
    /**
     * Min number of sides possible on the die
     */
    public final int MIN_SIDES = 2;
    /**
     * Max number of sides possible on the die
     */
    public final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random = new Random();

    /**
     * Creates a die with number of sides (numSides)
     * exception if the die is not between min and max number of sides as defined above
     * @param numSides number of side on the die given by the user
     */
    public Die(int numSides){
        if(numSides < MIN_SIDES || numSides > MAX_SIDES){
            throw new IllegalArgumentException("Bad die creation: Illegal number of sides: "
                    + numSides);
        }
        this.numSides = numSides;
    }


    /**
     * Gets the current value then resets current value to 0, if between min and max
     * @return the current value
     */
    public int getCurrentValue() {
        roll();
        int temp = currentValue;
        try{
            if(currentValue > numSides || currentValue < 1){
                throw new DieNotRolledException();
            }
        } catch (DieNotRolledException e) {
            System.out.println(e.getMessage());
        }
        currentValue = 0;
        return temp;

    }

    /**
     * Sets currentValue to a number between 1 & number of sides
     */
    public void roll() {
        currentValue = random.nextInt(numSides) + 1;
    }

}