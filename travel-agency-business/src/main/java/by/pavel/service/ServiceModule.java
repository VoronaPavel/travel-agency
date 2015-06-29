package by.pavel.service;

import by.pavel.DataAccessModule;
import by.pavel.service.internal.tour.ServiceTourModule;
import by.pavel.service.internal.user.ServiceUserModule;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override protected void configure() {
        install(new DataAccessModule());
        install(new ServiceUserModule());
        install(new ServiceTourModule());
    }
}
