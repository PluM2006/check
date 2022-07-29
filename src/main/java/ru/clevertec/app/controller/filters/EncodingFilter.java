package ru.clevertec.app.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import ru.clevertec.app.connection.ConnectionPool;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}