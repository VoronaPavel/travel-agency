package by.pavel.service.internal.tour;

import by.pavel.service.tour.TourService;
import com.google.inject.AbstractModule;

public class ServiceTourModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TourService.class).to(TourServiceImpl.class);
    }
}
