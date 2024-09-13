/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Kyle Sink
 * Last Updated: 9/12/24
 */
package sinkk;

/**
 * A custom exception thrown when the dice roll is not in the range set by the user
 */
public class DieNotRolledException extends Throwable {
    @Override
    public String getMessage() {
        return "CurrentValue not in Expected Range";
    }
}
