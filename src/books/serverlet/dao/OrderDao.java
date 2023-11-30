package books.serverlet.dao;

import books.serverlet.bean.Cart;
import books.serverlet.bean.CartItem;
import books.serverlet.bean.Order;
import books.serverlet.bean.OrderItem;

import java.util.Collection;

/**
 * ClassName:OrderDao
 * Package:books.serverlet.dao
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-209:18
 * Version 1.0
 */
public interface OrderDao {
    public Integer saveOrder(Order order);
    public Collection<Order> getOrder(Integer userid,String pIndex);
    public Integer getTotalPage(Integer userId);
}
