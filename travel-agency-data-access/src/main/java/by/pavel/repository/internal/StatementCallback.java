package by.pavel.repository.internal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface StatementCallback<T> {

    T call(PreparedStatement statement) throws SQLException;

    static <T> StatementCallback<T> SimpleCallbackFor(Class<T> tClass) {
        return (PreparedStatement statement) -> {
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getObject(1, tClass);
            }
        };
    }
}
