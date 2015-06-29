package by.pavel.domain.user;

import by.pavel.domain.Dto;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class UserDto implements Dto<User> {

    @Email @NotBlank
    public final String email;
    @NotBlank
    public final String password;

    public UserDto(@Email @NotBlank @NotNull String email, @NotBlank @NotNull String password) {
        this.email = email;
        this.password = password;
    }
}
