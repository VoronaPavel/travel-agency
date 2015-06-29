package by.pavel.repository.internal;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.pavel.DataAccessException.rethrow;
import static by.pavel.repository.internal.JdbcUtils.commitWithRollbackOnFail;

@Singleton
public class QueryExecutor {

    private final DataSource source;

    @Inject QueryExecutor(DataSource source) {
        this.source = source;
    }

    public void execute(Query query) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query.getQuery())) {
                setParameters(query, statement);
                statement.executeUpdate();
                commitWithRollbackOnFail(connection);
            }
        } catch (SQLException e) {
            rethrow(e);
        }
    }

    public <T> T execute(Query query, StatementCallback<T> callback) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query.getQuery())) {
                setParameters(query, statement);
                statement.executeUpdate();
                commitWithRollbackOnFail(connection);
                return callback.call(statement);
            }
        } catch (SQLException e) {
            rethrow(e);
        }
        throw new AssertionError(); //should never happen
    }

    @Nullable
    public <T> T execute(Query query, DataExtractor<T> dataExtractor) {
        T data = null;
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query.getQuery())) {
                setParameters(query, statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    data = dataExtractor.extract(resultSet);
                }
            }
        } catch (SQLException e) {
            rethrow(e);
        }
        return data;
    }

    //todo develop better API to get rid of this method and code duplication in others
    private void setParameters(Query query, PreparedStatement statement) throws SQLException {
        ImmutableList<Object> parameters = query.getParameters();
        JdbcUtils.setParameters(statement, parameters);
    }
}
