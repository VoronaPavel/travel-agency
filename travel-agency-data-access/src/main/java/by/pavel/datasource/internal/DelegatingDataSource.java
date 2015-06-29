package by.pavel.datasource.internal;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

abstract class DelegatingDataSource implements DataSource {

    private final DataSource delegate;

    protected DelegatingDataSource(DataSource delegate) {
        this.delegate = delegate;
    }

    @Override
    public Connection getConnection(String s, String s1) throws SQLException {
        return delegate.getConnection(s, s1);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return delegate.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {
        delegate.setLogWriter(printWriter);
    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {
        delegate.setLoginTimeout(i);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return delegate.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return delegate.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return delegate.unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return delegate.isWrapperFor(aClass);
    }
}
