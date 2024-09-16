/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Kyle Sink
 * Last Updated: 9/12/24
 */
package sinkk;

import java.util.InputMismatchException;
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
        // Check if the input is 3 things seperated by spaces
        // Then check if the input is all numbers
        // then check if the input adheres to the rules
        // 2-10 dice
        // 2-100 sides

        // 1 - x rolls
        //if at any point there is an exception then send back to start

        final int minDice = 2;
        final int maxDice = 10;
        boolean correctFormat = false;
        int[] config = new int[3];

        do{
            System.out.print("""
                           Please enter the number of dice to roll, how many sides the dice have,
                            and how many rolls to complete, separating the values by a space.
                            Example: "2 6 1000"\s
                           \s
                            Enter Configuration:""");
            try{
                config = getInput();
                if(config[0] < minDice || config[0] > maxDice){
                    throw new IllegalArgumentException("Bad die creation: Illegal number of dice: " + config[0]);
                }
                Die[] Dice = new Die[config[0]];
                for(int i = 0; i < config[0]; i++) {
                    Dice[i] = new Die(config[1]);
                }
                correctFormat = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers"); //catch non-whole numbers
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); //catch more or less than 3
            }
        } while(!correctFormat);
    }
    private static int[] getInput() {

        int[] intInput = new int[3]; // array to be returned
        Scanner input = new Scanner(System.in); //Scanner

        String userInput = input.nextLine(); // Receive input from user

        String[] inputArray = userInput.split(" "); //gets input then splits it up

        if (inputArray.length != 3) {
            throw new IllegalArgumentException("Invalid input: Expected 3 values but only received " +
                    String.valueOf(inputArray.length));
        }

        for (int i = 0; i < 3; i++) {
            intInput[i] = Integer.parseInt(inputArray[i]);
        } //attempts to turn split things into numbers, throws an error if not numbers
        return intInput;

    } // # of dice/# of sides/Number of rolls

    private Die[] createDice(int numDice, int numSides){
        Die[] dice = new Die[numDice]; //add in min and max dice thing
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }
}
/*
In the main() method, call getInput() and store the results in an int[]
 */