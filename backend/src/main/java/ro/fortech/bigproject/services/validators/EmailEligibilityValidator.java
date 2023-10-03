package ro.fortech.bigproject.services.validators;

import ro.fortech.bigproject.services.validators.exceptions.EmailInvalidException;

public class EmailEligibilityValidator implements Validator<String> {
    public void validate(String email) throws EmailInvalidException {
        String regexPattern = "^(.+)@(\\S+)$";
        if (!email.matches(regexPattern)) throw new EmailInvalidException("Email is invalid");
    }
}
