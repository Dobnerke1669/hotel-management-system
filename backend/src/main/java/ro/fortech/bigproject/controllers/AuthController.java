package ro.fortech.bigproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.fortech.bigproject.entities.Role;
import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.payload.request.LoginRequest;
import ro.fortech.bigproject.payload.request.SignupRequest;
import ro.fortech.bigproject.payload.response.IntermediateJwtResponse;
import ro.fortech.bigproject.payload.response.JwtResponse;
import ro.fortech.bigproject.payload.response.MessageResponse;
import ro.fortech.bigproject.services.AuthService;
import ro.fortech.bigproject.services.UserService;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Operation(summary = "Sign in", description = "Sign in with username and password")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());
        IntermediateJwtResponse intermediateJwtResponse = authService.login(user);
        return ResponseEntity.ok(new JwtResponse(intermediateJwtResponse.getJwt(),
                intermediateJwtResponse.getUserDetails().getId(),
                intermediateJwtResponse.getUserDetails().getUsername(),
                intermediateJwtResponse.getUserDetails().getEmail(),
                intermediateJwtResponse.getRoles()));
    }

    @Operation(summary = "Sign up", description = "Sign up with username, email, password, first name and last name")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName(), true);
        List<Role> roles = userService.insertUserRole();
        user.setRoles(roles);
        try {
            userService.createUser(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Operation(summary = "Find all users", description = "Finds all users")
    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAllUsers();
    }
}
