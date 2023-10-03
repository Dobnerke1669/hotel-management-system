package ro.fortech.bigproject.services.validators;

import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.repository.UserRepository;
import ro.fortech.bigproject.services.validators.exceptions.EmailTakenException;

public class EmailValidator implements Validator<User> {


    private UserRepository userRepository;

    public EmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void validate(User user) throws EmailTakenException {
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailTakenException("Email is taken");
    }
}
