package com.hotelservices.exception;

public class FoodOrderException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2484638108376462949L;

    /**
     * Instantiates a new FoodOrderException Exception.
     *
     * @param message the message
     */
    public FoodOrderException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new FoodOrderException exception.
     *
     * @param message the message
     * @param e the Exception
     */
    public FoodOrderException(String message, Throwable e) {
        super(message, e);
    }
}
