package by.pavel.domain.tour;

import by.pavel.domain.Entity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

public class Tour extends Entity {

    private String title;
    private String description;

    public Tour(long id, @NotBlank String title, @NotBlank String description) {
        super(id);
        this.title = title;
        this.description = description;
    }

    private Tour(long id, TourDto dto) {
        super(id);
        title = dto.title;
        description = dto.description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    public static Tour from(@Valid TourDto dto, long id) {
        return new Tour(id, dto);
    }
}
