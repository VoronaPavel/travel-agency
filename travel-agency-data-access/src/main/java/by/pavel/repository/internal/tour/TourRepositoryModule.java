package by.pavel.repository.internal.tour;

import by.pavel.repository.tour.TourRepository;
import com.google.inject.AbstractModule;

public class TourRepositoryModule extends AbstractModule {

    @Override protected void configure() {
        bind(TourRepository.class).to(TourRepositoryImpl.class);
    }
}
