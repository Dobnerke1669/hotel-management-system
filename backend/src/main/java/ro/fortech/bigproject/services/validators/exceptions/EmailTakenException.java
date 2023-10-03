package ro.fortech.bigproject.services.validators.exceptions;

public class EmailTakenException extends Exception {

    public EmailTakenException(String message) {
        super(message);
    }
}
