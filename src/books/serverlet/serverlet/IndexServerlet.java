package books.serverlet.serverlet;

import books.serverlet.bean.Books;
import books.serverlet.service.BooksService;
import books.serverlet.service.impl.BooksServiceimpl;
import books.serverlet.utils.PageUtil;
import books.serverlet.utils.viewServerlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName:IndexServerlet
 * Package:tianjiao.serverlet.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-0916:22
 * Version 1.0
 */
@WebServlet("/index")
public class IndexServerlet extends viewServerlet {
    BooksService booksService=new BooksServiceimpl();
    public void getBypageUtil(HttpServletRequest req,HttpServletResponse resp) {
        try {
            String temppIndex = req.getParameter("pIndex");
            String tempminPrice=req.getParameter("minPrice");
            String tempmaxPrice=req.getParameter("maxPrice");
            if("".equals(tempminPrice))
            {
                tempminPrice=null;
            }
            if("".equals(tempmaxPrice))
            {
                tempmaxPrice=null;
            }
            double minPrice=tempminPrice==null?0:Double.parseDouble(tempminPrice);
            double maxPrice=tempmaxPrice==null?Double.MAX_VALUE:Double.parseDouble(tempmaxPrice);
            int pIndex = temppIndex == null ? 1 : Integer.parseInt(temppIndex);
            PageUtil<Books> pageUtil;
            pageUtil = booksService.getBypageUtil(pIndex,minPrice,maxPrice);
            req.setAttribute("pu", pageUtil);
            if(minPrice!=0&&maxPrice!=Double.MAX_VALUE) {
                req.setAttribute("minp", minPrice);
                req.setAttribute("maxp", maxPrice);
            }
            processTemplate("index", req, resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
