package by.pavel.datasource.internal;

import by.pavel.datasource.Pool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

class ProxyConnection implements Connection {

    private final Connection original;
    private final Pool<Connection> parent;

    ProxyConnection(Connection original, Pool<Connection> parent) throws SQLException {
        this.original = original;
        this.parent = parent;
        configure();
    }

    private void configure() throws SQLException {
        original.setAutoCommit(false);
    }

    @Override public PreparedStatement prepareStatement(String query) throws SQLException {
        return original.prepareStatement(query, RETURN_GENERATED_KEYS);
    }

    @Override public void close() {
        parent.put(this);
    }

    //Delegating methods

    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        return original.prepareCall(s);
    }

    @Override
    public String nativeSQL(String s) throws SQLException {
        return original.nativeSQL(s);
    }

    @Override
    public void setAutoCommit(boolean b) throws SQLException {
        original.setAutoCommit(b);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return original.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        original.commit();
    }

    @Override
    public void rollback() throws SQLException {
        original.rollback();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return original.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return original.getMetaData();
    }

    @Override
    public void setReadOnly(boolean b) throws SQLException {
        original.setReadOnly(b);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return original.isReadOnly();
    }

    @Override
    public void setCatalog(String s) throws SQLException {
        original.setCatalog(s);
    }

    @Override
    public String getCatalog() throws SQLException {
        return original.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int i) throws SQLException {
        original.setTransactionIsolation(i);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return original.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return original.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        original.clearWarnings();
    }

    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return original.createStatement(i, i1);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        return original.prepareStatement(s, i, i1);
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        return original.prepareCall(s, i, i1);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return original.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        original.setTypeMap(map);
    }

    @Override
    public void setHoldability(int i) throws SQLException {
        original.setHoldability(i);
    }

    @Override
    public int getHoldability() throws SQLException {
        return original.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return original.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        return original.setSavepoint(s);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        original.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        original.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return original.createStatement(i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return original.prepareStatement(s, i, i1, i2);
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        return original.prepareCall(s, i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        return original.prepareStatement(s, i);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        return original.prepareStatement(s, ints);
    }

    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        return original.prepareStatement(s, strings);
    }

    @Override
    public Clob createClob() throws SQLException {
        return original.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return original.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return original.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return original.createSQLXML();
    }

    @Override
    public boolean isValid(int i) throws SQLException {
        return original.isValid(i);
    }

    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        original.setClientInfo(s, s1);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        original.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String s) throws SQLException {
        return original.getClientInfo(s);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return original.getClientInfo();
    }

    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return original.createArrayOf(s, objects);
    }

    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        return original.createStruct(s, objects);
    }

    @Override
    public void setSchema(String s) throws SQLException {
        original.setSchema(s);
    }

    @Override
    public String getSchema() throws SQLException {
        return original.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        original.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {
        original.setNetworkTimeout(executor, i);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return original.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return original.unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return original.isWrapperFor(aClass);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return original.createStatement();
    }
}
