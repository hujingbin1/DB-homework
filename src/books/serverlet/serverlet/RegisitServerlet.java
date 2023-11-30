package books.serverlet.serverlet;

import org.apache.commons.beanutils.BeanUtils;
import books.serverlet.bean.Users;
import books.serverlet.service.UsersService;
import books.serverlet.service.impl.UsersServiceimpl;
import books.serverlet.utils.MD5Util;
import books.serverlet.utils.viewServerlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * ClassName:RegisitServerlet
 * Package:tianjiao.serverlet.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-0622:27
 * Version 1.0
 */
@WebServlet("/pages/user/regist")
public class RegisitServerlet extends viewServerlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Users users=new Users();
            Map<String,String[]> map=req.getParameterMap();
            String inputEncoding=map.get("inputcoding")[0];
            String serverCoding=(String)req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
            BeanUtils.populate(users,map);
            if(serverCoding.equals(inputEncoding)) {
                UsersService usersService=new UsersServiceimpl();
                users.setPassWord(MD5Util.encode(users.getPassWord()));
                boolean bool = usersService.regist(users);
                if (bool) {
                    resp.sendRedirect("login.html");
                } else {
                    resp.sendRedirect("regist.html");
                }
            }
            else
            {
                req.setAttribute("errormessage","验证码错误");
                processTemplate("pages/user/regist",req,resp);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
