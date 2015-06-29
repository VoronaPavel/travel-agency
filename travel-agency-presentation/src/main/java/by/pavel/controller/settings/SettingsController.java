package by.pavel.controller.settings;

import by.pavel.controller.Controller;
import by.pavel.domain.user.User;
import by.pavel.service.user.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.pavel.View.SETTINGS;
import static by.pavel.Attribute.Session.USER;
import static by.pavel.Parameter.Request.NEW_PASSWORD;

@Singleton
public class SettingsController extends Controller {

    @Inject private UserService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        view(SETTINGS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = getRequestParameter(NEW_PASSWORD);
        User user = getSessionAttribute(USER, User.class);
        user.setPassword(newPassword);
        setSessionAttribute(USER, user);
        service.update(user);
        view(SETTINGS);
    }
}
