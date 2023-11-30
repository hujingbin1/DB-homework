package books.serverlet.serverlet;

import books.serverlet.service.UsersService;
import books.serverlet.service.impl.UsersServiceimpl;
import books.serverlet.utils.CommonResult;
import books.serverlet.utils.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:RegistServerletCheckName
 * Package:books.serverlet.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1121:27
 * Version 1.0
 */
@WebServlet("/pages/user/registCheckname")
public class RegistServerletCheckName extends HttpServlet {
    UsersService usersService=new UsersServiceimpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            String uname=req.getParameter("username");
            boolean bl= usersService.checkUname(uname);
            CommonResult commonResult= CommonResult.requestOK();
            if(bl)
            {
                commonResult.setMessage("该用户名已存在，不能使用");
            }
            else
            {
                commonResult.setMessage("√");
            }
            JSONUtils.writeResult(resp,commonResult);
        } catch (Exception e) {
           CommonResult comError=CommonResult.requestError();
           comError.setMessage("后台处理数据失败");
           JSONUtils.writeResult(resp,comError);
            throw new RuntimeException(e);
        }

    }
}
