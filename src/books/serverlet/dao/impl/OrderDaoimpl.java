package books.serverlet.dao.impl;

import books.serverlet.bean.Cart;
import books.serverlet.bean.CartItem;
import books.serverlet.bean.Order;
import books.serverlet.bean.OrderItem;
import books.serverlet.dao.BaseDao;
import books.serverlet.dao.JDBCUtil;
import books.serverlet.dao.OrderDao;
import books.serverlet.dao.OrderItemDao;
import books.serverlet.utils.DataUtils;
import jdk.jshell.Snippet;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Filter;

/**
 * ClassName:OrderDaoimpl
 * Package:books.serverlet.dao.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-209:18
 * Version 1.0
 */
public class OrderDaoimpl extends BaseDao implements OrderDao {
    @Override
    public Integer saveOrder(Order order) {
        Integer orderId=null;
        try {
            connection= JDBCUtil.getConnection();
            String sql="insert into t_order values(null,?,?,?,?,?,?);";
            pps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pps.setObject(1,order.getOrderSequence());
            pps.setObject(2,order.getCreateTime());
            pps.setObject(3,order.getTotalCount());
            pps.setObject(4,order.getTotalAmount());
            pps.setObject(5,order.getOrderStatus());
            pps.setObject(6,order.getUserId());
            int i=pps.executeUpdate();
            ResultSet resultSet=pps.getGeneratedKeys();
            while(resultSet.next())
            {
                orderId=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("订单保存失败");
        }

        return orderId;
    }

    @Override
    public Collection<Order> getOrder(Integer userid,String pIndex) {
        Collection<Order> list=null;
        ResultSet resultSet=null;
        Integer pindex=Integer.parseInt(pIndex);
        try {
            String sql="select * from t_order where user_id = ? limit ?,?;";
            resultSet=query(sql,userid,(pindex-1)* DataUtils.PAGESIZE,DataUtils.PAGESIZE);
            list=new ArrayList<>();
                while(resultSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt(1));
                    order.setOrderSequence(resultSet.getString(2));
                    order.setCreateTime(resultSet.getString(3));
                    order.setTotalCount(resultSet.getInt(4));
                    order.setTotalAmount(resultSet.getDouble(5));
                    order.setOrderStatus(resultSet.getInt(6));
                    order.setUserId(resultSet.getInt(7));
                    list.add(order);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            free();
        }
        return list;
    }

    @Override
    public Integer getTotalPage(Integer userId) {
        int tl=0;
        ResultSet resultSet=null;
        try {
            String sql="select count(*) from t_order where user_id = ?;";
          resultSet=query(sql,userId);
            while(resultSet.next())
            {
                tl=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            free();
        }
        return tl;
    }

}
