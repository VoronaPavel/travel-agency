package by.pavel.repository.internal;

import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static by.pavel.DataAccessException.rethrow;
import static java.util.Objects.isNull;

public class JdbcUtils {

    private JdbcUtils() {}

    public static void commitWithRollbackOnFail(@Nullable Connection connection) {
        if (isNull(connection)) return;

        try {
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
        }
    }

    public static void rollback(@Nullable Connection connection) {
        if (isNull(connection)) return;

        try {
            connection.rollback();
        } catch (SQLException e) {
            rethrow(e);
        }
    }

    public static void setParameters(PreparedStatement statement, List<?> parameters) throws SQLException {
        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }
    }

}
