package ro.fortech.bigproject.payload.response;

import ro.fortech.bigproject.services.UserDetailsImpl;

import java.util.List;

public class IntermediateJwtResponse {

    private String jwt;

    private UserDetailsImpl userDetails;

    private List<String> roles;


    public IntermediateJwtResponse(String jwt, UserDetailsImpl userDetails, List<String> roles) {
        this.jwt = jwt;
        this.userDetails = userDetails;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserDetailsImpl getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
