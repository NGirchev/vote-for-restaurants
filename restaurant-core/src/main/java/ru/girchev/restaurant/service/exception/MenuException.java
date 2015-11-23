package ru.girchev.restaurant.service.exception;


/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class MenuException extends Exception {

    //TODO to properties files
    public static final String MESSAGE_NOT_EMPTY_ID = "Restaurant id or menu id must be not null ";


    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MenuException(String message) {
        super(message);
    }
}
