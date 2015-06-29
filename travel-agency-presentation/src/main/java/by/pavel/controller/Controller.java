package by.pavel.controller;

import by.pavel.Attribute;
import by.pavel.Parameter;
import by.pavel.View;
import com.google.inject.Provider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Inject private Provider<HttpRequestHandler> delegate;

    protected void view(View view) throws ServletException, IOException {
        getHandler().view(view.getInternalAddress());
    }

    protected void setSessionAttribute(String name, Object value) {
        getHandler().setSessionAttribute(name, value);
    }

    protected void setSessionAttribute(Attribute.Session name, Object value) {
        setSessionAttribute(name.toString(), value);
    }

    protected void setRequestAttribute(Attribute.Request request, Object value) {
        setRequestAttribute(request.toString(), value);
    }

    protected void setRequestAttribute(Attribute.Request request, by.pavel.tag.Error error) {
        setRequestAttribute(request.toString(), error);
    }

    private void setRequestAttribute(String name, Object value) {
        getHandler().setRequestAttribute(name, value);
    }

    protected void redirect(View location) throws IOException {
        redirect(location.getExternalAddress());
    }

    private void redirect(String location) throws IOException {
        getHandler().redirect(location);
    }

    protected void redirect() throws IOException {
        getHandler().redirect();
    }

    protected void invalidateSession() {
        getHandler().invalidateSession();
    }

    protected <T> T getSessionAttribute(Attribute.Session name, Class<T> clazz) {
        return getSessionAttribute(name.toString(),  clazz);
    }

    private <T> T getSessionAttribute(String name, Class<T> clazz) {
        return (T) getSessionAttribute(name);
    }

    private Object getSessionAttribute(String name) {
        return getHandler().getSessionAttribute(name);
    }

    private String getRequestParameter(String name) {
        return getHandler().getRequestParameter(name);
    }

    protected String getRequestParameter(Parameter.Request name) {
        return getRequestParameter(name.toString());
    }

    private HttpRequestHandler getHandler() {
        return delegate.get();
    }
}
