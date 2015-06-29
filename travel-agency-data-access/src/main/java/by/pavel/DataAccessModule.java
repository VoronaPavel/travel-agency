package by.pavel;

import by.pavel.datasource.DataSourceModule;
import by.pavel.repository.RepositoryModule;
import com.google.inject.AbstractModule;

public class DataAccessModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DataSourceModule());
        install(new RepositoryModule());
    }
}
