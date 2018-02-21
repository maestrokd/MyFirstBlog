package blog.model.dto;

import blog.core.registration.validation.annotations.ValidEmail;
import blog.core.registration.validation.annotations.ValidLogin;
import blog.core.registration.validation.annotations.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @ValidLogin
    @NotNull
    @Size(min = 4)
    private String login;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 4)
    private String name;

//    @ValidEmail (message = "{error.enterInvalidEmail}")
    @ValidEmail
    @NotNull
    @Size(min = 1)
    private String email;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
