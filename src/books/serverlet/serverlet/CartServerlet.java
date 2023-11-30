package books.serverlet.serverlet;

import books.serverlet.bean.Books;
import books.serverlet.bean.Cart;
import books.serverlet.bean.CartItem;
import books.serverlet.bean.Users;
import books.serverlet.service.BooksService;
import books.serverlet.service.impl.BooksServiceimpl;
import books.serverlet.utils.CommonResult;
import books.serverlet.utils.JSONUtils;
import books.serverlet.utils.PageUtil;
import org.w3c.dom.html.HTMLParagraphElement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.event.CaretListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:AddCartServerlet
 * Package:books.serverlet.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1214:26
 * Version 1.0
 */
@WebServlet("/cartserverlet")
public class CartServerlet extends HttpServlet {
    BooksService booksService=new BooksServiceimpl();
    public Map<String,Object> getchangedCart(HttpServletRequest req,HttpServletResponse resp)
    {
        CommonResult commonResult;
        HttpSession session=req.getSession();
        Map<String,Object> map=null;
        Cart mycart=(Cart)session.getAttribute("mycart");
        if(mycart!=null&&mycart.getTotalCount()!=0)
        {

            Collection<CartItem> blist=mycart.getCartList();
            Double totalprice=mycart.getCartTotalPrice();
            Integer totalcount=mycart.getTotalCount();
            map=new HashMap<>();
            map.put("blist",blist);
            map.put("bprice",totalprice);
            map.put("bcount",totalcount);
        }
        return map;
    }
    public void changecount(HttpServletRequest req,HttpServletResponse resp)
    {
        HttpSession session=req.getSession();
        Cart mycart=(Cart)session.getAttribute("mycart");
        String bookId=req.getParameter("bookId");
        String bCount=req.getParameter("bCount");
        mycart.changecount(bookId,bCount);
        HashMap<String,Object> map=(HashMap<String, Object>) getchangedCart(req,resp);
        CommonResult commonResult=CommonResult.requestOK();
        commonResult.setResultData(map);
        JSONUtils.writeResult(resp,commonResult);
    }
    public void deletebookitem(HttpServletRequest req,HttpServletResponse resp)
    {
        HttpSession session=req.getSession();
        Cart mycart=(Cart)session.getAttribute("mycart");
        String bookId=req.getParameter("bookId");
        mycart.deletebookitem(bookId);
        HashMap<String,Object> map=(HashMap<String, Object>) getchangedCart(req,resp);
        CommonResult commonResult=CommonResult.requestOK();
        commonResult.setResultData(map);
        JSONUtils.writeResult(resp,commonResult);
    }
    public void downcount(HttpServletRequest req,HttpServletResponse resp)
    {
        HttpSession session=req.getSession();
        Cart mycart=(Cart)session.getAttribute("mycart");
        String bookId=req.getParameter("bookId");
        mycart.downcount(bookId);
        HashMap<String,Object> map=(HashMap<String, Object>) getchangedCart(req,resp);
        CommonResult commonResult=CommonResult.requestOK();
        commonResult.setResultData(map);
        JSONUtils.writeResult(resp,commonResult);
    }
    public void updatecount(HttpServletRequest req,HttpServletResponse resp)
    {
        HttpSession session=req.getSession();
        Cart mycart=(Cart)session.getAttribute("mycart");
        String bookId=req.getParameter("bookId");
        mycart.updatecount(bookId);
        HashMap<String,Object> map=(HashMap<String, Object>) getchangedCart(req,resp);
       CommonResult commonResult=CommonResult.requestOK();
       commonResult.setResultData(map);
       JSONUtils.writeResult(resp,commonResult);
    }
    public void clearCart(HttpServletRequest req,HttpServletResponse resp)
    {
        try {
            req.getSession().removeAttribute("mycart");
            resp.sendRedirect("pages/cart/cart.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getCartData(HttpServletRequest req,HttpServletResponse resp)
    {         CommonResult commonResult;
        try {
            HttpSession session=req.getSession();
            Cart cart=(Cart) session.getAttribute("mycart");
            HashMap<String,Object> map=(HashMap<String, Object>) getchangedCart(req,resp);
            commonResult=CommonResult.requestOK();
            commonResult.setResultData(map);
        } catch (Exception e) {
            commonResult=CommonResult.requestError();
            commonResult.setResultData(null);
            throw new RuntimeException(e);
        }
        JSONUtils.writeResult(resp,commonResult);

    }
    public void gettotalcount(HttpServletRequest req,HttpServletResponse resp)
    {
        CommonResult commonResult;
        try {
            commonResult=CommonResult.requestOK();
            HttpSession session=req.getSession();
            Cart cart=(Cart) session.getAttribute("mycart");
            if(cart==null)
            {
                commonResult.setResultData(0);
            }
            else
            {
                commonResult.setResultData(cart.getTotalCount());
            }
            JSONUtils.writeResult(resp,commonResult);
        } catch (Exception e) {
            CommonResult commonResult1=CommonResult.requestError();
            commonResult1.setResultData(-1);
            JSONUtils.writeResult(resp,commonResult1);
            throw new RuntimeException(e);
        }

    }

    public void addCart(HttpServletRequest req,HttpServletResponse resp)
    {
        CommonResult commonResult;
        try {
            String bookid=req.getParameter("bookid");
            Books books=booksService.getById(bookid);
            HttpSession session=req.getSession();
            Cart mycart=(Cart) session.getAttribute("mycart");
            if(mycart==null)
            {
                Cart cart=new Cart();
                session.setAttribute("mycart",cart);
            }
            mycart=(Cart) session.getAttribute("mycart");
            mycart.addBooksToCart(books);
            commonResult=CommonResult.requestOK();
            commonResult.setResultData(mycart.getTotalCount());
            JSONUtils.writeResult(resp,commonResult);
        } catch (Exception e) {
            commonResult = CommonResult.requestError();
            commonResult.setResultData(-1);
            JSONUtils.writeResult(resp,commonResult);
            throw new RuntimeException(e);
        }


    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {
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
}
