package books.serverlet.dao;

import books.serverlet.bean.CartItem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ClassName:OrderItemDao
 * Package:books.serverlet.dao
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-2010:08
 * Version 1.0
 */
public interface OrderItemDao {
    public int[] insertOrderItem(Collection<CartItem> list, Integer orderId);
}
