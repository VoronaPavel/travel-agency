package by.pavel.domain.tour;

import by.pavel.domain.Dto;
import org.hibernate.validator.constraints.NotBlank;

public class TourDto implements Dto<Tour> {
    @NotBlank
    public final String title;
    @NotBlank
    public final String description;

    public TourDto(@NotBlank String title, @NotBlank String description) {
        this.title = title;
        this.description = description;
    }
}
