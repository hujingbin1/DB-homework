package books.serverlet.filter;

import books.serverlet.bean.Users;
import books.serverlet.utils.CommonResult;
import books.serverlet.utils.JSONUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName:LoginFilter
 * Package:books.serverlet.filter
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1915:35
 * Version 1.0
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        HttpSession session=req.getSession();
        Users users=(Users)session.getAttribute("loginUser");
        String reqkey=req.getParameter("reqkey");
        if(reqkey.equals("getCartData")) {
            if (users == null) {
                CommonResult commonResult = CommonResult.requestError();
                commonResult.setMessage("unlogin");
                JSONUtils.writeResult(resp, commonResult);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        else
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
