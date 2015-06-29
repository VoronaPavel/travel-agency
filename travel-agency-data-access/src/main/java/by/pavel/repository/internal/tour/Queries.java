package by.pavel.repository.internal.tour;

import by.pavel.domain.tour.Tour;
import by.pavel.domain.tour.TourDto;
import by.pavel.repository.Pageable;
import by.pavel.repository.internal.Query;

class Queries {

    private static final String querySave = "INSERT INTO tours (title, description) VALUES (?,?)";
    private static final String queryDelete = "DELETE FROM tours WHERE id = ?";
    private static final String queryUpdate = "UPDATE tours SET title = ?, description = ? WHERE id = ?";
    private static final String queryFindAll = "SELECT * FROM tours LIMIT ? OFFSET ?";
    private static final String queryFindById = "SELECT * FROM tours WHERE id = ?";

    private Queries() {}

    public static Query save(Tour tour) {
        return Query.builder(querySave).setParameters(tour.getTitle(), tour.getDescription()).build();
    }

    public static Query delete(Tour tour) {
        return Query.builder(queryDelete).setParameters(tour.getId()).build();
    }

    public static Query update(Tour tour) {
        return Query.builder(queryUpdate).setParameters(tour.getTitle(), tour.getDescription(), tour.getId()).build();
    }

    public static Query saveDto(TourDto dto) {
        return Query.builder(querySave).setParameters(dto.title, dto.description).build();
    }

    public static Query findAll(Pageable context) {
        return Query.builder(queryFindAll).setParameters(context.getPageSize(), context.getOffset()).build();
    }

    public static Query findById(long id) {
        return Query.builder(queryFindById).setParameters(id).build();
    }
}
