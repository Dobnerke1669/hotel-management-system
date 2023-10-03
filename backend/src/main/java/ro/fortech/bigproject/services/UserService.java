package ro.fortech.bigproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.bigproject.entities.ERole;
import ro.fortech.bigproject.entities.Role;
import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.repository.RoleRepository;
import ro.fortech.bigproject.repository.UserRepository;
import ro.fortech.bigproject.services.validators.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<Validator<User>> validator;

    private final List<Validator<String>> eligibilityValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        validator = new ArrayList<>();
        validator.add(new UsernameValidator(userRepository));
        validator.add(new EmailValidator(userRepository));
        eligibilityValidator=new ArrayList<>();
        eligibilityValidator.add(new EmailEligibilityValidator());
        eligibilityValidator.add(new UsernameEligibilityValidator());
    }

    public void checkValidator(User user) throws Exception {
        try{
            for (Validator<User> v : validator)
            {
                v.validate(user);
            }
            eligibilityValidator.get(0).validate(user.getEmail());
            eligibilityValidator.get(1).validate(user.getUsername());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void checkUsername(String username) {
        userRepository.existsByUsername(username);
    }

    public void checkEmail(String email) {
        userRepository.existsByEmail(email);
    }


    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User createUser(User user) throws Exception {
        try {
            checkValidator(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        userRepository.save(user);
        return user;
    }

    public List<Role> createRoles(List<String> strRoles) {
        List<Role> roles = new ArrayList<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "client":
                        Role modRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "receptionist":
                        Role receptionistRole = roleRepository.findByName(ERole.ROLE_RECEPTIONIST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(receptionistRole);

                        break;
                    case "kitchen":
                        Role kitchenRole = roleRepository.findByName(ERole.ROLE_KITCHEN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(kitchenRole);

                        break;
                    case "cleaning":
                        Role cleaningRole = roleRepository.findByName(ERole.ROLE_CLEANING)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(cleaningRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }

    public List<Role> insertUserRole() {
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        return roles;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByFirstNameAndLastName(String firstName, String lastName) {
        List<User> usersByFirstName = userRepository.findByFirstName(firstName);
        for (User u : usersByFirstName) {
            if (u.getLastName().equals(lastName)) {
                return u;
            }
        }
        return null;
    }
}
