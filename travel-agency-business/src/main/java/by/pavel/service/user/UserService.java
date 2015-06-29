package by.pavel.service.user;

import by.pavel.domain.user.User;
import by.pavel.domain.user.UserDto;
import by.pavel.service.CrudService;
import by.pavel.service.ServiceException;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.Optional;

public interface UserService extends CrudService<User, UserDto> {

    Optional<User> findByEmail(@Valid @Email String email) throws ServiceException;
    Optional<User> findByEmailPassword(@Valid @Email @NotBlank String email, @Valid @NotBlank String password) throws ServiceException;
}
