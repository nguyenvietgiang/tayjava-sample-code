package vn.tayjava.dto.request;

import java.io.Serializable;

public class UserRequestDTO implements Serializable {

    private String firstName;
    private String phone;

    public UserRequestDTO(String firstName, String phone) {
        this.firstName = firstName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}