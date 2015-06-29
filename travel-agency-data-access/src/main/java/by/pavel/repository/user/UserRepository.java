package by.pavel.repository.user;

import by.pavel.domain.user.User;
import by.pavel.domain.user.UserDto;
import by.pavel.repository.CrudRepository;
import by.pavel.DataAccessException;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, UserDto> {

    Optional<User> findByEmail(String email) throws DataAccessException;
    Optional<User> findByEmailPassword(String email, String password) throws DataAccessException;
}