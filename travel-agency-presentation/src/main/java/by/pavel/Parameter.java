package by.pavel;

public class Parameter {

    public enum Request {
        EMAIL,
        PASSWORD,
        NEW_PASSWORD {
            @Override
            public String toString() {
                return "newPassword";
            }
        };

        @Override public String toString() {
            return name().toLowerCase();
        }
    }

}
