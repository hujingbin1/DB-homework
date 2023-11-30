package books.serverlet.serverlet;

import books.serverlet.bean.Cart;
import books.serverlet.bean.Users;
import books.serverlet.service.OrderService;
import books.serverlet.service.impl.OrderServiceimpl;
import books.serverlet.utils.viewServerlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName:OrderServerlet
 * Package:books.serverlet.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1917:20
 * Version 1.0
 */
@WebServlet("/pages/cart/orderserverlet")
public class OrderServerlet extends viewServerlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session=req.getSession();
            Users users=(Users)session.getAttribute("loginUser");
            Cart mycart=(Cart)session.getAttribute("mycart");
            OrderService orderService=new OrderServiceimpl();
            String orderUUID= orderService.createOrder(users.getUserName(),mycart);
            session.removeAttribute("mycart");
            req.setAttribute("orderUUID",orderUUID);
            processTemplate("pages/cart/checkout",req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单处理失败");
        }
    }
}
