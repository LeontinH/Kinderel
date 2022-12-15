package Kinderel.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class DTOModel {
    private Long id;
    @NotEmpty(message = "User Name should not be empty")
    private String userName;
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;

    public DTOModel(Long id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public DTOModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
