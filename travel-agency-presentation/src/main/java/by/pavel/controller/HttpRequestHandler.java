package by.pavel.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestScoped
public class HttpRequestHandler {

    @Inject private HttpServletRequest request;
    @Inject private HttpServletResponse response;

    public void view(String location) throws ServletException, IOException {
        request.getRequestDispatcher(location).forward(request, response);
    }

    protected void setSessionAttribute(String name, Object value) {
        request.getSession().setAttribute(name, value);
    }

    protected void setRequestAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    protected void redirect(String location) throws IOException {
        response.sendRedirect(location);
    }
    protected void redirect() throws IOException {
        response.sendRedirect(request.getContextPath());
    }

    protected void invalidateSession() {
        getSession().invalidate();
    }

    protected Object getSessionAttribute(String name) {
        return request.getSession().getAttribute(name);
    }

    protected String getRequestParameter(String name) {
        return request.getParameter(name);
    }

    private HttpSession getSession() {
        return request.getSession(false);
    }
}
