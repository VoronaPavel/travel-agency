package by.pavel;

import java.sql.SQLException;

public class DataAccessException extends RuntimeException {

    private DataAccessException(Throwable throwable) {
        super(throwable);
    }

    private DataAccessException(String s) {
        super(s);
    }

    public static void rethrow(SQLException e) {
        throw new DataAccessException(e);
    }

    public static class NoConnectionsException extends DataAccessException {
        public NoConnectionsException() {
            super("Cannot connect to DataSource. ");
        }
    }
}
