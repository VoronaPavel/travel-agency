package by.pavel.filter.security;

import by.pavel.domain.user.User;
import by.pavel.filter.Filter;

import javax.inject.Singleton;
import javax.servlet.*;
import java.io.IOException;

import static by.pavel.View.LOGIN;
import static by.pavel.Attribute.Session.USER;
import static java.util.Objects.nonNull;

@Singleton
public class SecurityFilter extends Filter {

    private String adminMail;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = getSessionAttribute(USER, User.class);
        if (notPresentOrDontHavePermission(user))
            redirect(LOGIN);
        else chain.doFilter(request, response);
    }

    private boolean notPresentOrDontHavePermission(User user) {
        return !(nonNull(user) && user.getEmail().equals(adminMail));
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {
        adminMail = filterConfig.getInitParameter("admin.mail");
    }
}
