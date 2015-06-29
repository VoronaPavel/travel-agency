package by.pavel.controller.authentification;

import by.pavel.controller.Controller;
import by.pavel.domain.user.User;
import by.pavel.domain.user.UserDto;
import by.pavel.service.user.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static by.pavel.View.HOME;
import static by.pavel.View.REGISTRATION;
import static by.pavel.Attribute.Session.USER;
import static by.pavel.Parameter.Request.EMAIL;
import static by.pavel.Parameter.Request.PASSWORD;

@Singleton
public class RegistrationController extends Controller {

    @Inject private UserService userService;
    @Inject private Validator validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        view(REGISTRATION);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = getRequestParameter(EMAIL);
        String password = getRequestParameter(PASSWORD);
        UserDto userDTO = new UserDto(email, password);
        if (userExistsOrInvalidParameters(userDTO)) {
            view(REGISTRATION);
            return;
        }
        User user = userService.save(userDTO);
        setSessionAttribute(USER, user);
        redirect(HOME);
    }

    private boolean userExistsOrInvalidParameters(UserDto dto) {
        Optional<User> optional = userService.findByEmail(dto.email);
        return optional.isPresent() || !isValid(dto);
    }

    private boolean isValid(UserDto userDTO) {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDTO);
        return constraintViolations.isEmpty();
    }
}
