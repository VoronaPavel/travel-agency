package by.pavel.repository.internal.tour;

import by.pavel.domain.tour.Tour;
import by.pavel.repository.internal.DataExtractor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

class Extractors {

    public static DataExtractor<Tour> instanceExtractor() {
        return (ResultSet resultSet)-> {
            if (!resultSet.next()) return null;
            Long id = resultSet.getLong(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            return new Tour(id, title, description);
        };
    }

    public static DataExtractor<Collection<Tour>> collectionExtractor() {
        return (ResultSet resultSet)-> {
            Collection<Tour> collection = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                collection.add(new Tour(id, title, description));
            }
            return collection;
        };
    }

    private Extractors() {}
}
