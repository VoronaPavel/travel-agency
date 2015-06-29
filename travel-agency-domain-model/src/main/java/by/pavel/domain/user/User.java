package by.pavel.domain.user;

import by.pavel.domain.Entity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

public class User extends Entity {

    @Email @NotBlank
    private String email;
    @NotBlank
    private String password;

    public User(long id, @Email @NotBlank String email, @NotBlank String password) {
        super(id);
        this.email = email;
        this.password = password;
    }

    public User(long id, @Valid UserDto userDTO) {
        super(id);
        email = userDTO.email;
        password = userDTO.password;
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

    public static User from(UserDto dto, long id) {
        return new User(id, dto);
    }
}
