package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
    @Size(min = 3, max = 20)
    @Schema(example = "test", required = true)
    private String username;

    @Schema(example = "test@yahoo.com", required = true)
    private String email;
    @Schema(example = "[\"client\"]", required = true)
    private Set<String> role;

    @Schema(example = "test123", required = true)
    private String password;

    @Schema(example = "Test", required = true)
    private String firstName;

    @Schema(example = "Test", required = true)
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
