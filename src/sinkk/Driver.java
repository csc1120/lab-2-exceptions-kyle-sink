/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Kyle Sink
 * Last Updated: 9/17/24
 */
package sinkk;

import java.util.Scanner;

/**
 * Receive input from user, # of dice to be roller, #of sides on the dice, # of times to be rolled
 * Roll all the dice storing the results
 * Find the most frequent # in rolls
 * Prints out results
 */


public class Driver {
    public static void main(String[] args) {
        // Start by asking for input
        // Check if the input is 3 things separated by spaces
        // Then check if the input is all numbers
        // then check if the input adheres to the rules
        // 2-10 dice
        // 2-100 sides

        // 1 - x rolls
        //if at any point there is an exception then send back to start

        final int minDice = 2;
        final int maxDice = 10;
        boolean correctFormat = false;
        int[] config;
        do{
            try{
                config = getInput();
                if(config[0] < minDice || config[0] > maxDice){
                    throw new IllegalArgumentException("Bad die creation: Illegal number of dice: "
                            + config[0]);
                }
                Die[] dice = createDice(config[0], config[1]);
                int[] data = rollDice(dice, config[1], config[2]);
                report(config[0], data, findMax(data));
                correctFormat = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers");
                //catch non-whole numbers
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); //catch more or less than 3
            }
        } while(!correctFormat);
    }

    private static int[] getInput() {
        System.out.print("""
                           Please enter the number of dice to roll, how many sides the dice have,
                            and how many rolls to complete, separating the values by a space.
                            Example: "2 6 1000"\s
                           \s
                            Enter Configuration:""");
        int[] intInput = new int[3]; // array to be returned
        Scanner input = new Scanner(System.in); //Scanner

        String userInput = input.nextLine(); // Receive input from user

        String[] inputArray = userInput.split(" "); //gets input then splits it up

        if (inputArray.length != 3) {
            throw new IllegalArgumentException("Invalid input: Expected 3 values but only received "
                    + inputArray.length);
        }

        for (int i = 0; i < 3; i++) {
            intInput[i] = Integer.parseInt(inputArray[i]);
        } //attempts to turn split things into numbers, throws an error if not numbers
        return intInput;

    } // # of dice/# of sides/Number of rolls

    private static Die[] createDice(int numDice, int numSides){
        Die[] dice = new Die[numDice]; //add in min and max dice thing
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private static int[] rollDice(Die[] dice, int numSides, int numRolls){
        int[] values = new int[numSides*dice.length - (dice.length - 1)];
        int total = 0;
        for(int i = 0; i < numRolls; i++) {
            for (Die die : dice) {
                total += die.getCurrentValue();
            }
            values[total - dice.length]++;
            total = 0;
        }
        return values;
    }

    private static int findMax(int[] rolls){
        int max = -1;
        for (int roll : rolls) {
            if (roll > max) {
                max = roll;
            }
        }
        return max;
    }

    private static void report(int numDice, int[] rolls, int max){
        final int scale = max / 10;
        int numStars;
        for(int i = 0; i < rolls.length; i++){
            System.out.printf("%-2s:%-9s", numDice + i, rolls[i]);
            numStars = rolls[i] / scale;
            for(int j = 0; j < numStars; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
