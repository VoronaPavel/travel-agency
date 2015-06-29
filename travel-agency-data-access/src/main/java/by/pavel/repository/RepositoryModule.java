package by.pavel.repository;

import by.pavel.repository.internal.tour.TourRepositoryModule;
import by.pavel.repository.internal.user.UserRepositoryModule;
import com.google.inject.AbstractModule;

public class RepositoryModule extends AbstractModule {

    protected void configure() {
        install(new UserRepositoryModule());
        install(new TourRepositoryModule());
    }
}
