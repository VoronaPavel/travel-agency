package by.pavel;

public class Attribute {

    public enum Request {
        ERROR,
        TOURS;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Session {
        USER;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

}
