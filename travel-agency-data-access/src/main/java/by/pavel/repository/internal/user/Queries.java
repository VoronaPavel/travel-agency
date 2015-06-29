package by.pavel.repository.internal.user;

import by.pavel.domain.user.User;
import by.pavel.domain.user.UserDto;
import by.pavel.repository.internal.Query;

import static by.pavel.repository.internal.Query.builder;

class Queries {

    private static final String querySave = "INSERT INTO users (email, password) VALUES (?,?)";
    private static final String queryDelete = "DELETE FROM users WHERE id = ?";
    private static final String queryUpdate = "UPDATE users SET email = ?, password = ? WHERE id = ?";
    private static final String queryFindByEmail = "SELECT * FROM users WHERE email = ?";
    private static final String queryFindByEmailPassword = "SELECT * FROM users WHERE email = ? AND password = ?";

    private Queries() {}

    public static Query save(User user) {
        return builder(querySave).setParameters(user.getEmail(), user.getPassword()).build();
    }

    public static Query update(User user) {
        return builder(queryUpdate).setParameters(user.getEmail(), user.getPassword(), user.getId()).build();
    }

    public static Query delete(User user) {
        return builder(queryDelete).setParameters(user.getId()).build();
    }

    public static Query saveDto(UserDto dto) {
        return builder(querySave).setParameters(dto.email, dto.password).build();
    }

    public static Query findByEmail(String email) {
        return builder(queryFindByEmail).setParameters(email).build();
    }

    public static Query findByEmailPassword(String email, String password) {
        return builder(queryFindByEmailPassword).setParameters(email, password).build();
    }
}
