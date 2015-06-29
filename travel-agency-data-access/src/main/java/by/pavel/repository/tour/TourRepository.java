package by.pavel.repository.tour;

import by.pavel.domain.tour.Tour;
import by.pavel.domain.tour.TourDto;
import by.pavel.repository.CrudRepository;
import by.pavel.repository.Pageable;

import java.util.Optional;

public interface TourRepository extends CrudRepository<Tour, TourDto> {

    Iterable<Tour> findAll(Pageable context);
    Optional<Tour> findById(long id);
}
