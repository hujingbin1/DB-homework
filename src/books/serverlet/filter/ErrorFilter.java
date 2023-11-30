package books.serverlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:ErrorFilter
 * Package:books.serverlet.filter
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-2022:11
 * Version 1.0
 */
public class ErrorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception e) {
            HttpServletResponse resp=(HttpServletResponse) servletResponse;
            resp.sendRedirect("/books/pages/error/error.html");
            e.printStackTrace();
        }

    }
}
