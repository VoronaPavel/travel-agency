package by.pavel.filter;

import by.pavel.View;
import by.pavel.Attribute;
import com.google.inject.Provider;
import com.google.inject.Inject;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class Filter implements javax.servlet.Filter {

    @Inject private Provider<RequestHandler> delegate;

    protected void redirect(View location) throws IOException {
        redirect(location.getExternalAddress());
    }

    private void redirect(String location) throws IOException {
        getHandler().redirect(location);
    }

    protected <T> T getSessionAttribute(Attribute.Session attribute, Class<T> clazz) {
        return getSessionAttribute(attribute.toString(), clazz);
    }

    private <T> T getSessionAttribute(String name, Class<T> clazz) {
        return (T) getSessionAttribute(name);
    }

    private Object getSessionAttribute(String name) {
        return getHandler().getSessionAttribute(name);
    }

    protected void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
        getHandler().setCharacterEncoding(encoding);
    }

    private RequestHandler getHandler() {
        return delegate.get();
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {}

    @Override public void destroy() {}
}
