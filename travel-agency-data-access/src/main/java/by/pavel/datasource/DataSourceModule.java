package by.pavel.datasource;

import com.google.inject.AbstractModule;

public class DataSourceModule extends AbstractModule {

    protected void configure() {
        install(new by.pavel.datasource.internal.DataSourceModule());
    }
}
