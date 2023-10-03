package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {
    @Schema(example = "all", required = true)
    private String username;

    @Schema(example = "all", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
