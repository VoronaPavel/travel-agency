package by.pavel.controller.authentification;

import by.pavel.controller.Controller;
import by.pavel.domain.user.User;
import by.pavel.service.user.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.pavel.View.HOME;
import static by.pavel.View.LOGIN;
import static by.pavel.Attribute.Request.ERROR;
import static by.pavel.Attribute.Session.USER;
import static by.pavel.Parameter.Request.EMAIL;
import static by.pavel.Parameter.Request.PASSWORD;
import static by.pavel.tag.Error.INVALID_EMAIL_OR_PASSWORD;
import static java.util.Objects.nonNull;

@Singleton
public class LoginController extends Controller {

    @Inject private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        view(LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (loggedIn()) {
            redirect(HOME);
            return;
        }

        String email = getRequestParameter(EMAIL);
        String password = getRequestParameter(PASSWORD);
        Optional<User> optional = userService.findByEmailPassword(email, password);
        if (optional.isPresent()) {
            User user = optional.get();
            setSessionAttribute(USER, user);
            redirect(HOME);
        } else {
            setRequestAttribute(ERROR, INVALID_EMAIL_OR_PASSWORD);
            view(LOGIN);
        }
    }

    private boolean loggedIn() {
        User user = getSessionAttribute(USER, User.class);
        return nonNull(user);
    }
}
