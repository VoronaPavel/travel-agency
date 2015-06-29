package by.pavel.repository.internal.tour;

import by.pavel.domain.tour.Tour;
import by.pavel.domain.tour.TourDto;
import by.pavel.repository.Pageable;
import by.pavel.repository.internal.Query;
import by.pavel.repository.internal.QueryExecutor;
import by.pavel.repository.tour.TourRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

import static by.pavel.repository.internal.StatementCallback.SimpleCallbackFor;
import static by.pavel.repository.internal.tour.Extractors.collectionExtractor;
import static by.pavel.repository.internal.tour.Extractors.instanceExtractor;

@Singleton
class TourRepositoryImpl implements TourRepository {

    private final QueryExecutor template;

    @Inject TourRepositoryImpl(QueryExecutor template) {
        this.template = template;
    }

    @Override public Tour save(TourDto dto) {
        Query query = Queries.saveDto(dto);
        Long id = template.execute(query, SimpleCallbackFor(Long.class));
        return Tour.from(dto, id);
    }

    @Override public void save(Tour entity) {
        Query query = Queries.save(entity);
        template.execute(query);
    }

    @Override public void update(Tour entity) {
        Query query = Queries.update(entity);
        template.execute(query);
    }

    @Override public void delete(Tour entity) {
        Query query = Queries.delete(entity);
        template.execute(query);
    }

    @Override public Iterable<Tour> findAll(Pageable pageable) {
        Query query = Queries.findAll(pageable);
        return template.execute(query, collectionExtractor());
    }

    @Override public Optional<Tour> findById(long id) {
        Query query = Queries.findById(id);
        Tour tour = template.execute(query, instanceExtractor());
        return Optional.ofNullable(tour);
    }
}
