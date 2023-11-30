package books.serverlet.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * ClassName:CharacterFilter
 * Package:books.serverlet.filter
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1916:05
 * Version 1.0
 */
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
