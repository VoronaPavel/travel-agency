package by.pavel.resource;

import by.pavel.domain.tour.Tour;
import by.pavel.service.tour.TourService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("tour")
@Singleton
public class TourResource {

    @Inject TourService tourService;
    @Inject Gson gson;

    @Path("{id}")
    @GET
    @Produces(APPLICATION_JSON)
    public String getTourById(@PathParam("id") long id) {
        Optional<Tour> optional = tourService.findById(id);
        if (optional.isPresent()) {
            Tour tour = optional.get();
            return gson.toJson(tour);
        }
        return gson.toJson(null);
    }
}
