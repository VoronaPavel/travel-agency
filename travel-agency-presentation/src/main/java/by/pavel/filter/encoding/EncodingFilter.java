package by.pavel.filter.encoding;

import by.pavel.filter.Filter;

import javax.inject.Singleton;
import javax.servlet.*;
import java.io.IOException;

@Singleton
public class EncodingFilter extends Filter {

    private String encoding;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }
}
