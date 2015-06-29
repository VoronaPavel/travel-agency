package by.pavel.controller.home;

import by.pavel.controller.Controller;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.pavel.View.HOME;

@Singleton
public class HomeController extends Controller {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        view(HOME);
    }
}
