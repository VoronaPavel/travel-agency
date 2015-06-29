package by.pavel.repository.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface DataExtractor<T> {

    T extract(ResultSet resultSet) throws SQLException;
}
