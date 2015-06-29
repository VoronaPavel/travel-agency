package by.pavel.controller.travel;

import by.pavel.controller.Controller;
import by.pavel.domain.tour.Tour;
import by.pavel.repository.Pageable;
import by.pavel.service.tour.TourService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.pavel.Attribute.Request.TOURS;
import static by.pavel.View.TRAVEL;

@Singleton
public class TravelController extends Controller {

    @Inject TourService tourService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pageable pageable = getPageable();
        Iterable<Tour> tours = tourService.findAll(pageable);
        setRequestAttribute(TOURS, tours);
        view(TRAVEL);
    }

    private Pageable getPageable() {
        return Pageable.builder()
                .setPageNumber(1)
                .setPageSize(10)
                .build();
    }

}
