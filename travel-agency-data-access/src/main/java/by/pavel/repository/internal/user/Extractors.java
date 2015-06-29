package by.pavel.repository.internal.user;

import by.pavel.domain.user.User;
import by.pavel.repository.internal.DataExtractor;

import java.sql.ResultSet;

class Extractors {

    public static DataExtractor<User> instanceExtractor() {
        return (ResultSet resultSet) -> {
            if (!resultSet.next()) return null;
            Long id = resultSet.getLong(1);
            String email = resultSet.getString(2);
            String password = resultSet.getString(3);
            return new User(id, email, password);
        };
    }

    private Extractors() {}
}
