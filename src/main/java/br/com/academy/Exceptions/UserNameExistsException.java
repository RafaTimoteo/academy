package br.com.academy.Exceptions;

public class UserNameExistsException extends Exception {

    public UserNameExistsException(String message) {
        super(message);
    }

}
