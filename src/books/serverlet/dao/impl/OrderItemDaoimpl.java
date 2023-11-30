package books.serverlet.dao.impl;

import books.serverlet.bean.Cart;
import books.serverlet.bean.CartItem;
import books.serverlet.dao.BaseDao;
import books.serverlet.dao.JDBCUtil;
import books.serverlet.dao.OrderItemDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ClassName:OrderItemDaoimpl
 * Package:books.serverlet.dao.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-2010:09
 * Version 1.0
 */
public class OrderItemDaoimpl extends BaseDao implements OrderItemDao {
    @Override
    public int[] insertOrderItem(Collection<CartItem> list, Integer orderId) {
        int[] result=null;
        try {
            connection= JDBCUtil.getConnection();
            String sql="insert into t_order_item values(null,?,?,?,?,?,?);";
            pps=connection.prepareStatement(sql);
            for(CartItem item:list)
            {
                pps.setObject(1,item.getTitle());
                pps.setObject(2,item.getPrice());
                pps.setObject(3,item.getImgPath());
                pps.setObject(4,item.getCount());
                pps.setObject(5,item.getTotalPrice());
                pps.setObject(6,orderId);
                pps.addBatch();
            }
            result=pps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("保存订单详情失败");
        }
        return result;
    }
}
