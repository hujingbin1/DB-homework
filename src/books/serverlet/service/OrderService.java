package books.serverlet.service;

import books.serverlet.bean.Cart;
import books.serverlet.bean.CartItem;
import books.serverlet.bean.Order;
import books.serverlet.bean.OrderItem;

import java.util.Collection;

/**
 * ClassName:OrderService
 * Package:books.serverlet.service
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1917:21
 * Version 1.0
 */
public interface OrderService {
    public String createOrder(String username, Cart mycart);
    public Collection<Order> getUsersOfOrder(String username,String pIndex);
   public Integer getTotalPage(String username);
}
