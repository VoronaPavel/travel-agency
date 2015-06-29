package by.pavel.tag;

public enum Error {

    INVALID_EMAIL_OR_PASSWORD("Invalid email or password. ");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
