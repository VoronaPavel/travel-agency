package by.pavel.service.tour;

import by.pavel.domain.tour.Tour;
import by.pavel.domain.tour.TourDto;
import by.pavel.repository.Pageable;
import by.pavel.service.CrudService;
import by.pavel.service.ServiceException;

import javax.validation.Valid;
import java.util.Optional;

public interface TourService extends CrudService<Tour, TourDto> {

    Iterable<Tour> findAll(@Valid Pageable context) throws ServiceException;
    Optional<Tour> findById(long id) throws ServiceException;
}
