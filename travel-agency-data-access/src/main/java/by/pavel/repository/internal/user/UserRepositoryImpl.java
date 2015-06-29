package by.pavel.repository.internal.user;

import by.pavel.domain.user.User;
import by.pavel.domain.user.UserDto;
import by.pavel.repository.internal.QueryExecutor;
import by.pavel.repository.internal.Query;
import by.pavel.repository.user.UserRepository;

import javax.inject.Inject;
import java.util.Optional;

import static by.pavel.repository.internal.StatementCallback.SimpleCallbackFor;
import static by.pavel.repository.internal.user.Extractors.instanceExtractor;

class UserRepositoryImpl implements UserRepository {

    private final QueryExecutor executor;

    @Inject UserRepositoryImpl(QueryExecutor executor) {
        this.executor = executor;
    }

    @Override public void save(User entity) {
        Query query = Queries.save(entity);
        executor.execute(query);
    }

    @Override public void update(User entity) {
        Query query = Queries.update(entity);
        executor.execute(query);
    }

    @Override public void delete(User entity) {
        Query query = Queries.delete(entity);
        executor.execute(query);
    }

    @Override public User save(UserDto dto) {
        Query query = Queries.saveDto(dto);
        Long id = executor.execute(query, SimpleCallbackFor(Long.class));
        return User.from(dto, id);
    }

    @Override public Optional<User> findByEmail(String email) {
        Query query = Queries.findByEmail(email);
        User user = executor.execute(query, instanceExtractor());
        return Optional.ofNullable(user);
    }

    @Override public Optional<User> findByEmailPassword(String email, String password) {
        Query query = Queries.findByEmailPassword(email, password);
        User user = executor.execute(query, instanceExtractor());
        return Optional.ofNullable(user);
    }
}
