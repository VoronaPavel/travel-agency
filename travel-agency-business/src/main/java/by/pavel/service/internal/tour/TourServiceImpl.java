package by.pavel.service.internal.tour;

import by.pavel.domain.tour.Tour;
import by.pavel.domain.tour.TourDto;
import by.pavel.repository.Pageable;
import by.pavel.repository.tour.TourRepository;
import by.pavel.service.internal.AbstractCrudService;
import by.pavel.service.tour.TourService;

import javax.inject.Inject;
import java.util.Optional;

class TourServiceImpl extends AbstractCrudService<Tour, TourDto, TourRepository> implements TourService {

    @Inject TourServiceImpl(TourRepository repository) {
        super(repository);
    }

    @Override public Iterable<Tour> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override public Optional<Tour> findById(long id) {
        return repository.findById(id);
    }
}
