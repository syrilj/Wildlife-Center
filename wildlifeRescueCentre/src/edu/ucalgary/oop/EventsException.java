package edu.ucalgary.oop;

/**
 An exception that is thrown when an error occurs during events processing.
 */
public class EventsException extends Exception {

    /**
     Constructs a new EventsException with the default message.
     */
    public EventsException() {
        super("Exception occurred during events processing");
    }
}

