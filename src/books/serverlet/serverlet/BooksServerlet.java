package books.serverlet.serverlet;

import org.apache.commons.beanutils.BeanUtils;
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
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ClassName:booksserverlet
 * Package:tianjiao.serverlet.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-079:50
 * Version 1.0
 */
@WebServlet("/pages/manager/booksserverlet")
public class BooksServerlet extends viewServerlet{
    BooksService booksService =new BooksServiceimpl();
    protected void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String bid=req.getParameter("bid");
            boolean bl=booksService.deleteById(bid);
            resp.setContentType("text/html;charset=utf-8");
            Writer writer=resp.getWriter();
            if(bl)
            {
                writer.write("<script type='text/javascript'>alert('删除成功');location.href='/books/pages/manager/booksserverlet?reqkey=selectAll'</script>");
            }
            else
            {
                writer.write("<script type='text/javascript'>alert('删除失败');location.href='/books/pages/manager/booksserverlet?reqkey=selectAll'</script>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    protected void updateBook(HttpServletRequest req, HttpServletResponse resp){
        try {
            Books books=new Books();
            Map<String,String[]> map=req.getParameterMap();
            BeanUtils.populate(books,map);
            boolean bl=booksService.updateBook(books);
            if(bl)
            {
                resp.sendRedirect("/books/pages/manager/booksserverlet?reqkey=selectAll");
            }
            else
            {
                resp.sendRedirect("/books/pages/manager/booksserverlet?reqkey=selectById&bid="+books.getId());
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    protected void insertBook(HttpServletRequest req, HttpServletResponse resp){

        try {
            Books books=new Books();
            Map<String,String[]> map=req.getParameterMap();
            BeanUtils.populate(books,map);
            //这里的图片路径应该由文件上传，先假设一个存在的路径
            books.setImgPath("/static/uploads/bainiangudu.jpg");
            boolean bl= booksService.insert(books);
            if(bl)
            {
                resp.sendRedirect("/books/pages/manager/booksserverlet?reqkey=selectAll");
            }
            else
            {
                resp.sendRedirect("/books/pages/manager/book_add.html");
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
       String bid= req.getParameter("bid");
        Books books=booksService.getById(bid);
        req.setAttribute("booku",books);
        processTemplate("pages/manager/book_edit",req,resp);
    }
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String temppIndex=req.getParameter("pIndex");
        int pIndex=temppIndex==null?1:Integer.parseInt(temppIndex);
        PageUtil<Books> pageUtil;
        //第一种写法
//        List<Books> list=booksService.selectAll(pIndex, DataUtils.PAGESIZE);
//        int total=total();
//        int pages=total%DataUtils.PAGESIZE>0?total/DataUtils.PAGESIZE+1:total/DataUtils.PAGESIZE;
//        req.setAttribute("bookutils",list);
//        req.setAttribute("index",pIndex);
//        req.setAttribute("pages",pages);
//        req.setAttribute("total",total);
        //使用工具类的写法
        pageUtil=booksService.getBypageUtil(pIndex,0,Double.MAX_VALUE);
        req.setAttribute("pu",pageUtil);
        processTemplate("pages/manager/book_manager",req,resp);
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
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
