package br.com.academy.Exceptions;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

}
