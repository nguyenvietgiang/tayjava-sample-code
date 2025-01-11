package vn.tayjava.dto.response;

import vn.tayjava.model.User;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String firstName;
    private String lastName;
    private String email;

    // Constructor
    public LoginResponse(String accessToken, String refreshToken, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

    // Getters
    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
}
