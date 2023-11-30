package books.serverlet.filter;

import books.serverlet.dao.JDBCUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * ClassName:CloseConnectionFilter
 * Package:books.serverlet.filter
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-2021:20
 * Version 1.0
 */
public class CloseConnectionFilter implements Filter {
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

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("结账失败");
        }finally{
            JDBCUtil.free();
        }
    }
}
