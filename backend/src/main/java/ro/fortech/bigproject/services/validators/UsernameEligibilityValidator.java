package ro.fortech.bigproject.services.validators;

import ro.fortech.bigproject.services.validators.exceptions.UsernameInvalidException;

public class UsernameEligibilityValidator implements Validator<String> {
    @Override
    public void validate(String username) throws UsernameInvalidException {
        String regexPattern = "^[a-zA-Z0-9]{3,}$";
        if (!username.matches(regexPattern)) throw new UsernameInvalidException("Username is invalid");
    }
}
