package ro.fortech.bigproject.services.validators;

import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.repository.UserRepository;
import ro.fortech.bigproject.services.validators.exceptions.UsernameTakenException;

public class UsernameValidator implements Validator<User> {

    private UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(User user) throws UsernameTakenException {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UsernameTakenException("Username is taken");


    }
}
