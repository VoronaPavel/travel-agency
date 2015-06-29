package by.pavel.service.internal.user;

import by.pavel.domain.user.User;
import by.pavel.domain.user.UserDto;
import by.pavel.repository.user.UserRepository;
import by.pavel.service.ServiceException;
import by.pavel.service.internal.AbstractCrudService;
import by.pavel.service.user.UserService;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import java.util.Optional;

@Singleton
class UserServiceImpl extends AbstractCrudService<User, UserDto, UserRepository> implements UserService {

    @Inject UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override public Optional<User> findByEmail(@Valid String email) throws ServiceException {
        return repository.findByEmail(email);
    }

    @Override public Optional<User> findByEmailPassword(@NotNull String email, String password) throws ServiceException {
        return repository.findByEmailPassword(email, password);
    }
}
