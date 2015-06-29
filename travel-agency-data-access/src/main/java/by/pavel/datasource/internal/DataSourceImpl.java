package by.pavel.datasource.internal;

import by.pavel.DataAccessException;
import by.pavel.aspects.async.Async;
import by.pavel.datasource.Pool;
import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;

import static by.pavel.DataAccessException.NoConnectionsException;
import static java.sql.DriverManager.registerDriver;

@Singleton
class DataSourceImpl extends DelegatingDataSource implements Pool<Connection> {

    private final ConcurrentLinkedQueue<Connection> pool = new ConcurrentLinkedQueue<>();
    private final SqlConnectionCreator connectionCreator;
    private final PoolContext poolContext;

    @Inject
    DataSourceImpl(@Origin DataSource source, SqlConnectionCreator connectionCreator, PoolContext poolContext) throws SQLException {
        super(source);
        this.connectionCreator = connectionCreator;
        this.poolContext = poolContext;
        bootstrap();
    }

    @Async private void bootstrap() throws SQLException {
        for (long l = 0L; l < poolContext.initialConnections; l++) {
            put(newProxyConnection());
        }
    }

    @Override public synchronized Connection getConnection() throws DataAccessException {
        if (pool.isEmpty()) throw new NoConnectionsException();
        return pool.poll();
    }

    @Override public void put(Connection connection) {
        pool.add(connection);
    }

    private Connection newProxyConnection() throws SQLException {
        Connection connection = connectionCreator.createSqlConnection();
        return new ProxyConnection(connection, this);
    }

    @Singleton private static class SqlConnectionCreator {

        @Inject private DbContext dbContext;
        private boolean driverLoaded;

        public Connection createSqlConnection() throws SQLException {
            if (!driverLoaded) {
                registerDriver(dbContext.driver);
                driverLoaded = true;
            }
            return DriverManager.getConnection(dbContext.url, dbContext);
        }
    }
}
