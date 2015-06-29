package by.pavel.datasource;

public interface Pool<T> {
    void put(T connection);
}
