package by.pavel.filter;

import com.google.inject.servlet.RequestScoped;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RequestScoped
public class RequestHandler {

    @Inject private ServletRequest request;
    @Inject private ServletResponse response;

    protected Object getSessionAttribute(String name) {
        return getSession().getAttribute(name);
    }

    private HttpSession getSession() {
        return getHttpRequest().getSession();
    }

    protected void redirect(String location) throws IOException {
        getHttpResponse().sendRedirect(location);
    }

    protected void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
        request.setCharacterEncoding(encoding);
    }

    private HttpServletRequest getHttpRequest() {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse getHttpResponse() {
        return (HttpServletResponse) response;
    }
}
