package by.pavel.repository.internal.user;

import by.pavel.repository.user.UserRepository;
import com.google.inject.AbstractModule;

public class UserRepositoryModule extends AbstractModule {

    @Override protected void configure() {
        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }
}
