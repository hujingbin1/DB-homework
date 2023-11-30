package books.serverlet.serverlet;

import books.serverlet.bean.*;
import books.serverlet.service.OrderService;
import books.serverlet.service.impl.OrderServiceimpl;
import books.serverlet.utils.DataUtils;
import books.serverlet.utils.PageUtil;
import books.serverlet.utils.viewServerlet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.beans.VetoableChangeListenerProxy;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * ClassName:OrderOfMy
 * Package:books.serverlet.serverlet
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-2022:51
 * Version 1.0
 */
@WebServlet("/pages/cart/orderMyserverlet")
public class OrderOfMyServerlet extends viewServerlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session=req.getSession();
            OrderService orderService=new OrderServiceimpl();
            Users user=(Users)session.getAttribute("loginUser");
            String pIndex=req.getParameter("pIndex");
            String username=user.getUserName();
            Collection<Order>  list=orderService.getUsersOfOrder(username,pIndex);
            Integer totalCount=orderService.getTotalPage(username);//这里实际求的是总的数据数
            PageUtil<Order> pageUtil=new PageUtil<>();
            pageUtil.setPageIndex(Integer.parseInt(pIndex));
            pageUtil.setDataList((List<Order>)list);
            pageUtil.setTotalCount(totalCount);
            Integer totalPage=totalCount%DataUtils.PAGESIZE>0?totalCount/DataUtils.PAGESIZE:totalCount/DataUtils.PAGESIZE+1;
            pageUtil.setTotalPage(totalPage);
             req.setAttribute("pu", pageUtil);
            processTemplate("pages/order/order",req,resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
