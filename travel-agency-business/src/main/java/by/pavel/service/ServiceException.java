package by.pavel.service;

public class ServiceException extends RuntimeException {

    public ServiceException(Throwable throwable) {
        super(throwable);
    }
}
