package books.serverlet.filter;


import books.serverlet.dao.JDBCUtil;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName:TransactionFilter
 * Package:books.serverlet.filter
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-2021:08
 * Version 1.0
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Connection connection=null;
        try {
            connection= JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            filterChain.doFilter(servletRequest,servletResponse);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
