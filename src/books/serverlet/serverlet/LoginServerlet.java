package books.serverlet.serverlet;

import books.serverlet.service.UsersService;
import books.serverlet.service.impl.UsersServiceimpl;
import books.serverlet.utils.MD5Util;
import books.serverlet.bean.Users;
import books.serverlet.utils.viewServerlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName:LoginServerlet
 * Package:tianjiao.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-10-2711:42
 * Version 1.0
 */
@WebServlet("/pages/user/login")
public class LoginServerlet extends viewServerlet {
    public void loginIn(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            UsersService usersService=new UsersServiceimpl();
            Users users=usersService.login(username);
            if(users!=null)
            {
                password= MD5Util.encode(password);
                if(password.equals(users.getPassWord())) {
                    HttpSession session=req.getSession();
                    users.setPassWord(null);
                    session.setAttribute("loginUser",users);
                    processTemplate("pages/user/login_success",req,resp);
                }
                else
                {
//                String uname=req.getParameter("username");
//                Cookie cookie= new Cookie("cookie_username",uname);
//                resp.addCookie(cookie);
//                processTemplate("pages/user/login",req,resp);
                    resp.setContentType("text/html;charset=utf-8");
                    Writer writer=resp.getWriter();
                    writer.write("<script type='text/javascript'>alert('登录失败，输入的用户名或者密码错误');location.href='login.html'</script>");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void loginOut(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            HttpSession session=req.getSession();
            session.invalidate();
           resp.sendRedirect("/books/index?reqkey=getBypageUtil&pIndex=1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String reqkey=req.getParameter("reqkey");
            Method method=this.getClass().getDeclaredMethod(reqkey,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
