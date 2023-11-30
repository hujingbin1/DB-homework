package books.serverlet.service.impl;
import books.serverlet.bean.*;
import books.serverlet.dao.BooksDao;
import books.serverlet.dao.OrderDao;
import books.serverlet.dao.OrderItemDao;
import books.serverlet.dao.Usersdao;
import books.serverlet.dao.impl.BooksDaoimpl;
import books.serverlet.dao.impl.OrderDaoimpl;
import books.serverlet.dao.impl.OrderItemDaoimpl;
import books.serverlet.dao.impl.Usersdaoimpl;
import books.serverlet.service.OrderService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.registry.infomodel.User;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName:OrderServiceimpl
 * Package:books.serverlet.service.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1917:22
 * Version 1.0
 */
public class OrderServiceimpl implements OrderService {
    Usersdao usersdao=new Usersdaoimpl();
    OrderDao orderDao=new OrderDaoimpl();
    @Override
    public String createOrder(String username, Cart mycart) {
        String uuid=null;
        try {
            Order order=new Order();
            uuid=UUID.randomUUID().toString();
            order.setOrderSequence(uuid);
            order.setOrderStatus(0);
            order.setCreateTime( new SimpleDateFormat("dd-MM-yy:HH:mm:ss").format(new Date()));
            order.setTotalCount(mycart.getTotalCount());
            order.setTotalAmount(mycart.getCartTotalPrice());
            Users users=usersdao.login(username);
            order.setUserId(users.getId());
            OrderDao orderdao=new OrderDaoimpl();
            Integer orderId=orderdao.saveOrder(order);
            OrderItemDao orderItemDao=new OrderItemDaoimpl();
            Collection<CartItem> list=mycart.getCartList();
            int[] result=orderItemDao.insertOrderItem(list,orderId);
            BooksDao booksDao=new BooksDaoimpl();
            booksDao.updateStockSales(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单操作失败");

        }
        return uuid;
    }

    @Override
    public Collection<Order> getUsersOfOrder(String username,String pIndex) {
        Users users=(Users)usersdao.login(username);
        Collection<Order> orders=orderDao.getOrder(users.getId(),pIndex);
        return orders;
    }

    @Override
    public Integer getTotalPage(String username) {
        Users users=(Users)usersdao.login(username);
        Integer totalPage=orderDao.getTotalPage(users.getId());
        return totalPage;
    }

}
